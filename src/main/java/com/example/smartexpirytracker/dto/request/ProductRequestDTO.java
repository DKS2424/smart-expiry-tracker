package com.example.smartexpirytracker.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductRequestDTO {

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