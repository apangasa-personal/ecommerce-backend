// CategoryMapper.java
package com.backendproject.catalogue.mapper;

import com.backendproject.catalogue.dto.*;
import com.backendproject.catalogue.entity.Category;

public final class CategoryMapper {
    private CategoryMapper() {}

    public static Category toEntity(CategoryRequest r) {
        var c = new Category();
        c.setTitle(r.title());
        c.setSlug(r.slug());
        c.setDescription(r.description());
        c.setImageUrl(r.imageUrl());
        return c;
    }

    public static CategoryResponse toResponse(Category c) {
        return new CategoryResponse(
                c.getId(),
                c.getTitle(),
                c.getSlug(),
                c.getDescription(),
                c.getImageUrl(),
                c.getCreatedAt(),
                c.getUpdatedAt()
        );
    }
}
