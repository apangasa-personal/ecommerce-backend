package com.backendproject.catalogue.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryDtoTest {

    @Test
    void shouldCreateCategoryRequestProperly() {
        CategoryRequest req = new CategoryRequest(
                "electronics",
                "Electronics",
                "Category for gadgets",
                "imageUrl"
        );

        assertThat(req.slug()).isEqualTo("Electronics");
        assertThat(req.title()).isEqualTo("electronics");
    }

    @Test
    void shouldBuildCategoryResponseProperly() {
        CategoryResponse res = new CategoryResponse(
                1L,
                "fashion",
                "Fashion",
                "Trendy items",
                "img.jpg",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        assertThat(res.title()).isEqualTo("fashion");
        assertThat(res.slug()).isEqualTo("Fashion");
    }
}
