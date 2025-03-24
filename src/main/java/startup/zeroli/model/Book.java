package startup.zeroli.model;

public class Book {
    private Integer bookId;
    private String bookName;
    private String author;
    private String category;
    private String imageBook;
    private String pdfPath;

    public Book() {
    }

    public Book(Integer bookId, String bookName, String author, String category, String imageBook, String pdfPath) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.imageBook = imageBook;
        this.pdfPath = pdfPath;
    }

    public Book(String bookName, String author, String category, String imageBook, String pdfPath) {
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.imageBook = imageBook;
        this.pdfPath = pdfPath;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", imageBook='" + imageBook + '\'' +
                ", pdfPath='" + pdfPath + '\'' +
                '}';
    }
}
