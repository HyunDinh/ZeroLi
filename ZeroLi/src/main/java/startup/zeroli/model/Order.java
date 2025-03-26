/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import startup.zeroli.common.OrderStatus;

/**
 *
 * @author Admin
 */
public class Order {

    private int orderId;
    private int productId;
    private int userId;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(int orderId, int productId, int userId, BigDecimal totalPrice, OrderStatus status, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
    }
    
    public Order(int productId, int userId, BigDecimal totalPrice, OrderStatus status, LocalDateTime orderDate) {
        this.productId = productId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order(int orderId, int productId, int userId, BigDecimal totalPrice, OrderStatus status) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(int productId, int userId, BigDecimal totalPrice, OrderStatus status) {
        this.productId = productId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", productId=" + productId + ", userId=" + userId + ", totalPrice=" + totalPrice + ", status=" + status + ", orderDate=" + orderDate + '}';
    }
    
}
