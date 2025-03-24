package startup.zeroli.service.BookStore;

import startup.zeroli.model.Bookstore;
import java.util.List;

public interface BookStoreService {
    List<Bookstore> readBookstoresFromFile();
}
