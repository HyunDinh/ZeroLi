/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.model;

import startup.zeroli.common.OrderDetailStatus;

/**
 *
 * @author Admin
 */
public class OrderDetail {

    private int id;
    private int userId;
    private int orderId;
    private int productId;
    private int quantity;
    private String fullName;
    private String phoneNumber;
    private String cityProvince;
    private String district;
    private String ward;
    private String street;
    private String orderNote;
    private String paymentMethod;
    private OrderDetailStatus status;

    public OrderDetail() {
    }

    public OrderDetail(int id, int userId, int orderId, int productId, int quantity, String fullName, String phoneNumber, String cityProvince, String district, String ward, String street, String orderNote, String paymentMethod, OrderDetailStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.cityProvince = cityProvince;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this.orderNote = orderNote;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityProvince() {
        return cityProvince;
    }

    public void setCityProvince(String cityProvince) {
        this.cityProvince = cityProvince;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderDetailStatus getStatus() {
        return status;
    }

    public void setStatus(OrderDetailStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", cityProvince=" + cityProvince + ", district=" + district + ", ward=" + ward + ", street=" + street + ", orderNote=" + orderNote + ", paymentMethod=" + paymentMethod + ", status=" + status + '}';
    }

}
