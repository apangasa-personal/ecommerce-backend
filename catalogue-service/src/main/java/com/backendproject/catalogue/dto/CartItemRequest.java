// CartItemRequest.java
package com.backendproject.catalogue.dto;

import java.util.UUID;

public record CartItemRequest(
        UUID productId,
        Integer quantity
) {}
