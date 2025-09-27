package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm user theo email (để login và validate JWT)
    Optional<User> findByEmail(String email);

    // Kiểm tra trùng email khi đăng ký
    boolean existsByEmail(String email);
}
