package startup.zeroli.controller.redirectController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;
import startup.zeroli.config.AbsolutePaths;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.Product;
import startup.zeroli.model.ProductReview;
import startup.zeroli.model.User;
import startup.zeroli.service.product.ProductServiceImpl;
import startup.zeroli.service.product.ProductService;
import startup.zeroli.service.productView.ProductReviewService;
import startup.zeroli.service.productView.ProductReviewServiceImpl;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductPageServlet", urlPatterns = {MainControllerServlet.PRODUCTPAGE_SERVLET})
public class ProductPageServlet extends HttpServlet {

    private ProductService productServiceImpl;
    private ProductReviewService productReviewServiceImpl;

    public void init() {
        productServiceImpl = new ProductServiceImpl();
        productReviewServiceImpl = new ProductReviewServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String productId = request.getParameter("id");
        if (action == null) {
            action = "";
        }

        if (MainControllerServlet.ACTION_SEARCHPRODUCT.equals(action)) {
            searchProduct(request, response);
        }

        if (productId != null && !productId.isEmpty()) {
            showProductDetail(request, response);
        } else {
            showListProduct(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case MainControllerServlet.ACTION_ADDREVIEW ->
                addProductReview(request, response);
            case MainControllerServlet.ACTION_UPDATEREVIEW ->
                updateProductReview(request, response);
            case MainControllerServlet.ACTION_DELETEREVIEW ->
                deleteProductReview(request, response);
            default ->
                showProductDetail(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" functional ... ">
    private void showListProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Integer, Product> productMap = productServiceImpl.getAllProducts();
        request.setAttribute("productMap", productMap);
        request.getRequestDispatcher(ProjectPaths.JSP_PRODUCTPAGE_PATH).forward(request, response);
    }

    private void showProductDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id của product");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }

        Product product = productServiceImpl.getProductById(id);
        List<ProductReview> reviews = productReviewServiceImpl.getReviewsByProductId(id);

        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy product");
            return;
        }

        // Xử lý avatar cho từng review
        for (ProductReview review : reviews) {
            String email = review.getUser().getEmail();
            String safeEmailKey = email.replace("@", "_at_");
            String avatarPath = AbsolutePaths.AVATAR_DIR + email + ".png";
            File avatarFile = new File(avatarPath);

            request.setAttribute("avatarExists_" + safeEmailKey, avatarFile.exists());
            request.setAttribute("lastModified_" + safeEmailKey,
                    avatarFile.exists() ? avatarFile.lastModified() : System.currentTimeMillis());
        }

        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);
        request.getRequestDispatcher(ProjectPaths.JSP_PRODUCTDETAILSPAGE_PATH).forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("name");
        Map<Integer, Product> productMap;
        if (searchName == null || searchName.isEmpty()) {
            productMap = productServiceImpl.getAllProducts();
        } else {
            productMap = productServiceImpl.searchProductsByName(searchName);
        }

        request.setAttribute("productMap", productMap);
        request.getRequestDispatcher(ProjectPaths.JSP_PRODUCTPAGE_PATH).forward(request, response);
    }

    private void addProductReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute(MainControllerServlet.SS_ATTR_USER);
        int productId = Integer.parseInt(request.getParameter("productId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        ProductReview productReview = new ProductReview(productId, u, rating, comment);
        productReviewServiceImpl.addProductReview(productReview);
        response.sendRedirect(ProjectPaths.HREF_TO_PRODUCTPAGE + "&id=" + productId);
    }

    private void updateProductReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int viewId = Integer.parseInt(request.getParameter("viewId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String newContent = request.getParameter("newContent");

            // Cập nhật vào database
            productReviewServiceImpl.updateProductReview(viewId, rating, newContent);

            response.sendRedirect(ProjectPaths.HREF_TO_PRODUCTPAGE + "&id=" + productId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu không hợp lệ");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    private void deleteProductReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int viewId = Integer.parseInt(request.getParameter("viewId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        productReviewServiceImpl.deleteProductReview(viewId);

        response.sendRedirect(ProjectPaths.HREF_TO_PRODUCTPAGE + "&id=" + productId);
    }

    // </editor-fold>
}
