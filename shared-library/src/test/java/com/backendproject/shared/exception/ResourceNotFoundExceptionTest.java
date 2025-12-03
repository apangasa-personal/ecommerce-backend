package com.backendproject.shared.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testMessageConstructor() {
        String message = "Entity not found";
        ResourceNotFoundException ex = new ResourceNotFoundException(message);
        assertEquals(message, ex.getMessage());
    }

    @Test
    void testDetailedConstructor() {
        ResourceNotFoundException ex = new ResourceNotFoundException("User", "id", 123);
        assertEquals("User not found with id : '123'", ex.getMessage());
    }
}
