package com.backendproject.payment.controller;

import com.backendproject.payment.dto.PaymentRequest;
import com.backendproject.payment.dto.PaymentResponse;
import com.backendproject.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam String signature) {
        boolean verified = paymentService.verifyPayment(orderId, paymentId, signature);
        return ResponseEntity.ok(verified ? "Payment verified" : "Verification failed");
    }
}
