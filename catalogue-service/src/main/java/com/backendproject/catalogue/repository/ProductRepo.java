package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);

    // Added from PR for search functionality
    Page<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title, String description, Pageable pageable
    );
}
