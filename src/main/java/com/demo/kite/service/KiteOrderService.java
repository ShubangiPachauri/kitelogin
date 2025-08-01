package com.demo.kite.service;

import com.demo.kite.entity.KiteSessionInfo;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.OrderParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KiteOrderService {

    private final KiteLoginService kiteLoginService; 

    public String placeBuyOrder() throws IOException, KiteException, JSONException {
        // ✅ Step 1: Get KiteConnect instance
        KiteConnect kiteConnect = kiteLoginService.getKiteConnect();

        // ✅ Step 2: Get latest session from DB
        KiteSessionInfo session = kiteLoginService.getLatestSession(); 

        // ✅ Step 3: Set access token
        kiteConnect.setAccessToken(session.getAccessToken());

        // ✅ Step 4: Set order details
        OrderParams orderParams = new OrderParams();
        orderParams.quantity = 1;
        orderParams.orderType = Constants.ORDER_TYPE_LIMIT;
        orderParams.tradingsymbol = "ASHOKLEY";
        orderParams.product = Constants.PRODUCT_CNC;
        orderParams.exchange = Constants.EXCHANGE_NSE;
        orderParams.transactionType = Constants.TRANSACTION_TYPE_BUY;
        orderParams.validity = Constants.VALIDITY_DAY;
        orderParams.price = 122.2;
        orderParams.triggerPrice = 0.0;
        orderParams.tag = "myTag";

        Order order = kiteConnect.placeOrder(orderParams, Constants.VARIETY_REGULAR);
        log.info("✅ Order Placed: ID = {}", order.orderId);
        return "Buy order placed successfully. Order ID: " + order.orderId;
    }
}
