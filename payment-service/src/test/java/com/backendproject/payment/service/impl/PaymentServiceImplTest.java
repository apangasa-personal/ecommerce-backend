package com.backendproject.payment.service.impl;

import com.backendproject.payment.dto.PaymentRequest;
import com.backendproject.payment.dto.PaymentResponse;
import com.backendproject.payment.entity.Payment;
import com.backendproject.payment.entity.PaymentStatus;
import com.backendproject.payment.integration.RazorpayIntegration;
import com.backendproject.payment.repository.PaymentRepository;
import com.razorpay.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private RazorpayIntegration razorpayIntegration;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(paymentService, "razorpayKey", "test_key");
        ReflectionTestUtils.setField(paymentService, "razorpaySecret", "test_secret");
    }

    @Test
    void shouldCreateOrderAndSavePayment() throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(500);
        request.setReceipt("receipt_123");
        request.setEmail("user@test.com");

        Order mockOrder = mock(Order.class);
        when(mockOrder.get("id")).thenReturn("order_123");
        when(mockOrder.get("status")).thenReturn("created");

        when(razorpayIntegration.createOrder(500, "INR", "receipt_123")).thenReturn(mockOrder);

        PaymentResponse response = paymentService.createOrder(request);

        assertEquals("order_123", response.getOrderId());
        assertEquals("created", response.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void shouldVerifyPaymentSuccessfully() {
        Payment payment = Payment.builder()
                .id(1L)
                .orderId("order_001")
                .status(PaymentStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        when(paymentRepository.findByOrderId("order_001")).thenReturn(Optional.of(payment));
        when(razorpayIntegration.verifySignature(any(), any(), any(), any())).thenReturn(true);

        paymentService.verifyPayment("order_001", "pay_001", "sig_001");

        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        verify(paymentRepository).save(payment);
    }

    @Test
    void shouldFailVerificationWhenInvalidSignature() {
        Payment payment = Payment.builder().orderId("order_002").status(PaymentStatus.CREATED).build();

        when(paymentRepository.findByOrderId("order_002")).thenReturn(Optional.of(payment));
        when(razorpayIntegration.verifySignature(any(), any(), any(), any())).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                paymentService.verifyPayment("order_002", "pay_002", "sig_wrong"));

        assertEquals("Payment signature verification failed", ex.getMessage());
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }
}
