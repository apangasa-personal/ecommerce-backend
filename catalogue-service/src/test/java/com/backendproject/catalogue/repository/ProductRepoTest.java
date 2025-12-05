package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void testSaveAndFindProduct() {
        Product product = new Product();
        product.setTitle("Test Product");
        product.setSku("SKU123");
        product.setMrp(BigDecimal.valueOf(999.99));
        product.setDiscountPrice(BigDecimal.valueOf(899.99));
        product.setMinOrderQuantity(1);
        product.setMaxOrderQuantity(5);
        product.setUnit("piece");
        product.setBarcode("12345");
        product.setSku("SKU123");
        product.setSellerName("Test Seller");
        product.setSellerDescription("Reliable seller");


        Product saved = productRepo.save(product);
        assertThat(saved.getId()).isNotNull();
        assertThat(productRepo.findById(saved.getId())).isPresent();
    }

    // ✅ local config ensures NO main app context loads
    @Configuration
    @EnableJpaRepositories(basePackages = "com.backendproject.catalogue.repository")
    @EntityScan(basePackages = "com.backendproject.catalogue.entity")
    static class TestConfig {
    }
}
