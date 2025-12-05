// WishListItemRepo.java
package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface WishListItemRepo extends JpaRepository<WishListItem, UUID> {
    List<WishListItem> findByUserId(Long userId);
}
