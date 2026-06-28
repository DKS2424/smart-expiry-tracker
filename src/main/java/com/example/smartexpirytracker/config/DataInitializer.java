package com.example.smartexpirytracker.config;

import com.example.smartexpirytracker.entity.RoleName;
import com.example.smartexpirytracker.entity.User;
import com.example.smartexpirytracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (!userRepository.existsByEmail("admin@gmail.com")) {

            User admin = new User();

            admin.setUsername("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(RoleName.ROLE_ADMIN);

            userRepository.save(admin);

            System.out.println("Admin account created.");
        }

    }
}