/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.productView;

import java.util.List;
import startup.zeroli.model.ProductReview;

/**
 *
 * @author Admin
 */
public interface ProductReviewService {

    List<ProductReview> getAllProductReview();

    List<ProductReview> getReviewsByProductId(int id);

    void addProductReview(ProductReview pr);

    void updateProductReview(ProductReview pr);

    void deleteProductReview(int id);

}
