package com.example.demo.repository;

import com.example.demo.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Lấy đơn hàng theo buyer
    List<Order> findByBuyer_Id(Long buyerId);

    // Lấy đơn hàng theo seller
    List<Order> findBySeller_Id(Long sellerId);

    // Thêm method này nếu muốn service gọi findByBuyerId()
    default List<Order> findByBuyerId(Long buyerId) {
        return findByBuyer_Id(buyerId);
    }
}
