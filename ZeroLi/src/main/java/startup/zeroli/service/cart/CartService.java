package startup.zeroli.service.cart;

import startup.zeroli.model.Product;
import startup.zeroli.model.CartItem;
import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    void addItemToCart(int userId, CartItem cartItem);

    void updateItemInCart(int userId, CartItem cartItem);

    void removeItemFromCart(int userId, CartItem cartItem);

    Map<Product, Integer> getCartItems(int userId); 

    BigDecimal getTotalPrice(int userId);

    boolean isCartEmpty(int userId);

    void clearCart(int userId);
}
