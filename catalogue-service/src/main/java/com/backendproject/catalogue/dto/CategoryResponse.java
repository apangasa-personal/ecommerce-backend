// CategoryResponse.java
package com.backendproject.catalogue.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponse(
        Long id,
        String title,
        String slug,
        String description,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
