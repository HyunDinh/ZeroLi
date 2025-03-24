package startup.zeroli.dao;

import startup.zeroli.model.Bookstore;

import java.util.List;

public class Printer {
    public void printAllBookstores(List<Bookstore> bookstores) {
        if (bookstores == null || bookstores.isEmpty()) {
            System.out.println("Danh sách nhà sách trống.");
        } else {
            for (Bookstore bookstore : bookstores) {
                System.out.println("Tên nhà sách: " + bookstore.getName());
                System.out.println("Địa chỉ: " + bookstore.getAddress());
                System.out.println("Mô tả: " + bookstore.getDescription());
                System.out.println("------------------------");
            }
        }
    }
}
