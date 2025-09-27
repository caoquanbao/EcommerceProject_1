package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;

@RestController
public class AdminController {

    // API dành cho ADMIN
    @GetMapping("/api/admin/dashboard")
    public String getAdminDashboard(@AuthenticationPrincipal User user) {
        return "Hello ADMIN: " + user.getEmail() + ". You have full access!";
    }
}
