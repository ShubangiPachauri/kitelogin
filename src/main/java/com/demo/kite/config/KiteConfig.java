package com.demo.kite.config;

import com.zerodhatech.kiteconnect.KiteConnect;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KiteConfig {

    @Value("${kite.apiKey}")
    private String apiKey;

    @Value("${kite.userId}")
    private String userId;

    @Bean
    public KiteConnect kiteConnect() {
        KiteConnect kiteConnect = new KiteConnect(apiKey);
        kiteConnect.setUserId(userId);
        return kiteConnect;
    }
}