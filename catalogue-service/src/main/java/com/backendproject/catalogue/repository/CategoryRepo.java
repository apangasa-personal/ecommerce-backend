package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findBySlug(String slug);
    boolean existsByTitle(String title);
}
