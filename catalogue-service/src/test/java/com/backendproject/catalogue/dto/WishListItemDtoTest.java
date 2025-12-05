package com.backendproject.catalogue.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class WishListItemDtoTest {

    @Test
    void shouldCreateRequestProperly() {
        UUID productId = UUID.randomUUID();
        WishListItemRequest req = new WishListItemRequest(productId);

        assertThat(req.productId()).isEqualTo(productId);
    }

    @Test
    void shouldBuildResponseProperly() {
        WishListItemResponse res = new WishListItemResponse(
                234L,
                678L,
                "ProductName",
                "pcs"
        );

        assertThat(res.productId()).isEqualTo(678L);
    }
}
