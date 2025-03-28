package startup.zeroli.controller.redirectController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.SaleBook;
import startup.zeroli.service.saleBook.SaleBookService;
import startup.zeroli.service.saleBook.SaleBookServiceImpl;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "SaleBookPageServlet", urlPatterns = {MainControllerServlet.SALEBOOKPAGE_SERVLET})
public class SaleBookPageServlet extends HttpServlet {

    private SaleBookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = new SaleBookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số nhà cung cấp từ request
        String supplier = request.getParameter("supplier");

        // Lấy danh sách tất cả nhà cung cấp
        List<String> suppliers = bookService.getAllSuppliers();

        // Lấy danh sách sách theo nhà cung cấp (hoặc tất cả nếu không chọn)
        List<SaleBook> books;
        if (supplier != null && !supplier.isEmpty()) {
            books = bookService.getBooksBySupplier(supplier);
        } else {
            books = bookService.getAllSaleBooks();
        }

        // Set các thuộc tính vào request để truyền sang JSP
        request.setAttribute("suppliers", suppliers);
        request.setAttribute("books", books);
        request.setAttribute("selectedSupplier", supplier);

        // Forward request đến JSP
        request.getRequestDispatcher(ProjectPaths.JSP_SALEBOOKPAGE_PATH).forward(request, response);
    }

}
