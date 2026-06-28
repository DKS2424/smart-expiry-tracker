package com.example.smartexpirytracker.dto.request;

import com.example.smartexpirytracker.entity.RoleName;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
    private RoleName role;
}