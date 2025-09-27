package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Người mua
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    // Người bán (lấy từ product.seller)
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    // Sản phẩm
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING; // mặc định
}
