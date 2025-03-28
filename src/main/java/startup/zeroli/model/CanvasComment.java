package startup.zeroli.model;

import java.util.List;

public class CanvasComment {
    private int idProduct;
    private String username;
    private List<String> reviews;

    public CanvasComment() {
    }

    public CanvasComment(int idProduct, String username, List<String> reviews) {
        this.idProduct = idProduct;
        this.username = username;
        this.reviews = reviews;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}