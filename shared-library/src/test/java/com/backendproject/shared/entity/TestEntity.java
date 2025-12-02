package com.backendproject.shared.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A simple concrete class extending BaseEntity for unit testing purposes.
 * We include the @Entity annotation just to satisfy JPA conventions in the test context.
 */
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class TestEntity extends BaseEntity {
    // No fields needed here, we just test the inherited fields
}