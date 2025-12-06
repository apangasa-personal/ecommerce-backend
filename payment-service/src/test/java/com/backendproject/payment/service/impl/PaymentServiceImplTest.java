package com.backendproject.payment.service.impl;

import com.backendproject.payment.dto.PaymentRequest;
import com.backendproject.payment.entity.Payment;
import com.backendproject.payment.entity.PaymentStatus;
import com.backendproject.payment.integration.RazorpayIntegration;
import com.backendproject.payment.repository.PaymentRepository;
import com.razorpay.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RazorpayIntegration razorpayIntegration;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setup() {
        // Inject dummy secret key
        ReflectionTestUtils.setField(paymentService, "secretKey", "dummy_secret");
    }

    @Test
    void shouldCreateOrderAndSavePayment() throws Exception {
        // Mock Razorpay order response
        Order mockOrder = mock(Order.class);
        when(mockOrder.get("id")).thenReturn("order_123");
        when(mockOrder.get("status")).thenReturn("created");
        when(razorpayIntegration.createOrder(anyDouble(), anyString(), anyString()))
                .thenReturn(mockOrder);

        // Mock Payment repository save
        Payment savedPayment = new Payment();
        savedPayment.setId(1L);
        savedPayment.setStatus(PaymentStatus.CREATED);

        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        // Build the request
        PaymentRequest request = new PaymentRequest();
        request.setAmount(500.0);
        request.setEmail("test@example.com");
        request.setReceipt("Test Order");

        var result = paymentService.createOrder(request);

        assertNotNull(result);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void shouldVerifyPaymentSuccessfully() throws Exception {
        when(razorpayIntegration.verifySignature(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        boolean result = paymentService.verifyPayment("orderId", "paymentId", "signature");

        assertTrue(result);
        verify(razorpayIntegration, times(1))
                .verifySignature(anyString(), anyString(), anyString(), eq("dummy_secret"));
    }

    @Test
    void shouldFailVerificationWhenInvalidSignature() throws Exception {
        when(razorpayIntegration.verifySignature(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(false);

        boolean result = paymentService.verifyPayment("orderId", "paymentId", "signature");

        assertFalse(result);
        verify(razorpayIntegration, times(1))
                .verifySignature(anyString(), anyString(), anyString(), eq("dummy_secret"));
    }
}
