package com.backendproject.order.service;

import com.backendproject.order.client.CatalogueClient;
import com.backendproject.order.client.PaymentClient;
import com.backendproject.order.dto.*;
import com.backendproject.order.entity.Order;
import com.backendproject.order.entity.OrderItem;
import com.backendproject.order.repository.OrderRepository;
import com.backendproject.order.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Test
    void createsOrderAndCallsPayment() {
        CatalogueClient catalogue = mock(CatalogueClient.class);
        PaymentClient payment = mock(PaymentClient.class);
        OrderRepository repo = mock(OrderRepository.class);

        // catalogue prices
        CataloguePriceDto p1 = new CataloguePriceDto();
        p1.setProductId(1L); p1.setName("P1"); p1.setPrice(new BigDecimal("100")); p1.setAvailable(true);

        when(catalogue.fetchPricing(List.of(1L))).thenReturn(List.of(p1));

        // repo save: echo with id
        when(repo.save(any(Order.class))).thenAnswer(inv -> {
            Order o = inv.getArgument(0);
            if (o.getId() == null) o.setId(10L);
            if (o.getItems() != null) {
                for (OrderItem it : o.getItems()) {
                    if (it.getId() == null) it.setId(100L);
                }
            }
            return o;
        });

        // payment service
        PaymentCreateResponse payResp = new PaymentCreateResponse();
        payResp.setOrderId("razor_order_123");
        payResp.setStatus("PENDING");
        payResp.setMessage("ok");
        when(payment.createPaymentOrder(any())).thenReturn(payResp);

        OrderServiceImpl svc = new OrderServiceImpl(catalogue, payment, repo);

        CreateOrderRequest req = new CreateOrderRequest();
        req.setUserEmail("a@b.com");
        ItemRequest ir = new ItemRequest(); ir.setProductId(1L); ir.setQuantity(2);
        req.setItems(List.of(ir));

        var resp = svc.createOrder(req);

        assertThat(resp.getOrderId()).isEqualTo(10L);
        assertThat(resp.getTotalAmount()).isEqualByComparingTo("200");
        assertThat(resp.getPaymentGatewayOrderId()).isEqualTo("razor_order_123");

        // verify payment call amount
        ArgumentCaptor<PaymentCreateRequest> cap = ArgumentCaptor.forClass(PaymentCreateRequest.class);
        verify(payment).createPaymentOrder(cap.capture());
        assertThat(cap.getValue().getAmount()).isEqualTo(200.0);
    }
}
