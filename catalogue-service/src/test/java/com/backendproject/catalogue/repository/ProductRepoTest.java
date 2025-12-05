package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void testSaveAndFindProduct() {
        Product product = new Product();
        product.setSku("SKU123");
        product.setTitle("Sample Product");
        product.setMrp(new BigDecimal("1000.0"));
        product.setDiscountPrice(new BigDecimal("900.0"));
        product.setUnit("pcs");  // ✅ add this line
        productRepo.save(product);

        Optional<Product> found = productRepo.findBySku("SKU123");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Sample Product");
    }
}
