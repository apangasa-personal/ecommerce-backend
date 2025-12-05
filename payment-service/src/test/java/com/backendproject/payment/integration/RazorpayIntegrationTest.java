package com.backendproject.payment.integration;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RazorpayIntegrationTest {

    @Mock
    private RazorpayClient razorpayClient;

    @InjectMocks
    private RazorpayIntegration razorpayIntegration;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        razorpayIntegration = new RazorpayIntegration("test_key", "test_secret");
    }

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {
        // Mock Razorpay order
        Order mockOrder = mock(Order.class);
        when(mockOrder.get("id")).thenReturn("order_123");
        when(mockOrder.get("status")).thenReturn("created");

        // Stub client
        RazorpayClient mockClient = mock(RazorpayClient.class);
        when(mockClient.orders.create(any(JSONObject.class))).thenReturn(mockOrder);

        // Replace internal client using reflection (to avoid real API)
        var field = RazorpayIntegration.class.getDeclaredField("razorpayClient");
        field.setAccessible(true);
        field.set(razorpayIntegration, mockClient);

        Order order = razorpayIntegration.createOrder(500.0, "INR", "receipt_123");

        assertNotNull(order);
        assertEquals("order_123", order.get("id"));
    }

    @Test
    void shouldVerifySignatureSuccessfully() {
        boolean result = razorpayIntegration.verifySignature("order_123", "pay_456", "fake_signature", "test_secret");
        assertFalse(result); // expected false since we didn’t mock actual signature verification
    }
}
