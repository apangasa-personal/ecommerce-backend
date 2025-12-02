package com.backendproject.shared.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDateTime;

class BaseEntityTest {

    @Test
    void testLombokGettersAndSetters() {
        TestEntity entity = new TestEntity();
        Long expectedId = 100L;
        LocalDateTime testTime = LocalDateTime.now();

        // Test Setters
        entity.setId(expectedId);
        entity.setCreatedAt(testTime);
        entity.setUpdatedAt(testTime);

        // Test Getters
        assertEquals(expectedId, entity.getId(), "ID getter should return the set value.");
        assertEquals(testTime, entity.getCreatedAt(), "createdAt getter should return the set value.");
        assertEquals(testTime, entity.getUpdatedAt(), "updatedAt getter should return the set value.");
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        TestEntity originalEntity = new TestEntity();
        originalEntity.setId(5L);
        originalEntity.setCreatedAt(LocalDateTime.now());

        // Serialize the object
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(originalEntity);
        out.close();

        // Deserialize the object
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        TestEntity deserializedEntity = (TestEntity) in.readObject();
        in.close();

        // Verify that the deserialized object is equal to the original
        assertNotNull(deserializedEntity, "Deserialized entity should not be null.");
        assertEquals(originalEntity.getId(), deserializedEntity.getId(), "ID should be preserved after serialization.");
        // We use isEquals() on LocalDateTime to ensure exact comparison
        assertTrue(originalEntity.getCreatedAt().isEqual(deserializedEntity.getCreatedAt()), "Creation time should be preserved after serialization.");
    }
}