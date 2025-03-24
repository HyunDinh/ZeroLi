package startup.zeroli.service.BookStore;

import java.util.List;
import startup.zeroli.dao.BookDAO;
import startup.zeroli.model.Bookstore;

public class BookStoreServiceImpl implements BookStoreService{
    BookDAO bookDAO = new BookDAO();


    @Override
    public List<Bookstore> readBookstoresFromFile() {
        return bookDAO.readBookstoresFromFile();
    }
}
