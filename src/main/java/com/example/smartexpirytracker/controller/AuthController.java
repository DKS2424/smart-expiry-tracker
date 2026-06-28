package com.example.smartexpirytracker.controller;

import com.example.smartexpirytracker.dto.request.LoginRequestDTO;
import com.example.smartexpirytracker.dto.request.RegisterRequestDTO;
import com.example.smartexpirytracker.dto.response.LoginResponseDTO;
import com.example.smartexpirytracker.dto.response.UserResponseDTO;
import com.example.smartexpirytracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authenticationService.login(request);
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return authenticationService.register(request);
    }
}