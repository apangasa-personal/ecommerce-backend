package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.CartItem;
import com.backendproject.catalogue.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CartItemRepoTest {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Test
    void shouldSaveAndRetrieveCartItemsByUserId() {
        // Create Product
        Product product = new Product();
        product.setSku("SKU100");
        product.setTitle("Test Product");
        product.setMrp(BigDecimal.valueOf(1000));
        product.setDiscountPrice(BigDecimal.valueOf(950));
        product.setUnit("pcs");
        productRepo.save(product);

        // Create Cart Item
        CartItem item = new CartItem();
        item.setUserId(1L);
        item.setProduct(product);
        item.setQuantity(2);
        cartItemRepo.save(item);

        // Fetch by userId
        List<CartItem> found = cartItemRepo.findByUserId(1L);
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getQuantity()).isEqualTo(2);
        assertThat(found.get(0).getProduct().getSku()).isEqualTo("SKU100");
    }
}
