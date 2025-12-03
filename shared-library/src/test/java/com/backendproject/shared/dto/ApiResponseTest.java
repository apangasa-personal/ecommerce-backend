package com.backendproject.shared.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testSuccessStaticMethod() {
        String data = "Hello";
        ApiResponse<String> response = ApiResponse.success(data);

        assertTrue(response.isSuccess());
        assertEquals("Operation successful", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    void testFailureStaticMethod() {
        String errorMessage = "Something went wrong";
        ApiResponse<String> response = ApiResponse.failure(errorMessage);

        assertFalse(response.isSuccess());
        assertEquals(errorMessage, response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void testAllArgsConstructor() {
        ApiResponse<Integer> response = new ApiResponse<>(true, "OK", 100);

        assertTrue(response.isSuccess());
        assertEquals("OK", response.getMessage());
        assertEquals(100, response.getData());
    }
}
