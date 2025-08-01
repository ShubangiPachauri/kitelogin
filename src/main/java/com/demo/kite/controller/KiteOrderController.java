package com.demo.kite.controller;

import com.demo.kite.dto.BuyOrderRequest;
import com.demo.kite.service.KiteOrderService;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class KiteOrderController {

    private final KiteOrderService orderService;

@PostMapping("/buy")
public String placeBuyOrder(@RequestBody BuyOrderRequest request)
        throws KiteException, IOException, JSONException {
    return orderService.placeBuyOrder();
}
}
