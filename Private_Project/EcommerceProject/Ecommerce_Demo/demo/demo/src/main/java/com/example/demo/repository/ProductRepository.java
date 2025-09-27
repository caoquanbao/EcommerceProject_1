package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Lấy sản phẩm theo seller
    List<Product> findBySellerId(Long sellerId);

    // Lấy sản phẩm theo category
    List<Product> findByCategory(ProductCategory category);
}
