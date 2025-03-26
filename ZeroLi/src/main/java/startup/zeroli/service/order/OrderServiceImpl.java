///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package startup.zeroli.service.order;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import startup.zeroli.model.Cart;
//import startup.zeroli.model.Order;
//import startup.zeroli.service.cart.CartService;
//
///**
// *
// * @author Admin
// */
//public class OrderServiceImpl implements OrderService {
//    private Map<Integer, OrderService> orderStorage = new HashMap<>();
//    private Long orderIdCounter = 0L;
//    private CartService cartService; 
//
//    public OrderServiceImpl(CartService cartService) {
//        this.cartService = cartService;
//    }
//
//    @Override
//    public Order createOrderFromCart(int cartId) {
//        Cart cart = cartService.getCart(cartId);
//        if (cart == null) {
//            return null;
//        }
//
//        Order order = new Order();
//        order.setOrderId(++orderIdCounter);
//        order.setItems(new ArrayList<>(cart.getItems()));
//        order.setTotal(cart.getTotal);
//        order.setStatus("PENDING");
//        order.setOrderDate(new Date());
//        
//        orderStorage.put(order.getId(), order);
//        cartService.clearCart(cartId); // Xóa giỏ hàng sau khi tạo đơn
//        
//        return order;
//    }
//
//    @Override
//    public Order getOrder(int orderId) {
//        return orderStorage.get(orderId);
//    }
//
//    @Override
//    public void updateOrderStatus(int orderId, String status) {
//        Order order = orderStorage.get(orderId);
//        if (order != null) {
//            order.setStatus(status);
//        }
//    }
//
//    @Override
//    public List<Order> getAllOrders() {
//        return new ArrayList<>(orderStorage.values());
//    }
//    
//}
