package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;

@RestController
public class BuyerController {

    // API dành cho BUYER
    @GetMapping("/api/buyer/profile")
    public String getBuyerProfile(@AuthenticationPrincipal User user) {
        return "Hello BUYER: " + user.getEmail();
    }
}
