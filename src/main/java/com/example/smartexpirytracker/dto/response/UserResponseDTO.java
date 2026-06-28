package com.example.smartexpirytracker.dto.response;

import com.example.smartexpirytracker.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String username;

    private String email;

    private RoleName role;

}