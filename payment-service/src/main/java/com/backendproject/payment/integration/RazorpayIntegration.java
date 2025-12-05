package com.backendproject.payment.integration;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RazorpayIntegration {

    private final RazorpayClient razorpayClient;
    private final String secretKey;

    public RazorpayIntegration(
            @Value("${razorpay.keyId}") String keyId,
            @Value("${razorpay.secretKey}") String secretKey) throws Exception {
        this.razorpayClient = new RazorpayClient(keyId, secretKey);
        this.secretKey = secretKey;
    }

    public Order createOrder(double amount, String currency, String receiptId) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100);
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receiptId);

        return razorpayClient.orders.create(orderRequest);
    }

    public boolean verifySignature(String orderId, String paymentId, String signature, String secret) {
        try {
            String payload = orderId + "|" + paymentId;
            Utils.verifySignature(payload, signature, secret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
