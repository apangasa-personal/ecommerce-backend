// CartItemResponse.java
package com.backendproject.catalogue.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(
        Long id,
        Long productId,
        String title,
        String imageUrl,
        Integer quantity,
        BigDecimal mrp,
        BigDecimal discountPrice
) {}
