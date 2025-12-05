// CartItemRepo.java
package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByUserId(Long userId);
}
