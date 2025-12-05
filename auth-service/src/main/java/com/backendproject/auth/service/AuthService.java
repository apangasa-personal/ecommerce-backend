package com.backendproject.auth.service;

import com.backendproject.auth.dto.LoginRequest;
import com.backendproject.auth.dto.RegisterRequest;

public interface AuthService {
    Object register(RegisterRequest request);
    Object login(LoginRequest request);
}
