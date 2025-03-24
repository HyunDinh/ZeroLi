package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.Book;
import startup.zeroli.service.book.BookService;
import startup.zeroli.service.book.BookServiceImpl;
import startup.zeroli.utils.ErrDialog;

@WebServlet(name = "BookPageServlet", urlPatterns = {MainControllerServlet.BOOKPAGE_SERVLET})
public class BookPageServlet extends HttpServlet {

    private BookService bookService;
    
    public BookPageServlet(){
        bookService = new BookServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case MainControllerServlet.BOOKPAGE_REDIRECT -> showBooksByCategory(request, response);
            case MainControllerServlet.ACTION_VIEW_BOOKDETAIL -> showBooksDetail(request, response);
            case MainControllerServlet.ACTION_FAVORITE_BOOK -> addFavoriteBook(request, response);
            default ->
                response.sendRedirect("errorAtBookPageServlet.jsp");
        }
    }
    
    private void addFavoriteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookIdParam = req.getParameter("bookId");
        if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
            System.out.println("bookIdParam is null or empty in addFavoriteBook");
            return; // Không làm gì nếu bookId không hợp lệ
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdParam);
        } catch (NumberFormatException e) {
            System.out.println("Invalid bookId in addFavoriteBook: " + bookIdParam);
            return;
        }

        HttpSession session = req.getSession();
        List<Integer> favoriteBooks = (List<Integer>) session.getAttribute("favoriteBooks");
        if (favoriteBooks == null) {
            favoriteBooks = new ArrayList<>();
        }

        if (!favoriteBooks.contains(bookId)) {
            favoriteBooks.add(bookId);
            session.setAttribute("favoriteBooks", favoriteBooks);
            System.out.println("Added bookId " + bookId + " to favorites");
        } else {
            System.out.println("BookId " + bookId + " already in favorites");
        }
    }


    private void showBooksDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookIdParam = req.getParameter("bookId");
        System.out.println("bookIdParam received: " + bookIdParam); // Debug
        if (bookIdParam == null || bookIdParam.trim().isEmpty()) {

            return;
        }
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdParam);
        } catch (NumberFormatException e) {
            System.out.println("Invalid bookId: " + bookIdParam); // Debug

            return;
        }
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            System.out.println("Book not found for bookId: " + bookId); // Debug

            return;
        }
        Map<String, List<Book>> booksByCategory = bookService.getBooksByCategory();
        List<Book> relatedBooks = booksByCategory.get(book.getCategory());
        if (relatedBooks != null) {
            relatedBooks.removeIf(b -> b.getBookId() == bookId);
        }
        req.setAttribute("book", book);
        req.setAttribute("relatedBooks", relatedBooks);
        req.getRequestDispatcher(ProjectPaths.JSP_BOOKDETAILPAGE_PATH).forward(req, resp);
    }

    private void showBooksByCategory(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, List<Book>> booksByCategory = bookService.getBooksByCategory();
        req.setAttribute("booksByCategory", booksByCategory);
        try {
            req.getRequestDispatcher(ProjectPaths.JSP_BOOKPAGE_PATH).forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
