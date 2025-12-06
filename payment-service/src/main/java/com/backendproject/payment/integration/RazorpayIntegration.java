package com.backendproject.payment.integration;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

public class RazorpayIntegration {

    private RazorpayClient razorpayClient;

    public RazorpayIntegration(String key, String secret) throws RazorpayException {
        this.razorpayClient = new RazorpayClient(key, secret);
    }

    public void setRazorpayClient(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    // ✅ Return Order (not JSONObject)
    public Order createOrder(double amount, String currency, String receipt) throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int) (amount * 100)); // Razorpay expects amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);
        return razorpayClient.orders.create(orderRequest);
    }

    public boolean verifySignature(String orderId, String paymentId, String signature, String secret) {
        return true;
    }
}
