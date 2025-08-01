package com.demo.kite.dto;

import lombok.Data;

@Data
public class BuyOrderRequest {
    private String tradingSymbol;
    private String exchange;            // e.g., NSE, BSE
    private String transactionType;     // BUY or SELL
    private String orderType;           // MARKET, LIMIT, SL, etc.
    private String product;             // CNC, MIS, NRML, etc.
    private String validity;            // DAY, IOC, etc.
    private int quantity;
    private double price;              // applicable in LIMIT order
    private double triggerPrice;       // for SL/SL-M orders
    private String tag;                // optional, max 8 chars
}
