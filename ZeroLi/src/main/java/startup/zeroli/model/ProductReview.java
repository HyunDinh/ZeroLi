/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.model;

/**
 *
 * @author Admin
 */
import java.time.LocalDateTime;
import startup.zeroli.common.Status;

public class ProductReview {

    private int viewId;
    private int productId;
    private String userName;
    private int rating;
    private String content;
    private Status status;

    public ProductReview() {
    }

    public ProductReview(int viewId, int productId, String userName, int rating, String content, Status status) {
        this.viewId = viewId;
        this.productId = productId;
        this.userName = userName;
        this.rating = rating;
        this.content = content;
        this.status = status;
    }

    public ProductReview(int viewId, int productId, int rating, String content, Status status) {
        this.viewId = viewId;
        this.productId = productId;
        this.rating = rating;
        this.content = content;
        this.status = status;
    }

    public ProductReview(int productId, int rating, String content) {
        this.productId = productId;
        this.rating = rating;
        this.content = content;
        this.status = Status.ACTIVE;
    }
    
    public ProductReview(int productId, String userName, int rating, String content) {
        this.productId = productId;
        this.userName = userName;
        this.rating = rating;
        this.content = content;
        this.status = Status.ACTIVE;
    }

    public ProductReview(int viewId, int productId, String userName, int rating, String content) {
        this.viewId = viewId;
        this.productId = productId;
        this.userName = userName;
        this.rating = rating;
        this.content = content;
        this.status = Status.ACTIVE;
    }

    public ProductReview(int viewId, int productId, int rating, String content) {
        this.viewId = viewId;
        this.productId = productId;
        this.rating = rating;
        this.content = content;
        this.status = Status.ACTIVE;
    }

    public ProductReview(int rating, String content) {
        this.rating = rating;
        this.content = content;
        //this.status = Status.ACTIVE;               
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
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

    public Status setStatusFromString(String statusString) {
        try {
            this.status = Status.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid status: " + statusString);
            this.status = Status.ACTIVE;
        }
        return this.status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ProductReview{" + "viewId=" + viewId + ", productId=" + productId + ", userName=" + userName + ", rating=" + rating + ", content=" + content + ", status=" + status + '}';
    }

}
