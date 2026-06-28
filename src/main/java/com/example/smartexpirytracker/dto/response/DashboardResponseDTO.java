package com.example.smartexpirytracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {

    private Long totalProducts;

    private Long expiredProducts;

    private Long expiringSoonProducts;

    private Long lowStockProducts;

}