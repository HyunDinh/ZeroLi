///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package startup.zeroli.controller.redirectController;
//
//import jakarta.servlet.RequestDispatcher;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.util.List;
//import java.util.Map;
//import startup.zeroli.config.ProjectPaths;
//import startup.zeroli.controller.mainController.MainControllerServlet;
//import startup.zeroli.model.Product;
//import startup.zeroli.model.ProductReview;
//import startup.zeroli.service.product.ProductServiceImpl;
//import startup.zeroli.service.product.ProductService;
//import startup.zeroli.service.productView.ProductReviewService;
//import startup.zeroli.service.productView.ProductReviewServiceImpl;
//import startup.zeroli.utils.ErrDialog;
//
///**
// *
// * @author Admin
// */
//@WebServlet(name = "ProductPageServlet", urlPatterns = {MainControllerServlet.PRODUCTPAGE_SERVLET})
//public class ProductBackUpServlet extends HttpServlet {
//
//    private ProductService productServiceImpl;
//    private ProductReviewService productReviewServiceImpl;
//
//    public void init() {
//        productServiceImpl = new ProductServiceImpl();
//        productReviewServiceImpl = new ProductReviewServiceImpl();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        ErrDialog.showError("action prod  " + action);
//        String productId = request.getParameter("id");
//        ErrDialog.showError("id truyen  " + productId);
//
//        if (action == null) {
//            action = "";
//        }
//
//        if (productId != null && !productId.isEmpty()) {
//            showProductDetail(request, response);
//        } else {
//            showListProduct(request, response);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        //String productId = request.getParameter("id");
//        ErrDialog.showError(action);
//        HttpSession session = request.getSession();
//        Integer productId = (Integer) session.getAttribute("productId");
//
////        ErrDialog.showError("id gửi dến productPage " + request.getParameter("id"));//nulll
//        ErrDialog.showError("id gửi dến productPage " + productId);//nulll
//        if (action == null) {
//            action = "";
//        }
//
//        if (MainControllerServlet.ACTION_ADDREVIEW.equals(action)) {
//            addProductReview(request, response);
//        } else {
//            showProductDetail(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc=" functional ... ">
//    private void showListProduct(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        Map<Integer, Product> productMap = productServiceImpl.getAllProducts();
//        request.setAttribute("productMap", productMap);
//        request.getRequestDispatcher(ProjectPaths.JSP_PRODUCTPAGE_PATH).forward(request, response);
//    }
//
//    private void showProductDetail(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String idParam = request.getParameter("id");
//        if (idParam == null || idParam.isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id của product");
//            return;
//        }
//        int id;
//        try {
//            id = Integer.parseInt(idParam);
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
//            return;
//        }
//        Product product = productServiceImpl.getProductById(id);
//        List<ProductReview> reviews = productReviewServiceImpl.getReviewsByProductId(id);
////        ErrDialog.showError("reviews : " + reviews.size());
//        if (product == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy product");
//            return;
//        }
//        request.setAttribute("product", product);
//        request.setAttribute("reviews", reviews);
////        ErrDialog.showError("" + reviews.get(0));
//        request.getRequestDispatcher(ProjectPaths.JSP_PRODUCTDETAILSPAGE_PATH).forward(request, response);
//    }
//
//    private void addProductReview(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int productId = Integer.parseInt(request.getParameter("productId"));
//        String userName = request.getParameter("userName");
//        int rating = Integer.parseInt(request.getParameter("rating"));
//        String comment = request.getParameter("comment");
//
//        ProductReview productReview = new ProductReview(productId, userName, rating, comment);
//        
//        HttpSession session = request.getSession();
//        Integer productIdS = (Integer) session.getAttribute("productId");
//        
//        ErrDialog.showError("add Pro: " + productIdS);
//        
//        request.setAttribute("id", productIdS);
//        request.getRequestDispatcher(ProjectPaths.HREF_TO_PRODUCTPAGE + "&id=" + productIdS).forward(request, response);
//                
//        
//    }
//
//    // </editor-fold>
//}
