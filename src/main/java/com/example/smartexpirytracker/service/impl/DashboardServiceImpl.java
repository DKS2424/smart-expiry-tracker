package com.example.smartexpirytracker.service.impl;

import com.example.smartexpirytracker.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Override
    public String getDashboardData() {
        return "Dashboard working";
    }
}