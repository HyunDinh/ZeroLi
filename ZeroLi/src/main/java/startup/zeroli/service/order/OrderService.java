/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.order;

import java.util.List;
import startup.zeroli.common.OrderStatus;
import startup.zeroli.model.Order;

/**
 *
 * @author Admin
 */
public interface OrderService {

    Order createOrderFromCart(int cartId);

    Order getOrder(int orderId);

    void updateOrderStatus(int orderId, OrderStatus status);

    List<Order> getAllOrders();
}
