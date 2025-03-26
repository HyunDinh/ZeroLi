/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.controller.redirectController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.CartItem;
import startup.zeroli.model.Product;
import startup.zeroli.service.cart.CartService;
import startup.zeroli.service.cart.CartServiceImpl;
import startup.zeroli.service.product.ProductService;
import startup.zeroli.service.product.ProductServiceImpl;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartPageServlet", urlPatterns = {MainControllerServlet.CARTPAGE_SERVLET})
public class CartPageServlet extends HttpServlet {

    private CartService cartService = new CartServiceImpl();

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
//        ErrDialog.showError("userid: " + userId);
        if (userId == null) {
            userId = 0;
            session.setAttribute("userId", userId);
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        displayCart(request, response);
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
//        ErrDialog.showError("Cart: " + action);

        if (action == null) {
            action = "";
        }

        switch (action) {
            case MainControllerServlet.ACTION_ADDITEMS:
                addToCart(request, response);
                break;
            case MainControllerServlet.ACTION_INCREASE_QUANTITY:
                increaseQuantity(request, response);
                break;
            case MainControllerServlet.ACTION_DECREASE_QUANTITY:
                decreaseQuantity(request, response);
                break;
            case MainControllerServlet.ACTION_APPLY_VOUCHER:
                applyVoucher(request, response);
                break;
            case MainControllerServlet.ACTION_UPDATE_SELECTION:
                updateSelection(request, response);
                break;
            default:
                displayCart(request, response);
                break;
        }
    }

    private void updateSelection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String selectedProductIds = request.getParameter("selectedProductIds");
        List<Integer> selectedIds = new ArrayList<>();

        if (selectedProductIds != null && !selectedProductIds.isEmpty()) {
            String[] ids = selectedProductIds.split(",");
            for (String id : ids) {
                try {
                    selectedIds.add(Integer.valueOf(id));
                } catch (NumberFormatException e) {
                    ErrDialog.showError("Invalid product ID: " + id);
                }
            }
        }
        session.setAttribute("selectedProductIds", selectedIds);
        response.sendRedirect(ProjectPaths.HREF_TO_CARTPAGE);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");
        String name = request.getParameter("productName");
        String priceParam = request.getParameter("price");
        String imageURL = request.getParameter("imageURL");

        if (productIdParam == null || name == null || priceParam == null || imageURL == null) {
            ErrDialog.showError("Thiếu id || name || price || url");
            return;
        }

        try {
            int productId = Integer.parseInt(productIdParam);
            BigDecimal price = new BigDecimal(priceParam);
            int quantity = 1;

            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(name);
            product.setPrice(price);
            product.setImageURL(imageURL);

            CartItem cartItem = new CartItem(product, quantity);

            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                userId = 0;
            }

            cartService.addItemToCart(userId, cartItem);
//            cartService.getCartItems(0);

            BigDecimal totalPrice = cartService.getTotalPrice(userId);
            session.setAttribute("totalPrice", totalPrice);

            response.sendRedirect(ProjectPaths.HREF_TO_PRODUCTPAGE);
        } catch (NumberFormatException e) {
            ErrDialog.showError("Parse kh được!!!");
        }
    }

    private void increaseQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");
//        ErrDialog.showError("id cart: " + productIdParam);
        if (productIdParam == null) {
            ErrDialog.showError("id null");
            displayCart(request, response);
            return;
        }

        try {
            int productId = Integer.parseInt(productIdParam);
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            Map<Product, Integer> cartItems = cartService.getCartItems(userId);
//            ErrDialog.showError("size cart page in" + cartItems.size());
            Product product = null;
            int currentQuantity = 0;

            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                if (entry.getKey().getProductId() == productId) {
                    product = entry.getKey();
//                    ErrDialog.showError("product cart" + targetProduct);
                    currentQuantity = entry.getValue();
//                    ErrDialog.showError("quantity" + currentQuantity);

                    break;
                }
            }

            if (product != null) {
                CartItem cartItem = new CartItem(product, currentQuantity + 1);
                cartService.updateItemInCart(userId, cartItem);
            }

            BigDecimal totalPrice = cartService.getTotalPrice(userId);
            session.setAttribute("totalPrice", totalPrice);

            response.sendRedirect(ProjectPaths.HREF_TO_CARTPAGE);
        } catch (NumberFormatException e) {
            ErrDialog.showFullError("Lỗi thiếu id || parse number");
        }
    }

    private void decreaseQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");

        if (productIdParam == null) {
            ErrDialog.showError("id null");
            displayCart(request, response);
            return;
        }

        try {
            int productId = Integer.parseInt(productIdParam);
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            Map<Product, Integer> cartItems = cartService.getCartItems(userId);
//            ErrDialog.showError("size cart page in" + cartItems.size());

            Product product = null;
            int currentQuantity = 0;

            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                if (entry.getKey().getProductId() == productId) {
                    product = entry.getKey();
                    currentQuantity = entry.getValue();
                    break;
                }
            }

            if (product != null) {
                if (currentQuantity > 1) {
                    CartItem cartItem = new CartItem(product, currentQuantity - 1);
                    cartService.updateItemInCart(userId, cartItem);
                } else {
                    CartItem cartItem = new CartItem(product, 0);
                    cartService.removeItemFromCart(userId, cartItem);
                }
            }

            BigDecimal totalPrice = cartService.getTotalPrice(userId);
            session.setAttribute("totalPrice", totalPrice);

            response.sendRedirect(ProjectPaths.HREF_TO_CARTPAGE);
        } catch (NumberFormatException e) {
            ErrDialog.showFullError("Lỗi thiếu id || parse number");
        }
    }

    private void applyVoucher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String voucherText = request.getParameter("voucherText");

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Lấy danh sách voucher đã áp dụng từ session
        Map<String, String> appliedVouchers = (Map<String, String>) session.getAttribute("appliedVouchers");
        if (appliedVouchers == null) {
            appliedVouchers = new HashMap<>();
        }

        // Lưu hoặc xóa voucher đã áp dụng
        if (voucherText != null && !voucherText.isEmpty()) {
            appliedVouchers.put(productName, voucherText);
        } else {
            appliedVouchers.remove(productName);
        }
        session.setAttribute("appliedVouchers", appliedVouchers);

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
//        ErrDialog.showError("display cart id: " + userId);
        Map<Product, Integer> cartItems = cartService.getCartItems(userId);

//        ErrDialog.showFullError("size cartDisplay: " + cartItems);
        request.setAttribute("userId", userId);
        session.setAttribute("userId", userId);
        session.setAttribute("cartItems", cartItems);
        request.setAttribute("cart", cartItems);

        BigDecimal totalPrice = cartService.getTotalPrice(userId);
        session.setAttribute("totalPrice", totalPrice);

        request.getRequestDispatcher(ProjectPaths.JSP_CARTPAGE_PATH).forward(request, response);
    }

    private String determineVoucher(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10000000")) >= 0 && price.compareTo(new BigDecimal("19999999")) <= 0) {
            return "Giảm 3% cho đơn hàng từ 10.000.000 VNĐ đến 19.999.999 VNĐ";
        } else if (price.compareTo(new BigDecimal("20000000")) >= 0 && price.compareTo(new BigDecimal("29999999")) <= 0) {
            return "Giảm 5% cho đơn hàng từ 20.000.000 VNĐ đến 29.999.999 VNĐ";
        } else if (price.compareTo(new BigDecimal("30000000")) >= 0 && price.compareTo(new BigDecimal("49999999")) <= 0) {
            return "Giảm 7% cho đơn hàng từ 30.000.000 VNĐ đến 49.999.999 VNĐ";
        } else if (price.compareTo(new BigDecimal("50000000")) > 0) {
            return "Giảm 10% cho đơn hàng trên 50.000.000 VNĐ";
        } else {
            return "Không có voucher cho sản phẩm này";
        }
    }

    private BigDecimal calculateDiscount(BigDecimal itemTotal, String voucherText) {
        if (voucherText.contains("3%")) {
            return itemTotal.multiply(new BigDecimal("0.03"));
        } else if (voucherText.contains("5%")) {
            return itemTotal.multiply(new BigDecimal("0.05"));
        } else if (voucherText.contains("7%")) {
            return itemTotal.multiply(new BigDecimal("0.07"));
        } else if (voucherText.contains("10%")) {
            return itemTotal.multiply(new BigDecimal("0.10"));
        }
        return BigDecimal.ZERO;
    }
}
