package com.backendproject.catalogue.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class ProductEntityTest {

    @Test
    void shouldCreateProductSuccessfully() {
        Product product = Product.builder()
                .title("Fresh Apples")
                .description("Organic farm apples")
                .sku("APPLE123")
                .mrp(new BigDecimal("120.50"))
                .discountPrice(new BigDecimal("99.99"))
                .unit("kg")
                .sellerName("FruitMart")
                .imageUrl("http://example.com/apple.jpg")
                .build();

        assertThat(product.getTitle()).isEqualTo("Fresh Apples");
        assertThat(product.getDiscountPrice()).isEqualByComparingTo("99.99");
        assertThat(product.getSku()).isNotNull();
    }
}
