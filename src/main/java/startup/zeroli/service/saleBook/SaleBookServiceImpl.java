package startup.zeroli.service.saleBook;

import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;
import startup.zeroli.dal.GenericDAO;
import startup.zeroli.model.SaleBook;

/**
 *
 * @author LENOVO
 */
public class SaleBookServiceImpl implements SaleBookService {

    private final GenericDAO<SaleBook> saleBookDAO;

    public SaleBookServiceImpl() {
        this.saleBookDAO = new GenericDAO<>(SaleBook.class);
    }

    // Create a new SaleBook
    @Override
    public void createSaleBook(SaleBook saleBook) {
        saleBookDAO.save(saleBook);
    }

    // Retrieve a SaleBook by ID
    @Override
    public SaleBook getSaleBookById(int bookId) {
        return saleBookDAO.findById(bookId);
    }

    // Retrieve all SaleBooks
    @Override
    public List<SaleBook> getAllSaleBooks() {
        return saleBookDAO.findAll();
    }

    // Update an existing SaleBook
    @Override
    public void updateSaleBook(SaleBook saleBook) {
        saleBookDAO.update(saleBook);
    }

    // Delete a SaleBook by ID
    @Override
    public void deleteSaleBook(int bookId) {
        saleBookDAO.delete(bookId);
    }

    // Lấy danh sách tất cả nhà cung cấp (không trùng lặp)
    @Override
    public List<String> getAllSuppliers() {
        List<SaleBook> allBooks = saleBookDAO.findAll();
        return allBooks.stream()
                .map(SaleBook::getSupplier)
                .distinct() // Loại bỏ các giá trị trùng lặp
                .collect(Collectors.toList());
    }

    // Lấy danh sách sách theo nhà cung cấp
    @Override
    public List<SaleBook> getBooksBySupplier(String supplier) {
        List<SaleBook> allBooks = saleBookDAO.findAll();
        return allBooks.stream()
                .filter(book -> book.getSupplier().equalsIgnoreCase(supplier))
                .collect(Collectors.toList());
    }

    // Các hàm khác giữ nguyên...
    @Override
    public List<SaleBook> findBooksByCategory(String category) {
        List<SaleBook> allBooks = saleBookDAO.findAll();
        return allBooks.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleBook> findBooksByAuthor(String author) {
        List<SaleBook> allBooks = saleBookDAO.findAll();
        return allBooks.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public SaleBook getBookById(int bookId) {
        try {
            // Sử dụng JPA để truy vấn database
            TypedQuery<SaleBook> query = saleBookDAO.getEntityManager().createQuery(
                    "SELECT b FROM SaleBook b WHERE b.bookId = :bookId", SaleBook.class);
            query.setParameter("bookId", bookId);
            return query.getSingleResult();
        } catch (Exception e) {
            // Nếu không tìm thấy sách hoặc có lỗi
            return null;
        }
    }

}
