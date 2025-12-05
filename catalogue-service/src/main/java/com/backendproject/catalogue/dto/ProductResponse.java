package com.backendproject.catalogue.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String sku,
        String title,
        String description,
        String sellerName,
        String sellerDescription,
        String barcode,
        Integer minOrderQuantity,
        Integer maxOrderQuantity,
        BigDecimal mrp,
        BigDecimal discountPrice,
        String unit,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
