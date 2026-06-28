package com.example.smartexpirytracker.service;

import com.example.smartexpirytracker.dto.request.LoginRequestDTO;
import com.example.smartexpirytracker.dto.request.RegisterRequestDTO;
import com.example.smartexpirytracker.dto.response.LoginResponseDTO;
import com.example.smartexpirytracker.dto.response.UserResponseDTO;

public interface AuthenticationService {
    UserResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}