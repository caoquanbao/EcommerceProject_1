package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Status;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Tạo đơn hàng: client gửi buyerId, productId, quantity
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestParam Long buyerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        Order order = orderService.createOrder(buyerId, productId, quantity);
        return ResponseEntity.ok(order);
    }

    // Lấy tất cả đơn của 1 user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // Cập nhật trạng thái đơn hàng
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam Status status) {
        Order updated = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updated);
    }
}
