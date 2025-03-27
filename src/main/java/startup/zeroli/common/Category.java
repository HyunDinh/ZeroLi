package startup.zeroli.common;

/**
 *
 * @author Admin
 */
public enum Category {
    CLOTHING(1, "Quần áo"),
    PHONE_ACCESSORIES(2, "Điện thoại & phụ kiện"),
    COMPUTERS_LAPTOPS(3, "Máy tính & laptop"),
    ELECTRONICS(4, "Thiết bị điện tử"),
    SHOES(5, "Giày dép"),
    HOME_APPLIANCES(6, "Thiết bị điện gia dụng"),
    ONLINE_GROCERY(7, "Bách hoá online"),
    ONLINE_BOOKSTORE(8, "Nhà sách online"),
    HEALTH(9, "Sức khoẻ"),
    TOYS(10, "Đồ chơi"),
    ACCESSORIES(11, "Phụ kiện"),
    PET_CARE(12, "Chăm sóc thú cưng"),
    HOME_LIVING(13, "Nhà cửa & đời sống"),
    SPORTS_TRAVEL(14, "Thể thao & du lịch"),
    VEHICLES(15, "Oto & xe máy & xe đạp");

    private final int id;
    private final String vietnameseName;

    Category(int id, String vietnameseName) {
        this.id = id;
        this.vietnameseName = vietnameseName;
    }

    public int getId() {
        return id;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }
}