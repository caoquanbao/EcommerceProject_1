-- ==============================
-- 1. Tạo database
-- ==============================
DROP DATABASE IF EXISTS ecommerce_demo;
CREATE DATABASE ecommerce_demo;
USE ecommerce_demo;

-- ==============================
-- 2. Bảng users
-- ==============================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    role ENUM('buyer', 'seller'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thêm dữ liệu demo (password hash của "123456")
INSERT INTO users (name, email, password, role) VALUES
('Nguyen Van A', 'a@gmail.com', '$2b$10$wRZr1D8EdWbJxK0G/jV3P.WuCffmMl4uQ7osXtjJNns0YkJp9ZTz6', 'buyer'),
('Le Thi B', 'b@gmail.com', '$2b$10$wRZr1D8EdWbJxK0G/jV3P.WuCffmMl4uQ7osXtjJNns0YkJp9ZTz6', 'buyer'),
('Tran Thi C', 'c@gmail.com', '$2b$10$wRZr1D8EdWbJxK0G/jV3P.WuCffmMl4uQ7osXtjJNns0YkJp9ZTz6', 'seller');

-- ==============================
-- 3. Bảng products (thêm category)
-- ==============================
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seller_id INT,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL(10,2),
    stock INT,
    category ENUM('food','drink','snack') DEFAULT 'food',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_id) REFERENCES users(id)
);

-- Thêm dữ liệu demo (foods)
INSERT INTO products (seller_id, name, description, price, stock, category) VALUES
(3, 'Apple', 'Táo tươi nhập khẩu', 2.50, 50, 'food'),
(3, 'Banana', 'Chuối vàng', 1.20, 100, 'food'),
(3, 'Orange Juice', 'Nước cam tươi', 3.00, 30, 'drink'),
(3, 'Potato Chips', 'Snack khoai tây', 1.50, 40, 'snack'),
(3, 'Milk', 'Sữa tươi tiệt trùng', 2.00, 60, 'drink');

-- ==============================
-- 4. Bảng orders
-- ==============================
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    buyer_id INT,
    total_price DECIMAL(10,2),
    status ENUM('pending','completed','cancelled') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (buyer_id) REFERENCES users(id)
);

-- ==============================
-- 5. Bảng order_items
-- ==============================
CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Thêm dữ liệu demo cho orders
INSERT INTO orders (buyer_id, total_price, status) VALUES
(1, 6.50, 'completed'),
(2, 4.50, 'pending');

-- Thêm order_items tương ứng
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 2.50),  -- Nguyen Van A mua 2 Apple
(1, 4, 1, 1.50),  -- + 1 Potato Chips
(2, 2, 2, 1.20),  -- Le Thi B mua 2 Banana
(2, 5, 1, 2.00);  -- + 1 Milk
