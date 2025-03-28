package startup.zeroli.dao;

import startup.zeroli.model.Bookstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import startup.zeroli.config.AbsolutePaths;
import startup.zeroli.model.Event;
import startup.zeroli.utils.ErrDialog;

public class BookDAO {

    public List<Event> readEventsFromFile() {
        List<Event> events = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.EVENTS_DATA_PATH))) {
            String line;

            // Đọc từng dòng từ file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Kiểm tra nếu dòng có đủ 3 phần (hình ảnh, tên sự kiện, mô tả)
                if (parts.length == 3) {
                    String imageUrl = parts[0].trim();      // Địa chỉ hình ảnh
                    String eventName = parts[1].trim();     // Tên sự kiện
                    String description = parts[2].trim();  // Mô tả sự kiện

                    // In thông tin sự kiện để kiểm tra
                    System.out.println("Hình ảnh: " + imageUrl);
                    System.out.println("Tên sự kiện: " + eventName);
                    System.out.println("Mô tả: " + description);
                    System.out.println("------------------------");

                    // Tạo đối tượng Event và thêm vào danh sách
                    events.add(new Event(imageUrl, eventName, description));
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return events;
    }

    public List<Bookstore> readBookstoresFromFile() {
        List<Bookstore> bookstores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.BOOKSTORES_DATA_PATH))) {
            String line;

            // Đọc từng dòng từ file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Kiểm tra nếu dòng có đủ 4 phần (hình ảnh, tên, địa chỉ, mô tả)
                if (parts.length == 4) {
                    String imageUrl = parts[0].trim();    // Địa chỉ hình ảnh
                    String name = parts[1].trim();        // Tên nhà sách
                    String address = parts[2].trim();     // Địa chỉ nhà sách
                    String description = parts[3].trim(); // Mô tả nhà sách

                    // In thông tin nhà sách để kiểm tra
                    System.out.println("Hình ảnh: " + imageUrl);
                    System.out.println("Tên nhà sách: " + name);
                    System.out.println("Địa chỉ: " + address);
                    System.out.println("Mô tả: " + description);
                    System.out.println("------------------------");

                    // Tạo đối tượng Bookstore và thêm vào danh sách
                    bookstores.add(new Bookstore(imageUrl, name, address, description));
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            ErrDialog.showError(e.getMessage());
        }

        return bookstores;
    }

}
