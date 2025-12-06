package com.backendproject.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CataloguePriceDto {
    private Long productId;
    private String name;
    private BigDecimal price;
    private boolean available;
}
