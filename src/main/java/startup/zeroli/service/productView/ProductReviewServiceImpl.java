package startup.zeroli.service.productView;

import startup.zeroli.model.ProductReview;
import startup.zeroli.common.Status;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import startup.zeroli.utils.ErrDialog;

public class ProductReviewServiceImpl implements ProductReviewService {


    private static final String FILE_PATH = "D:\\code\\University\\ZeroLi\\src\\main\\java\\startup\\zeroli\\service\\productView\\productReview";
    @Override
    public List<ProductReview> getAllProductReview() {
        List<ProductReview> listReviews = new ArrayList<>();
        ProductReview review = new ProductReview();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|\\|");
                listReviews.add(new ProductReview(
                        Integer.parseInt(data[0].trim()),
                        Integer.parseInt(data[1].trim()),
                        data[2].trim(),
                        Integer.parseInt(data[3].trim()),
                        data[4].trim(),
                        review.setStatusFromString(data[5].trim())));
            }
        } catch (IOException e) {
            ErrDialog.showError(e.getMessage());
        }
        return listReviews;
    }

    public List<ProductReview> getReviewsByProductId(int productId) {
        List<ProductReview> listReviews = getAllProductReview();
        List<ProductReview> productReviews = new ArrayList<>();

        for (ProductReview review : listReviews) {
            if (review.getProductId() == productId && review.getStatus() == Status.ACTIVE) {
                productReviews.add(review);
            }
        }
        return productReviews;
    }

    @Override
    public void addProductReview(ProductReview pr) {
        List<ProductReview> listReviews = getAllProductReview();

        int nextId = listReviews.isEmpty() ? 1 : listReviews.get(listReviews.size() - 1).getViewId() + 1;

        pr.setViewId(nextId);
        //tham số true có ý nghĩa là mở file ở chế độ "append" (thêm vào cuối file)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            listReviews.add(pr);
            bw.write(nextId + " || "
                    + pr.getProductId() + " || "
                    + pr.getUserName() + " || "
                    + pr.getRating() + " || "
                    + pr.getContent() + " || "
                    + pr.getStatus() + "\n"
            );
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
    }

    @Override
    public void updateProductReview(ProductReview pr) {
        List<ProductReview> listReviews = getAllProductReview();
        boolean isUpdated = false;

        for (ProductReview review : listReviews) {
            if (review.getViewId() == pr.getViewId()) {
                review.setRating(pr.getRating());
                review.setContent(pr.getContent());
                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (ProductReview review : listReviews) {
                    bw.write(review.getViewId() + " || "
                            + review.getProductId() + " || "
                            + review.getUserName() + " || "
                            + review.getRating() + " || "
                            + review.getContent() + " || "
                            + review.getStatus() + "\n");
                }
            } catch (IOException e) {
                ErrDialog.showError(e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy nhận xét với viewId = " + pr.getViewId());
        }
    }

    @Override
    public void deleteProductReview(int id) {
        List<ProductReview> listReviews = getAllProductReview();
        boolean isUpdated = false;

        for (ProductReview review : listReviews) {
            if (review.getViewId() == id) {
                review.setStatus(Status.INACTIVE);
                isUpdated = true;
                ErrDialog.showError("" + review.getStatus());
                break;
            }
        }

        if (isUpdated) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (ProductReview review : listReviews) {
                    bw.write(review.getViewId() + " || "
                            + review.getProductId() + " || "
                            + review.getUserName() + " || "
                            + review.getRating() + " || "
                            + review.getContent() + " || "
                            + review.getStatus() + "\n");
                }
            } catch (IOException e) {
                ErrDialog.showError(e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy nhận xét với viewId = " + id);
        }
    }

    private void writeToFile(List<ProductReview> reviews) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ProductReview review : reviews) {
                writer.write(review.getViewId() + " || "
                        + review.getProductId() + " || "
                        + review.getRating() + " || \""
                        + review.getContent() + "\" || "
                        + review.getStatus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ProductReview pr = new ProductReview(1, 1, 5, "ZOkkkk");
        ProductReview pr1 = new ProductReview(1, "HUHUHU", 5, "OKKKKK!");
        ProductReviewServiceImpl impl = new ProductReviewServiceImpl();
        impl.updateProductReview(pr);

    }
}
