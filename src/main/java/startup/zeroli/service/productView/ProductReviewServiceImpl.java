package startup.zeroli.service.productView;

import jakarta.persistence.EntityManager;
import startup.zeroli.dal.GenericDAO;
import startup.zeroli.model.ProductReview;
import startup.zeroli.model.User;
import startup.zeroli.utils.ErrDialog;
import startup.zeroli.common.Status;

import java.util.List;

public class ProductReviewServiceImpl implements ProductReviewService {

    private final GenericDAO<ProductReview> productReviewDAO;
    private final EntityManager entityManager;

    public ProductReviewServiceImpl() {
        this.productReviewDAO = new GenericDAO<>(ProductReview.class);
        this.entityManager = productReviewDAO.getEntityManager();
    }

    @Override
    public List<ProductReview> getAllActiveReviews() {
        try {
            String jpql = "SELECT r FROM ProductReview r WHERE r.status = :status";
            return entityManager.createQuery(jpql, ProductReview.class)
                    .setParameter("status", Status.ACTIVE)
                    .getResultList();
        } catch (Exception e) {
            ErrDialog.showError("Failed to fetch reviews: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public ProductReview getReviewById(int viewId) {
        try {
            return productReviewDAO.findById(viewId);
        } catch (Exception e) {
            ErrDialog.showError("Review not found: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductReview> getReviewsByProductId(int productId) {
        try {
            String jpql = "SELECT r FROM ProductReview r JOIN FETCH r.user " +
                         "WHERE r.productId = :productId AND r.status = :status " +
                         "ORDER BY r.viewId DESC";
            return entityManager.createQuery(jpql, ProductReview.class)
                    .setParameter("productId", productId)
                    .setParameter("status", Status.ACTIVE)
                    .getResultList();
        } catch (Exception e) {
            ErrDialog.showError("Failed to fetch product reviews: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public boolean addProductReview(int productId, User user, int rating, String content) {
        try {
            ProductReview review = new ProductReview(productId, user, rating, content);
            productReviewDAO.save(review);
            return true;
        } catch (Exception e) {
            ErrDialog.showError("Failed to add review: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean addProductReview(ProductReview review) {
        try {
            productReviewDAO.save(review);
            return true;
        } catch (Exception e) {
            ErrDialog.showError("Failed to add review: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProductReview(int reviewId, int newRating, String newContent) {
        try {
            ProductReview review = productReviewDAO.findById(reviewId);
            if (review != null) {
                review.setRating(newRating);
                review.setContent(newContent);
                productReviewDAO.update(review);
                return true;
            }
            return false;
        } catch (Exception e) {
            ErrDialog.showError("Failed to update review: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProductReview(int id) {
        try {
            ProductReview review = productReviewDAO.findById(id);
            if (review != null) {
                review.deleteReview();
                productReviewDAO.update(review);
                return true;
            }
            return false;
        } catch (Exception e) {
            ErrDialog.showError("Failed to delete review: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hasUserReviewedProduct(int userId, int productId) {
        try {
            String jpql = "SELECT COUNT(r) FROM ProductReview r " +
                          "WHERE r.user.id = :userId AND r.productId = :productId AND r.status = :status";
            Long count = entityManager.createQuery(jpql, Long.class)
                    .setParameter("userId", userId)
                    .setParameter("productId", productId)
                    .setParameter("status", Status.ACTIVE)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            ErrDialog.showError("Failed to check user review: " + e.getMessage());
            return false;
        }
    }
}