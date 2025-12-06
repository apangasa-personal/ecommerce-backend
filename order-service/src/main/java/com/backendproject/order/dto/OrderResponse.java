package com.backendproject.order.dto;

import com.backendproject.order.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private String userEmail;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private String paymentGatewayOrderId;
    private String receipt;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
}
