// ProductCard.java  (compact list item)
package com.backendproject.catalogue.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCard(
        Long id,
        String title,
        String imageUrl,
        BigDecimal mrp,
        BigDecimal discountPrice
) {}
