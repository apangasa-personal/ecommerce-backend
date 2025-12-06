package com.backendproject.order.controller;

import com.backendproject.order.dto.CreateOrderRequest;
import com.backendproject.order.dto.OrderResponse;
import com.backendproject.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> byId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
}
