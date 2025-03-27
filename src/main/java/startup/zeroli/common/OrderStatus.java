package startup.zeroli.common;

/**
 *
 * @author Admin
 */
public enum OrderStatus {
    PENDING_CONFIRMATION("Chờ xác nhận"),
    PENDING_PICKUP("Chờ lấy hàng"),
    PENDING_DELIVERY("Chờ giao hàng"),
    DELIVERED("Đã giao"),
    RETURNED("Trả hàng"),
    CANCELLED("Đã hủy");

    private final String description;

    OrderStatus(String description) {
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