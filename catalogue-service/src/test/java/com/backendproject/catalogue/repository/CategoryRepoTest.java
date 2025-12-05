package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    void shouldSaveAndFindCategoryBySlug() {
        Category category = new Category();
        category.setSlug("electronics");
        category.setTitle("Electronics");
        categoryRepo.save(category);

        Optional<Category> found = categoryRepo.findBySlug("electronics");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Electronics");
    }

    @Test
    void shouldNotFindCategoryWithInvalidSlug() {
        Optional<Category> found = categoryRepo.findBySlug("invalid-slug");
        assertThat(found).isNotPresent();
    }
}
