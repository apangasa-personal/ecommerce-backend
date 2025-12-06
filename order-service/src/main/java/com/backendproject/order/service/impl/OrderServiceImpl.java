package com.backendproject.order.service.impl;

import com.backendproject.order.client.CatalogueClient;
import com.backendproject.order.client.PaymentClient;
import com.backendproject.order.dto.*;
import com.backendproject.order.entity.Order;
import com.backendproject.order.entity.OrderItem;
import com.backendproject.order.enums.OrderStatus;
import com.backendproject.order.repository.OrderRepository;
import com.backendproject.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CatalogueClient catalogueClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        // 1) Fetch pricing/availability from catalogue
        List<Long> productIds = request.getItems().stream()
                .map(ItemRequest::getProductId)
                .toList();

        List<CataloguePriceDto> prices = catalogueClient.fetchPricing(productIds);
        Map<Long, CataloguePriceDto> byId = prices.stream()
                .collect(Collectors.toMap(CataloguePriceDto::getProductId, x -> x));

        // 2) Build Order + items, compute total
        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();

        for (ItemRequest it : request.getItems()) {
            CataloguePriceDto p = byId.get(it.getProductId());
            if (p == null || !p.isAvailable()) {
                throw new IllegalArgumentException("Product unavailable: " + it.getProductId());
            }
            BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(it.getQuantity()));
            total = total.add(line);

            OrderItem oi = OrderItem.builder()
                    .productId(p.getProductId())
                    .productName(p.getName())
                    .unitPrice(p.getPrice())
                    .quantity(it.getQuantity())
                    .build();
            items.add(oi);
        }

        String receipt = "rcpt_" + UUID.randomUUID();

        final Order orderEntity = Order.builder()
                .userEmail(request.getUserEmail())
                .totalAmount(total)
                .status(OrderStatus.PAYMENT_PENDING)
                .receipt(receipt)
                .createdAt(LocalDateTime.now())
                .build();

        items.forEach(i -> i.setOrder(orderEntity));
        orderEntity.setItems(items);

        Order savedOrder = orderRepository.save(orderEntity);

        PaymentCreateRequest payReq =
                new PaymentCreateRequest(total.doubleValue(), request.getUserEmail(), receipt);

        PaymentCreateResponse payResp = paymentClient.createPaymentOrder(payReq);
        if (payResp != null) {
            savedOrder.setPaymentGatewayOrderId(payResp.getOrderId());
            orderRepository.save(savedOrder);
        }

        return toResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found: " + id));
        return toResponse(order);
    }

    private OrderResponse toResponse(Order o) {
        return OrderResponse.builder()
                .orderId(o.getId())
                .userEmail(o.getUserEmail())
                .totalAmount(o.getTotalAmount())
                .status(o.getStatus())
                .paymentGatewayOrderId(o.getPaymentGatewayOrderId())
                .receipt(o.getReceipt())
                .createdAt(o.getCreatedAt())
                .items(
                        o.getItems().stream()
                                .map(i -> new OrderItemResponse(
                                        i.getProductId(),
                                        i.getProductName(),
                                        i.getQuantity(),
                                        i.getUnitPrice()))
                                .toList()
                )
                .build();
    }
}
