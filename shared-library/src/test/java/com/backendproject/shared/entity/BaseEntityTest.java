package com.backendproject.shared.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    private BaseEntity baseEntity;

    @BeforeEach
    void setUp() {
        baseEntity = new BaseEntity() {};
    }

    @Test
    void testOnCreateSetsTimestamps() {
        baseEntity.onCreate();
        assertNotNull(baseEntity.getCreatedAt());
        assertNotNull(baseEntity.getUpdatedAt());
        assertEquals(baseEntity.getCreatedAt().getClass(), LocalDateTime.class);
    }

    @Test
    void testOnUpdateChangesUpdatedAt() {
        baseEntity.onCreate();
        LocalDateTime oldUpdatedAt = baseEntity.getUpdatedAt();
        baseEntity.onUpdate();
        assertTrue(baseEntity.getUpdatedAt().isAfter(oldUpdatedAt));
    }

    @Test
    void testIdAssignment() {
        Long id = 123L;
        baseEntity.setId(id);
        assertEquals(id, baseEntity.getId());
    }
}
