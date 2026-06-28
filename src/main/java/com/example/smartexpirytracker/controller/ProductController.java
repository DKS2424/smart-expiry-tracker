package com.example.smartexpirytracker.controller;

import com.example.smartexpirytracker.dto.request.ProductRequestDTO;
import com.example.smartexpirytracker.dto.response.ProductResponseDTO;
import com.example.smartexpirytracker.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDTO addProduct(@Valid @RequestBody ProductRequestDTO request) {
        return productService.addProduct(request);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{category}")
    public List<ProductResponseDTO> searchByCategory(@PathVariable String category) {
        return productService.searchByCategory(category);
    }

    @GetMapping("/expiring")
    public List<ProductResponseDTO> expiringProducts() {
        return productService.expiringProducts();
    }

    @GetMapping("/low-stock")
    public List<ProductResponseDTO> lowStockProducts() {
        return productService.lowStockProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDTO updateProduct(@PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully.";
    }
}