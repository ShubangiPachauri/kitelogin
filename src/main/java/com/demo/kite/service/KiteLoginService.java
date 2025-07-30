package com.demo.kite.service;

import com.demo.kite.entity.KiteSessionInfo;
import com.demo.kite.repo.KiteSessionRepository;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class KiteLoginService {

    private final KiteConnect kiteConnect;
    private final KiteSessionRepository credentialsRepository;

    @Value("${kite.secret}")
    private String secret;

    public String getLoginUrl() {
        return kiteConnect.getLoginURL();
    }

    public User generateSession(String requestToken) throws IOException, KiteException, JSONException {
        log.info("Generating session with request token: {}", requestToken);
        System.out.println(kiteConnect.getUserId());
        
        
        
        // Generate session using token and secret
        User user = kiteConnect.generateSession(requestToken, secret);

        // Save the credentials
        KiteSessionInfo credentials = new KiteSessionInfo();
        credentials.setAccessToken(kiteConnect.getAccessToken());
        credentials.setPublicToken(kiteConnect.getPublicToken());
        credentials.setUserId(kiteConnect.getUserId());
        credentials.setLoginTime(LocalDateTime.now());

        credentialsRepository.save(credentials);

        log.info("Session generated and saved for user: {}", user.userId);
        return user;
    }
}
