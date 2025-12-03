package com.backendproject.shared.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.Instant;
import java.util.UUID;
import java.time.temporal.ChronoUnit;

class BaseEntityTest {

    // You must ensure TestEntity is correctly set up with Lombok (@Data) for this test to pass.

    @Test
    void testLombokGettersAndSetters() {
        TestEntity entity = new TestEntity();
        UUID expectedId = UUID.randomUUID();
        // Truncate time for reliable comparison, as Instant's precision can vary
        Instant testTime = Instant.now().truncatedTo(ChronoUnit.MILLIS);

        // Test Setters (which were the missing symbols)
        entity.setId(expectedId);
        entity.setCreatedAt(testTime);
        entity.setUpdatedAt(testTime);

        // Test Getters (which were the missing symbols)
        assertEquals(expectedId, entity.getId(), "ID getter should return the set value.");
        assertEquals(testTime, entity.getCreatedAt(), "createdAt getter should return the set value.");
        assertEquals(testTime, entity.getUpdatedAt(), "updatedAt getter should return the set value.");
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        TestEntity originalEntity = new TestEntity();
        originalEntity.setId(UUID.randomUUID());

        Instant creationTime = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        originalEntity.setCreatedAt(creationTime);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(originalEntity);
        out.close();

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        TestEntity deserializedEntity = (TestEntity) in.readObject();
        in.close();

        assertNotNull(deserializedEntity, "Deserialized entity should not be null.");
        assertEquals(originalEntity.getId(), deserializedEntity.getId(), "ID should be preserved after serialization.");

        // Use equals on Instant which accounts for time zone differences
        assertEquals(originalEntity.getCreatedAt(), deserializedEntity.getCreatedAt(), "Creation time should be preserved after serialization.");
    }
}