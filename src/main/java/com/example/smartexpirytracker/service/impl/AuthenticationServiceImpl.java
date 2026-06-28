package com.example.smartexpirytracker.service.impl;

import com.example.smartexpirytracker.dto.request.RegisterRequestDTO;
import com.example.smartexpirytracker.dto.request.LoginRequestDTO;
import com.example.smartexpirytracker.dto.response.UserResponseDTO;
import com.example.smartexpirytracker.dto.response.LoginResponseDTO;
import com.example.smartexpirytracker.entity.RoleName;
import com.example.smartexpirytracker.entity.User;
import com.example.smartexpirytracker.repository.UserRepository;
import com.example.smartexpirytracker.security.JwtService;
import com.example.smartexpirytracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;  // inject JWT service

    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // FIX 1: use role from request instead of hardcoded ROLE_STAFF
        user.setRole(request.getRole() != null ? request.getRole() : RoleName.ROLE_STAFF);

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(
            savedUser.getId(),
            savedUser.getUsername(),
            savedUser.getEmail(),
            savedUser.getRole()
        );
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // FIX 2: generate real JWT token
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return new LoginResponseDTO(
            token,
            user.getEmail(),
            user.getRole().name(),
            "Login successful"
        );
    }
}