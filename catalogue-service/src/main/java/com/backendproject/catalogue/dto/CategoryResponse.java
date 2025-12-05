// CategoryResponse.java
package com.backendproject.catalogue.dto;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String title,
        String slug,
        String description,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
