package startup.zeroli.model;

import startup.zeroli.common.ProductStatus; // Import enum ProductStatus
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Product {

    private int productId;
    private String productName;
    private String brandName;
    private BigDecimal price;
    private int stock;
    private ProductStatus productStatus;
//    private LocalDateTime createdDate;
//    private LocalDateTime updatedDate;
    private String description;
    private String imageURL;

    public Product() {
    }

    public Product(int productId, String productName, String brandName, BigDecimal price, int stock, ProductStatus productStatus, String description, String imageURL) {
        this.productId = productId;
        this.productName = productName;
        this.brandName = brandName;
        this.price = price;
        this.stock = stock;
        this.productStatus = productStatus;
        this.description = description;
        this.imageURL = imageURL;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Product that)) {
            return false;
        }

        return getProductId() == that.getProductId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }

    public ProductStatus setProductStatusFromString(String statusString) {
        try {
            this.productStatus = ProductStatus.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid product status: " + statusString);
            this.productStatus = ProductStatus.AVAILABLE;
        }
        return this.productStatus;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", brandName=" + brandName + ", price=" + price + ", stock=" + stock + ", productStatus=" + productStatus + ", description=" + description + ", imageURL=" + imageURL + '}';
    }

}
