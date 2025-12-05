package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WishListRepo extends JpaRepository<WishListItem, Long> {
    List<WishListItem> findByUserId(Long userId);
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
