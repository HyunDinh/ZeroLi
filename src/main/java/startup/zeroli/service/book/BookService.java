package startup.zeroli.service.book;

import startup.zeroli.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BookService {
    Map<String, List<Book>> getBooksByCategory();
    Book getBookById(int bookId);
}

