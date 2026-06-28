package com.example.smartexpirytracker.service.impl;

import com.example.smartexpirytracker.dto.request.ProductRequestDTO;
import com.example.smartexpirytracker.dto.response.ProductResponseDTO;
import com.example.smartexpirytracker.entity.Product;
import com.example.smartexpirytracker.repository.ProductRepository;
import com.example.smartexpirytracker.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO request) {

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setBatchNumber(request.getBatchNumber());
        product.setManufactureDate(request.getManufactureDate());
        product.setExpiryDate(request.getExpiryDate());
        product.setQuantity(request.getQuantity());
        product.setMinimumStock(request.getMinimumStock());
        product.setSupplierName(request.getSupplierName());
        product.setPrice(request.getPrice());

        return map(productRepository.save(product));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {

        return map(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setBatchNumber(request.getBatchNumber());
        product.setManufactureDate(request.getManufactureDate());
        product.setExpiryDate(request.getExpiryDate());
        product.setQuantity(request.getQuantity());
        product.setMinimumStock(request.getMinimumStock());
        product.setSupplierName(request.getSupplierName());
        product.setPrice(request.getPrice());

        return map(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> searchByCategory(String category) {

        return productRepository.findByCategoryContainingIgnoreCase(category)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> expiringProducts() {

        return productRepository.findByExpiryDateBetween(
                        LocalDate.now(),
                        LocalDate.now().plusDays(7))
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> lowStockProducts() {

        return productRepository.findByQuantityLessThan(10)
                .stream()
                .map(this::map)
                .toList();
    }

    private ProductResponseDTO map(Product product) {
    ProductResponseDTO dto = new ProductResponseDTO();
    dto.setId(product.getId());
    dto.setProductName(product.getProductName());
    dto.setCategory(product.getCategory());
    dto.setBatchNumber(product.getBatchNumber());
    dto.setManufactureDate(product.getManufactureDate());
    dto.setExpiryDate(product.getExpiryDate());
    dto.setQuantity(product.getQuantity());
    dto.setMinimumStock(product.getMinimumStock());
    dto.setSupplierName(product.getSupplierName());
    dto.setPrice(product.getPrice());
    return dto;
}

}