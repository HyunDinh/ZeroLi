package startup.zeroli.model;

import jakarta.persistence.*;
import startup.zeroli.common.ProductStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    
    @Column(nullable = false, length = 255)
    private String productName;
    
    @Column(length = 255)
    private String brandName;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(nullable = false)
    private int stock;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ProductStatus productStatus;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    
    private LocalDateTime updatedDate;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(length = 500)
    private String imageURL;

    public Product() {
    }

    public Product(String productName, String brandName, BigDecimal price, int stock, ProductStatus productStatus, String description, String imageURL) {
        this.productName = productName;
        this.brandName = brandName;
        this.price = price;
        this.stock = stock;
        this.productStatus = productStatus;
        this.description = description;
        this.imageURL = imageURL;
        this.createdDate = LocalDateTime.now();
    }
    
    public int getProductId() {
        return productId;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
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
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName='" + productName + '\'' + ", brandName='" + brandName + '\'' + ", price=" + price + ", stock=" + stock + ", productStatus=" + productStatus + ", description='" + description + '\'' + ", imageURL='" + imageURL + '\'' + '}';
    }
}
