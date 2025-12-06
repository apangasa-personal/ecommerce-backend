package com.backendproject.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    @Email
    @NotNull
    private String userEmail;

    @NotEmpty
    private List<ItemRequest> items;
}
