/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package startup.zeroli.controller.redirectController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.SaleBook;
import startup.zeroli.service.saleBook.SaleBookServiceImpl;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "SaleBookDetailServlet", urlPatterns = {MainControllerServlet.SALEBOOKDETAILPAGE_SERVLET})
public class SaleBookDetailServlet extends HttpServlet {

    private SaleBookServiceImpl saleBookService;
    
    public SaleBookDetailServlet(){
        saleBookService = new SaleBookServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Lấy ID sách từ request parameter
            String bookIdParam = request.getParameter("id");

            if (bookIdParam == null || bookIdParam.isEmpty()) {
                // Nếu không có ID, chuyển hướng về trang danh sách
                response.sendRedirect("main?action=search-booksupplier-products");
                return;
            }

            int bookId = Integer.parseInt(bookIdParam);

            // Lấy thông tin sách từ service
            SaleBook book = saleBookService.getBookById(bookId);

            if (book == null) {
                // Nếu không tìm thấy sách, hiển thị thông báo lỗi
                request.setAttribute("errorMessage", "Không tìm thấy sách với ID: " + bookId);
                request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
                return;
            }

            // Đặt thông tin sách vào request attribute
            request.setAttribute("book", book);

            // Forward đến trang chi tiết
            request.getRequestDispatcher(ProjectPaths.JSP_SALEBOOKDETAILPAGE_PATH).forward(request, response);

        } catch (NumberFormatException e) {
            // Xử lý khi ID không phải số
            request.setAttribute("errorMessage", "ID sách không hợp lệ");
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Xử lý các lỗi khác
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }
}
