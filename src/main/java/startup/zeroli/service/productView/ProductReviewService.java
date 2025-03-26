/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.productView;

import java.util.List;
import startup.zeroli.model.ProductReview;
import startup.zeroli.model.User;

/**
 *
 * @author Admin
 */
public interface ProductReviewService {

    List<ProductReview> getAllActiveReviews();
    ProductReview getReviewById(int viewId);
    List<ProductReview> getReviewsByProductId(int productId);
    boolean addProductReview(int productId, User user, int rating, String content);
    boolean addProductReview(ProductReview review);
    boolean updateProductReview(int reviewId, int newRating, String newContent);
    boolean deleteProductReview(int id);
    boolean hasUserReviewedProduct(int userId, int productId);

}
