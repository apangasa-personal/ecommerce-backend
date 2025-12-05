package com.backendproject.payment.service.impl;

import com.backendproject.payment.dto.PaymentRequest;
import com.backendproject.payment.dto.PaymentResponse;
import com.backendproject.payment.entity.Payment;
import com.backendproject.payment.entity.PaymentStatus;
import com.backendproject.payment.integration.RazorpayIntegration;
import com.backendproject.payment.repository.PaymentRepository;
import com.backendproject.payment.service.PaymentService;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RazorpayIntegration razorpayIntegration;

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${razorpay.secretKey}")
    private String secretKey;

    @Override
    public PaymentResponse createOrder(PaymentRequest request) {
        try {
            String currency = "INR";
            String receiptId = "txn_" + System.currentTimeMillis();

            Order order = razorpayIntegration.createOrder(request.getAmount(), currency, receiptId);

            String orderId = order.get("id").toString();
            String status = order.get("status").toString();

            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setAmount(request.getAmount());
            payment.setStatus(PaymentStatus.CREATED);
            paymentRepository.save(payment);

            return new PaymentResponse(orderId, status, "Order created successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean verifyPayment(String orderId, String paymentId, String signature) {
        boolean isValid = razorpayIntegration.verifySignature(orderId, paymentId, signature, secretKey);

        Payment payment = paymentRepository.findByOrderId(orderId).orElse(null);
        if (payment != null) {
            payment.setStatus(isValid ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
            paymentRepository.save(payment);
        }

        return isValid;
    }
}
