package com.example.demo.repository;

import com.example.demo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Lấy tất cả item của 1 order
    List<OrderItem> findByOrderId(Long orderId);

    // Lấy tất cả item của 1 product (dùng khi seller muốn check)
    List<OrderItem> findByProductId(Long productId);
}
