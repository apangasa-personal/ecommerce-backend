package com.backendproject.payment.service;

import com.backendproject.payment.dto.PaymentRequest;
import com.backendproject.payment.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse createOrder(PaymentRequest request);

    boolean verifyPayment(String orderId, String paymentId, String signature);
}
