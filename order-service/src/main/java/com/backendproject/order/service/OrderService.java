package com.backendproject.order.service;

import com.backendproject.order.dto.CreateOrderRequest;
import com.backendproject.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    OrderResponse getOrder(Long id);
}
