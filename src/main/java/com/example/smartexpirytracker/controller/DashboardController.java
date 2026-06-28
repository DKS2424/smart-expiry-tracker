package com.example.smartexpirytracker.controller;

import com.example.smartexpirytracker.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ProductRepository productRepository;

    @GetMapping
    public Map<String, Object> dashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalProducts", productRepository.count());
        response.put("expiredProducts",
            productRepository.findByExpiryDateBefore(LocalDate.now()).size());
        response.put("expiringSoon",
            productRepository.findByExpiryDateBetween(
                LocalDate.now(), LocalDate.now().plusDays(7)).size());
        response.put("lowStockProducts",
            productRepository.findByQuantityLessThan(10).size());
        return response;
    }
}