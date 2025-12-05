package com.backendproject.catalogue.dto;

import com.backendproject.catalogue.entity.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemDtoTest {

    @Test
    void shouldCreateCartItemRequestProperly() {
        UUID productId = UUID.randomUUID();
        CartItemRequest req = new CartItemRequest(productId, 3);

        assertThat(req.productId()).isEqualTo(productId);
        assertThat(req.quantity()).isEqualTo(3);
    }

    @Test
    void shouldBuildResponseProperly() {
        CartItemResponse res = new CartItemResponse(
                123L,
                456L,
                "ProductName",
                "pcs",
                2,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(900)
        );

        assertThat(res.productId()).isEqualTo(456L);
        assertThat(res.quantity()).isEqualTo(2);
    }
}
