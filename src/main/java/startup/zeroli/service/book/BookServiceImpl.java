package startup.zeroli.service.book;

import startup.zeroli.model.Book;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {
    private final int option = 0;
    private static final String filePath = "E:\\prj301\\ZeroLi\\src\\main\\java\\startup\\zeroli\\service\\book\\books.txt";
    private List<Book> books; // Lưu trữ danh sách sách để tránh đọc file nhiều lần

    public BookServiceImpl() {
        books = loadBooksFromFile(); // Load dữ liệu một lần khi khởi tạo
        System.out.println("Số lượng sách đã load: " + books.size()); // Debug
    }

    private List<Book> loadBooksFromFile() {
        List<Book> bookList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File không tồn tại tại: " + filePath);
            return bookList; // Trả về list rỗng nếu file không tồn tại
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            System.out.println("Đã mở tệp thành công tại: " + filePath);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // Bỏ qua dòng trống

                String[] bookDetails = line.split(",\\s*");
                if (bookDetails.length == 6) { // Kiểm tra đúng 6 trường
                    try {
                        Integer id = Integer.parseInt(bookDetails[0].trim());
                        String name = bookDetails[1].trim();
                        String author = bookDetails[2].trim();
                        String category = bookDetails[3].trim();
                        String imageUrl = bookDetails[4].trim();
                        String pdfPath = bookDetails[5].trim();
                        Book book = new Book(id, name, author, category, imageUrl, pdfPath);
                        bookList.add(book);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi chuyển đổi ID thành số nguyên: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.out.println("Dòng không hợp lệ (không đủ 6 trường): " + line + " - Số trường: " + bookDetails.length);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tệp: " + filePath + " - " + e.getMessage());
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Map<String, List<Book>> getBooksByCategory() {
        switch (option) {
            case 0 -> {
                Map<String, List<Book>> booksByCategory = new HashMap<>();
                for (Book book : books) {
                    booksByCategory.computeIfAbsent(book.getCategory(), k -> new ArrayList<>()).add(book);
                }
                System.out.println("Tổng số danh mục: " + booksByCategory.size());
                return booksByCategory;
            }
            case 1 -> {
                // JPA (nếu cần sau này)
                return new HashMap<>();
            }
            default -> throw new AssertionError("Invalid option value");
        }
    }

    @Override
    public Book getBookById(int bookId) {
        switch (option) {
            case 0 -> {
                System.out.println("Tìm kiếm bookId: " + bookId); // Debug
                for (Book book : books) {
                    if (book.getBookId() == bookId) {
                        System.out.println("Đã tìm thấy sách: " + book.getBookName()); // Debug
                        return book;
                    }
                }
                System.out.println("Không tìm thấy sách với bookId: " + bookId); // Debug
                return null; // Trả về null nếu không tìm thấy sách
            }
            case 1 -> {
                // JPA (nếu cần sau này)
                return null;
            }
            default -> throw new AssertionError("Invalid option value");
        }
    }
}

