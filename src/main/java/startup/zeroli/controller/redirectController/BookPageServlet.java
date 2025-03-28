package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import startup.zeroli.config.AbsolutePaths;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.Book;
import startup.zeroli.service.book.BookService;
import startup.zeroli.service.book.BookServiceImpl;

@WebServlet(name = "BookPageServlet", urlPatterns = {MainControllerServlet.BOOKPAGE_SERVLET})
public class BookPageServlet extends HttpServlet {
    
    private BookService bookService;
    
    public BookPageServlet() {
        bookService = new BookServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case MainControllerServlet.BOOKPAGE_REDIRECT ->
                showBooksByCategory(request, response);
            case MainControllerServlet.ACTION_VIEW_BOOKDETAIL ->
                showBooksDetail(request, response);
            case MainControllerServlet.ACTION_FAVORITE_BOOK ->
                addFavoriteBook(request, response);
            case MainControllerServlet.ACTION_GET_AUDIO ->
                serveAudioFile(request, response);
            case MainControllerServlet.ACTION_VIEW_BOOK_BY_CATEGORY ->{
                String category = request.getParameter("category");
                if (category != null && !category.trim().isEmpty()) {
                    showBooksBySingleCategory(request, response, category);
                } else {
                    response.sendRedirect(ProjectPaths.HREF_TO_BOOKPAGE);
                }
            }
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
    
    private void serveAudioFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookIdParam = req.getParameter("bookId");
        if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing bookId parameter");
            return;
        }
        
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid bookId");
            return;
        }
        
        Book book = bookService.getBookById(bookId);
        if (book == null || getAudioPath(book.getPdfPath()) == null || getAudioPath(book.getPdfPath()).isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Audio file not found for bookId: " + bookId);
            return;
        }
        
        String audioPath = getAudioPath(book.getPdfPath());

        String realPath = AbsolutePaths.AUDIO_DIR + audioPath;
        File audioFile = new File(realPath);
        
        if (!audioFile.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Audio file does not exist at: " + realPath);
            return;
        }

        // Thiết lập header để trả về tệp âm thanh
        resp.setContentType("audio/mp3");
        resp.setContentLength((int) audioFile.length());
        resp.setHeader("Content-Disposition", "inline; filename=\"" + audioFile.getName() + "\"");

        // Đọc và gửi tệp âm thanh về client
        try (FileInputStream in = new FileInputStream(audioFile); OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
    
    
    private String getAudioPath(String pdfPath){
        return pdfPath.replaceAll(".pdf", ".mp3");
    }
    
    private void showBooksBySingleCategory(HttpServletRequest req, HttpServletResponse resp, String category) {
        Map<String, List<Book>> booksByCategory = bookService.getBooksByCategory();
        Map<String, List<Book>> filteredBooks = new HashMap<>();

        if (booksByCategory.containsKey(category)) {
            filteredBooks.put(category, booksByCategory.get(category));
        }

        req.setAttribute("booksByCategory", filteredBooks);
        try {
            req.getRequestDispatcher(ProjectPaths.JSP_BOOKCATEGORY_PATH).forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
