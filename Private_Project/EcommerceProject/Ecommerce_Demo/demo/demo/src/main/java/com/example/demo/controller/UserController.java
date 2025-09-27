package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;

@RestController
public class UserController {

    // API dành cho USER
    @GetMapping("/api/user/profile")
    public String getUserProfile(@AuthenticationPrincipal User user) {
        return "Hello USER: " + user.getEmail();
    }
}

