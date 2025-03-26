package startup.zeroli.model;

import jakarta.persistence.*;
import startup.zeroli.common.Status;

@Entity
@Table(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id")
    private int viewId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    public ProductReview() {
    }

    public ProductReview(int productId, User user, int rating, String content) {
        this.productId = productId;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }

    // Các getter và setter cần thay đổi
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Giữ nguyên các phương thức khác...
    public int getViewId() {
        return viewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void deleteReview() {
        this.status = Status.INACTIVE;
    }

    public void setStatusFromString(String statusString) {
        try {
            this.status = Status.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid status: " + statusString);
            this.status = Status.ACTIVE;
        }
    }

    @Override
    public String toString() {
        return "ProductReview{" + "viewId=" + viewId + ", productId=" + productId
                + ", user=" + (user != null ? user.getUsername() : "null")
                + ", rating=" + rating + ", content='" + content + '\''
                + ", status=" + status + '}';
    }
}
