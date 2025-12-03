package com.backendproject.shared.entity;

// JPA Imports (needed for @Entity)
import jakarta.persistence.Entity;

// Lombok Imports
import lombok.Data; // Provides @Getter, @Setter, @EqualsAndHashCode, @ToString
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // <--- Add this import!
@Data // <--- Replaces individual @Getter, @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
// Ensure TestEntity extends BaseEntity
public class TestEntity extends BaseEntity {

    // Test fields if needed, otherwise leave blank.
    // The class is intentionally simple to test the parent.
}