package startup.zeroli.service.saleBook;

import java.util.List;
import startup.zeroli.model.SaleBook;

/**
 *
 * @author LENOVO
 */
public interface SaleBookService {
    void createSaleBook(SaleBook saleBook);
    SaleBook getSaleBookById(int bookId);
    List<SaleBook> getAllSaleBooks();
    void updateSaleBook(SaleBook saleBook);
    void deleteSaleBook(int bookId);
    List<String> getAllSuppliers();
    List<SaleBook> getBooksBySupplier(String supplier);
    List<SaleBook> findBooksByCategory(String category);
    List<SaleBook> findBooksByAuthor(String author);
}
