package com.example.smartexpirytracker.service;

import com.example.smartexpirytracker.dto.request.ProductRequestDTO;
import com.example.smartexpirytracker.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO addProduct(ProductRequestDTO request);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO request);

    void deleteProduct(Long id);

    List<ProductResponseDTO> searchByCategory(String category);

    List<ProductResponseDTO> expiringProducts();

    List<ProductResponseDTO> lowStockProducts();

}