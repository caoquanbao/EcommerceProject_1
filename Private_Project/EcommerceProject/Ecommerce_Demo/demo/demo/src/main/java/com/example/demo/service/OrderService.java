package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Tạo order mới
    public Order createOrder(Long buyerId, Long productId, Integer quantity) {
        // Tìm buyer
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        // Tìm product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra stock
        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        // Giảm stock
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // Tạo order
        Order order = new Order();
        order.setBuyer(buyer);
        order.setSeller(product.getSeller()); // product phải có seller
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);

        return orderRepository.save(order);
    }

    // Lấy order theo buyer
    public List<Order> getOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyer_Id(buyerId);
    }

    // Lấy order theo seller
    public List<Order> getOrdersBySeller(Long sellerId) {
        return orderRepository.findBySeller_Id(sellerId);
    }

    // Lấy tất cả order
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Tìm order theo ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}

public List<Order> getOrdersByUser(Long userId) {
    return orderRepository.findByBuyerId(userId);
}

public Order updateOrderStatus(Long orderId, Status status) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    order.setStatus(status);
    return orderRepository.save(order);
}
