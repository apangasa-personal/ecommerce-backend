package com.backendproject.order.client;

import com.backendproject.order.dto.PaymentCreateRequest;
import com.backendproject.order.dto.PaymentCreateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PaymentClient(RestTemplate restTemplate,
                         @Value("${clients.payment.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Calls payment-service to create a gateway order (e.g., Razorpay)
     * Example endpoint in your payment-service:
     * POST {base}/payments/orders
     */
    public PaymentCreateResponse createPaymentOrder(PaymentCreateRequest req) {
        String url = baseUrl + "/payments/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentCreateRequest> entity = new HttpEntity<>(req, headers);

        ResponseEntity<PaymentCreateResponse> resp =
                restTemplate.postForEntity(url, entity, PaymentCreateResponse.class);

        return resp.getBody();
    }
}
