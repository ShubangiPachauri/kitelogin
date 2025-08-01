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
    private final KiteSessionRepository sessionRepo;

    @Value("${kite.secret}")
    private String secret;

    public String getLoginUrl() {
        return kiteConnect.getLoginURL();
    }

    public User generateSession(String requestToken) throws IOException, KiteException, JSONException {
        log.info("Generating session with request token: {}", requestToken);

        // Step 1: Generate session using token and secret
        User user = kiteConnect.generateSession(requestToken, secret);

        // Step 2: Save the credentials using values from the User object
        KiteSessionInfo credentials = new KiteSessionInfo();
        credentials.setAccessToken(user.accessToken);     // set from User
        credentials.setPublicToken(user.publicToken);     // set from User
        credentials.setUserId(kiteConnect.getUserId());
        credentials.setLoginTime(LocalDateTime.now());

        sessionRepo.save(credentials);

        log.info("✅ Session generated and saved for user: {}", user.userId);
        return user;
    }
    public KiteConnect getKiteConnect() {
        return this.kiteConnect;
    }
 // ✅ NEW METHOD HERE
    public KiteSessionInfo getLatestSession() {
        return sessionRepo.findTopByOrderByLoginTimeDesc()
                .orElseThrow(() -> new RuntimeException("⚠️ No session found. Please login first."));
    }
}
