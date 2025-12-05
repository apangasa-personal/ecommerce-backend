package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.WishListItem;
import com.backendproject.catalogue.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WishListItemRepoTest {

    @Autowired
    private WishListItemRepo wishListItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Test
    void shouldSaveAndFindByUserId() {
        Product product = new Product();
        product.setSku("SKU777");
        product.setTitle("Wishlist Product");
        product.setMrp(BigDecimal.valueOf(700));
        product.setDiscountPrice(BigDecimal.valueOf(650));
        product.setUnit("pcs");
        productRepo.save(product);

        WishListItem item = new WishListItem();
        item.setUserId(10L);
        item.setProduct(product);
        wishListItemRepo.save(item);

        List<WishListItem> found = wishListItemRepo.findByUserId(10L);
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getProduct().getSku()).isEqualTo("SKU777");
    }
}
