// CartItemResponse.java
package com.backendproject.catalogue.dto;

import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        Long productId,
        String title,
        String imageUrl,
        Integer quantity,
        BigDecimal mrp,
        BigDecimal discountPrice
) {}
