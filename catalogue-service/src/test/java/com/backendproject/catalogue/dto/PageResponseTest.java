package com.backendproject.catalogue.dto;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PageResponseTest {

    @Test
    void shouldCreatePageResponseFromPage() {
        ProductCard p1 = new ProductCard(1L, "SKU1", "Mouse", BigDecimal.valueOf(500), BigDecimal.valueOf(450));
        ProductCard p2 = new ProductCard(2L, "SKU2", "Keyboard", BigDecimal.valueOf(700), BigDecimal.valueOf(650));

        var page = new PageImpl<>(List.of(p1, p2), PageRequest.of(0, 2), 2);
        var response = PageResponse.of(page);

        assertThat(response.content()).hasSize(2);
        assertThat(response.totalPages()).isEqualTo(1);
        assertThat(response.pageNumber()).isEqualTo(0);
    }
}
