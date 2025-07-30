package com.demo.kite.controller;

import com.demo.kite.service.KiteLoginService;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/kite")
@RequiredArgsConstructor
public class KiteLoginController {

    private final KiteLoginService kiteLoginService;

    @GetMapping("/login")
    public String loginUrl() {
        return kiteLoginService.getLoginUrl();
    }

    @PostMapping("/access-token")
    public User getAccessToken(@RequestParam String requestToken) {
        try {
            return kiteLoginService.generateSession(requestToken);
        } catch (IOException | KiteException | JSONException e) {
            // Log or rethrow or return null or a custom error response
            throw new RuntimeException("Failed to generate session", e);
        }
    }
}
