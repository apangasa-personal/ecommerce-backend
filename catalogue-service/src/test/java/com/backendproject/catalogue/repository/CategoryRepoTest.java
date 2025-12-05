package com.backendproject.catalogue.repository;

import com.backendproject.catalogue.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    void shouldSaveAndFindCategoryBySlug() {
        Category category = new Category();
        category.setTitle("Fruits");
        category.setSlug("fruits");

        Category saved = categoryRepo.save(category);
        assertThat(saved.getId()).isNotNull();
        assertThat(categoryRepo.findById(saved.getId())).isPresent();
    }
}
