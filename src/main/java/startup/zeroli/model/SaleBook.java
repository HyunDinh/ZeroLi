package startup.zeroli.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SaleBook")
public class SaleBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Basic(optional = false)
    @Column(name = "book_name", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String bookName;

    @Basic(optional = false)
    @Column(name = "publisher", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String publisher;

    @Basic(optional = false)
    @Column(name = "supplier", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String supplier;

    @Basic(optional = false)
    @Column(name = "publish_year", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String publishYear;

    @Basic(optional = false)
    @Column(name = "author", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String author;

    @Basic(optional = false)
    @Column(name = "price", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String price;

    @Basic(optional = false)
    @Column(name = "category", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String category;

    @Basic(optional = false)
    @Column(name = "descriptions", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String descriptions;

    @Basic(optional = false)
    @Column(name = "imageBook", columnDefinition = "NVARCHAR(555)", nullable = false)
    private String imageBook;

    // Constructors
    public SaleBook() {}

    public SaleBook(String bookName, String publisher, String supplier, String publishYear,
                    String author, String price, String category, String descriptions, String imageBook) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.supplier = supplier;
        this.publishYear = publishYear;
        this.author = author;
        this.price = price;
        this.category = category;
        this.descriptions = descriptions;
        this.imageBook = imageBook;
    }

    // Getters và Setters
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }
}
