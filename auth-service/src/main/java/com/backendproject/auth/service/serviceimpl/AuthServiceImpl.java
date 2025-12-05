package com.backendproject.auth.service.impl;

import com.backendproject.auth.dto.LoginRequest;
import com.backendproject.auth.dto.RegisterRequest;
import com.backendproject.auth.entity.User;
import com.backendproject.auth.repository.UserRepository;
import com.backendproject.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Object register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public Object login(LoginRequest request) {
        return "Login successful for " + request.getEmail();
    }
}
