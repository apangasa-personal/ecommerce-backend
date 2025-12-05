// CategoryRequest.java
package com.backendproject.catalogue.dto;

public record CategoryRequest(
        String title,
        String slug,
        String description,
        String imageUrl
) {}
