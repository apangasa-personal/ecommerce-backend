// WishListItemResponse.java
package com.backendproject.catalogue.dto;

import java.util.UUID;

public record WishListItemResponse(
        Long id,
        Long productId,
        String title,
        String imageUrl
) {}
