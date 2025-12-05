// WishListItemResponse.java
package com.backendproject.catalogue.dto;

public record WishListItemResponse(
        Long id,
        Long productId,
        String title,
        String imageUrl
) {}
