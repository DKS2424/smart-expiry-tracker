package com.example.smartexpirytracker.repository;

import com.example.smartexpirytracker.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByCategoryContainingIgnoreCase(String category);

    List<Product> findBySupplierNameContainingIgnoreCase(String supplierName);

    List<Product> findByExpiryDateBefore(LocalDate date);

    List<Product> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);

    List<Product> findByQuantityLessThan(Integer quantity);

}