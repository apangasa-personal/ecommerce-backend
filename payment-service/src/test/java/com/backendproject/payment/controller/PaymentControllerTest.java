package com.backendproject.payment.controller;

import static org.hamcrest.Matchers.containsString;

import com.backendproject.payment.dto.PaymentRequest;
import com.backendproject.payment.dto.PaymentResponse;
import com.backendproject.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {
        when(paymentService.createOrder(any(PaymentRequest.class)))
                .thenReturn(new PaymentResponse("order_001", "CREATED", "Order created successfully"));


        String body = """
            {"email":"test@abc.com","amount":5000}
        """;

        mockMvc.perform(
                        post("/payments/create-order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("order_001")));

    }

    @Test
    void shouldVerifyPaymentSuccessfully() throws Exception {
        when(paymentService.createOrder(any(PaymentRequest.class)))
                .thenReturn(new PaymentResponse("order_001", "CREATED", "Order created successfully"));

        mockMvc.perform(
                        post("/payments/verify")
                                .param("orderId","order_001")
                                .param("paymentId","pay_001")
                                .param("signature","sig_abc")
                )
                .andExpect(status().isOk());
    }
}
