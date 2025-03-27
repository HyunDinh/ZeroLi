package startup.zeroli.service.cart;

import startup.zeroli.model.CartItem;
import startup.zeroli.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {

    // Lưu trữ giỏ hàng của tất cả người dùng: userId -> List<CartItem>
    private Map<Integer, List<CartItem>> cartStorage = new HashMap<>();

    @Override
    public void addItemToCart(int userId, CartItem cartItem) {
        List<CartItem> userCart = cartStorage.computeIfAbsent(userId, k -> new ArrayList<>());
        boolean found = false;
        for (CartItem item : userCart) {
            if (item.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            userCart.add(cartItem);
        }
    }

    @Override
    public void updateItemInCart(int userId, CartItem cartItem) {
        List<CartItem> userCart = cartStorage.get(userId);
        if (userCart != null) {
            for (CartItem item : userCart) {
                if (item.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
                    item.setQuantity(cartItem.getQuantity());
                    break;
                }
            }
        }
    }

    @Override
    public void removeItemFromCart(int userId, CartItem cartItem) {
        List<CartItem> userCart = cartStorage.get(userId);
        if (userCart != null) {
            userCart.removeIf(item
                    -> item.getProduct().getProductId() == cartItem.getProduct().getProductId()
            );
            if (userCart.isEmpty()) {
                cartStorage.remove(userId);
            }
        }
    }

    @Override
    public Map<Product, Integer> getCartItems(int userId) {
        List<CartItem> userCart = cartStorage.getOrDefault(userId, new ArrayList<>());
        Map<Product, Integer> cartItems = new HashMap<>();
        for (CartItem item : userCart) {
            cartItems.put(item.getProduct(), item.getQuantity());
        }
        return cartItems;
    }

    @Override
    public BigDecimal getTotalPrice(int userId) {
        List<CartItem> userCart = cartStorage.getOrDefault(userId, new ArrayList<>());
        if (userCart.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return userCart.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean isCartEmpty(int userId) {
        List<CartItem> userCart = cartStorage.get(userId);
        return userCart == null || userCart.isEmpty();
    }

    @Override
    public void clearCart(int userId) {
        cartStorage.remove(userId);
    }
}