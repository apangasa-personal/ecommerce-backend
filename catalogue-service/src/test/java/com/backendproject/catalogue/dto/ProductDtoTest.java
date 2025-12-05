package com.backendproject.catalogue.dto;

import com.backendproject.catalogue.entity.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDtoTest {

    @Test
    void shouldCreateProductEntityFromRequest() {
        ProductRequest req = new ProductRequest(
                "SKU100",
                "Sample Product",
                "Sample Description",
                "Seller A",
                "Seller Description",
                "barcode123",
                1,
                10,
                BigDecimal.valueOf(1200),
                BigDecimal.valueOf(1000),
                "pcs",
                "imageUrl.jpg"
        );

        Product entity = new Product();
        entity.setSku(req.sku());
        entity.setTitle(req.title());
        entity.setDescription(req.description());
        entity.setSellerName(req.sellerName());
        entity.setMrp(req.mrp());
        entity.setDiscountPrice(req.discountPrice());
        entity.setUnit(req.unit());

        assertThat(entity.getSku()).isEqualTo("SKU100");
        assertThat(entity.getUnit()).isEqualTo("pcs");
    }

    @Test
    void shouldBuildResponseCorrectly() {
        ProductResponse res = new ProductResponse(
                1L,
                "SKU200",
                "Test Product",
                "desc",
                "Seller",
                "Seller Desc",
                "barcode",
                1,
                5,
                BigDecimal.valueOf(900),
                BigDecimal.valueOf(850),
                "pcs",
                "img.jpg",
                java.time.LocalDateTime.now(),
                java.time.LocalDateTime.now()
        );

        assertThat(res.sku()).isEqualTo("SKU200");
        assertThat(res.unit()).isEqualTo("pcs");
    }
}
