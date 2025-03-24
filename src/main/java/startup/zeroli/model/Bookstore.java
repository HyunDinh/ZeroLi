package startup.zeroli.model;

import java.util.List;

public class Bookstore {
    private String imageUrl;     // Địa chỉ hình ảnh
    private String name;         // Tên nhà sách
    private String address;      // Địa chỉ nhà sách
    private String description;  // Mô tả nhà sách
    private double latitude;     // Vĩ độ
    private double longitude;
    private List<String> googleMapsLinks;// Kinh độ


    public Bookstore(String imageUrl, String name, String address, String description, double latitude, double longitude, List<String> googleMapsLinks) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.googleMapsLinks = googleMapsLinks;
    }

    public Bookstore(String imageUrl, String name, String address, String description) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    // Getter và Setter
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getGoogleMapsLinks() {
        return googleMapsLinks;
    }

    public void setGoogleMapsLinks(List<String> googleMapsLinks) {
        this.googleMapsLinks = googleMapsLinks;
    }

    @Override
    public String toString() {
        return "Bookstore{" +
                "imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", googleMapsLink='" + googleMapsLinks + '\'' +
                '}';
    }
}
