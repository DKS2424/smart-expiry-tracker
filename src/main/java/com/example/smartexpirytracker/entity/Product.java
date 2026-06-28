package com.example.smartexpirytracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String category;
    private String batchNumber;

    private LocalDate manufactureDate;
    private LocalDate expiryDate;

    private Integer quantity;
    private Integer minimumStock;

    private String supplierName;
    private Double price;
}