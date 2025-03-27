package startup.zeroli.common;

/**
 *
 * @author Admin
 */
public enum OrderDetailStatus {
    IN_STOCK("Còn hàng"),
    OUT_OF_STOCK("Hết hàng"),
    PACKED("Đã đóng gói"),
    SHIPPING("Đang giao"),
    RETURNED("Đã trả lại"),
    CANCELLED("Đã hủy");

    private final String description;

    OrderDetailStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
