/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.CartItem;
import startup.zeroli.model.Product;
import startup.zeroli.service.cart.CartService;
import startup.zeroli.service.cart.CartServiceImpl;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutPageServlet", urlPatterns = {MainControllerServlet.CHECKOUTPAGE_SERVLET})
public class CheckoutPageServlet extends HttpServlet {

    private CartService cartService = new CartServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        ErrDialog.showError("userid: " + userId);
        if (userId == null) {
            userId = 0;
            session.setAttribute("userId", userId);
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        Map<Product, Integer> cartItems = (Map<Product, Integer>) session.getAttribute("cartItems");
        ErrDialog.showError("size in checkout: " + cartItems);
        request.setAttribute("cart", cartItems);
        request.getRequestDispatcher(ProjectPaths.JSP_CHECKOUTPAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            userId = 0;
            session.setAttribute("userId", userId);
        }

        String action = request.getParameter("action");

        if (MainControllerServlet.ACTION_CHECKOUT.equals(action)) {
            String[] productIds = request.getParameterValues("productId");
            String[] quantities = request.getParameterValues("quantity");

            if (productIds == null || quantities == null) {
                ErrDialog.showError("Không có sản phẩm nào được chọn để thanh toán!");
                response.sendRedirect(ProjectPaths.HREF_TO_CARTPAGE);
                return;
            }

            for (int i = 0; i < productIds.length; i++) {
                int productId = Integer.parseInt(productIds[i]);
                int quantity = Integer.parseInt(quantities[i]);

                Product productToRemove = new Product();
                productToRemove.setProductId(productId);
                CartItem cartItemToRemove = new CartItem(productToRemove, quantity);

                cartService.removeItemFromCart(userId, cartItemToRemove);
            }

            Map<Product, Integer> cartItems = cartService.getCartItems(userId);
            session.setAttribute("cartItems", cartItems);

            BigDecimal totalPrice = cartService.getTotalPrice(userId);
            session.setAttribute("totalPrice", totalPrice);

            session.removeAttribute("selectedProductIds");

            response.sendRedirect(ProjectPaths.HREF_TO_PRODUCTPAGE);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
