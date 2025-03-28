package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.dao.BookDAO;
import startup.zeroli.model.Bookstore;
import startup.zeroli.model.Event;
import startup.zeroli.service.BookStore.BookStoreService;
import startup.zeroli.service.BookStore.BookStoreServiceImpl;



@WebServlet(name = "ServicePageServlet", urlPatterns = {MainControllerServlet.SERVICEPAGE_SERVLET})
public class ServicePageServlet extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookStoreService service = new BookStoreServiceImpl();
        List<Bookstore> bookstores = service.readBookstoresFromFile();
        // Kiểm tra xem danh sách có được đọc đúng không
        System.out.println("Số lượng nhà sách: " + bookstores.size());
        if (bookstores.isEmpty()) {
            System.out.println("Không có nhà sách trong danh sách.");
        }
        // Tạo và thêm nhiều đường link Google Maps cho mỗi nhà sách
        for (int i = 0; i < bookstores.size(); i++) {
            Bookstore bookstore = bookstores.get(i);
            List<String> googleMapsLinks = new ArrayList<>();

            // Đường link cho i = 0 (Nhà sách Phương Nam)
            if (i == 0) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+s%C3%A1ch+Ph%C6%B0%C6%A1ng+Nam/@16.0645,108.2175041,17z/data=!3m1!4b1!4m6!3m5!1s0x314218337f0db0c9:0xbb4a46e102fa201!8m2!3d16.0645!4d108.220079!16s%2Fg%2F1tdyn810?entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            // Đường link cho i = 1 (Nhà sách Fahasa Đà Nẵng)
            else if (i == 1) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+S%C3%A1ch+FAHASA+%C4%90%C3%A0+N%E1%BA%B5ng/@16.0696862,108.0579736,12z/data=!4m10!1m2!2m1!1zTmjDoCBTw6FjaCBGQUhBU0E!3m6!1s0x3142184bcf090a55:0xdc47090985058dcf!8m2!3d16.0696862!4d108.2104089!15sChFOaMOgIFPDoWNoIEZBSEFTQSIDiAEBWhMiEW5ow6Agc8OhY2ggZmFoYXNhkgEKYm9va19zdG9yZeABAA!16s%2Fg%2F1thlwy7_?hl=vi-VN&entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            // Đường link cho i = 2 (Nhà sách Kim Đồng)
            else if (i == 2) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+s%C3%A1ch+Kim+%C4%90%E1%BB%93ng/@16.0646791,108.1748605,14z/data=!4m10!1m2!2m1!1zbmjDoCBzw6FjaCBraW0gxJHhu5NuZw!3m6!1s0x314218337f0db0c9:0xbb4a46e102fa201!8m2!3d16.0593968!4d108.2169826!15sChZuaMOgIHPDoWNoIGtpbSDEkeG7k25nIgOIAQGSARRjaGlsZHJlbnNfYm9va19zdG9yZeABAA!16s%2Fg%2F11byyjkqr4?entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            // Đường link cho i = 3 (Công ty Cổ phần Sách và Thiết bị Giáo dục Miền Trung)
            else if (i == 3) {
                googleMapsLinks.add("https://www.google.com/maps/place/C%C3%B4ng+ty+C%E1%BB%95+ph%E1%BA%A7n+S%C3%A1ch+v%C3%A0+Thi%E1%BA%BFt+b%E1%BB%8B+Gi%C3%A1o+d%E1%BB%A5c+Mi%E1%BB%81n+Trung/@13.6019184,104.8150989,7z/data=!4m10!1m2!2m1!1zY8O0bmcgdHkgY3Agc8OhY2ggJiB0YnR0!3m6!1s0x314219adae48340f:0x51a56af6aa451fbc!8m2!3d16.0506666!4d108.2096494!15sChhjw7RuZyB0eSBjcCBzw6FjaCAmIHRidHSSAQ5ib29rX3B1Ymxpc2hlcuABAA!16s%2Fg%2F11r9h2pw8c?entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            // Đường link cho i = 4 (Chi Nhánh Nhà Xuất Bản Trẻ Tại Đà Nẵng)
            else if (i == 4) {
                googleMapsLinks.add("https://www.google.com/maps/place/Chi+Nh%C3%A1nh+Nh%C3%A0+Xu%E1%BA%A5t+B%E1%BA%A3n+Tr%E1%BA%BB+T%E1%BA%A1i+%C4%90%C3%A0+N%E1%BA%B5ng/@16.0534074,108.2149064,17z/data=!3m1!4b1!4m6!3m5!1s0x314219c8bb50b6d3:0xccbf3de01c630e50!8m2!3d16.0534074!4d108.2174813!16s%2Fg%2F11hzxjwshg?entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            // Đường link cho i = 5 (Nhà sách Đồng Nguyên)
            else if (i == 5) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+s%C3%A1ch+%C4%90%E1%BB%93ng+Nguy%C3%AAn/@16.0187939,108.2051043,17z/data=!3m1!4b1!4m6!3m5!1s0x314219f15f356f8d:0xaa912b213f1a34e3!8m2!3d16.0187939!4d108.2076792!16s%2Fg%2F11fx8cfsk4?entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            //hi
            else if (i == 6) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+s%C3%A1ch+Ph%C6%B0%C6%A1ng/@16.0665891,108.2053375,17z/data=!4m10!1m2!2m1!1zTmjDoCBzw6FjaCBQaMawxqFuZw!3m6!1s0x3142184b1644e88f:0xc22d97e05b87e965!8m2!3d16.0665891!4d108.2101011!15sChNOaMOgIHPDoWNoIFBoxrDGoW5nWhUiE25ow6Agc8OhY2ggcGjGsMahbmeSAQpib29rX3N0b3Jl4AEA!16s%2Fg%2F1ts1k3fh?entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            else if (i == 7) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+S%C3%A1ch+T%C3%BA+Anh/@16.0372306,108.185286,17z/data=!3m1!4b1!4m6!3m5!1s0x31421976427b247b:0xb8ddfecb99b7171c!8m2!3d16.0372306!4d108.1878609!16s%2Fg%2F11hf8by9tz?hl=vi-VN&entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }
            else if (i == 8) {
                googleMapsLinks.add("https://www.google.com/maps/place/Nh%C3%A0+s%C3%A1ch+Nguy%C3%AAn+L%E1%BB%99c/@16.0609933,108.1587975,17z/data=!3m1!4b1!4m6!3m5!1s0x314219239e67021d:0xc8a655d1338ceecc!8m2!3d16.0609933!4d108.1613724!16s%2Fg%2F1tvgrv0b?hl=vi-VN&entry=ttu&g_ep=EgoyMDI1MDMxMi4wIKXMDSoASAFQAw%3D%3D");
            }


            // Cập nhật các đường link Google Maps cho nhà sách
            bookstore.setGoogleMapsLinks(googleMapsLinks);
        }
        
        // Đọc sự kiện từ file
        BookDAO bd = new BookDAO();
        List<Event> events = bd.readEventsFromFile();
        // Kiểm tra sự kiện
        System.out.println("Số lượng sự kiện: " + events.size());
        if (events.isEmpty()) {
            System.out.println("Không có sự kiện trong danh sách.");
        }
        for (int i = 0; i < events.size(); i++) {
            Event event=events.get(i);
            List<String> googleMapsLinks = new ArrayList<>();
            if (i == 0) {
                googleMapsLinks.add("https://www.google.com/maps/place/C%E1%BA%A7u+S%C3%B4ng+H%C3%A0n/@16.0721108,108.2242725,17z/data=!3m1!4b1!4m6!3m5!1s0x31421831cd8a36d1:0x384de766f6cc5a4e!8m2!3d16.0721108!4d108.2268474!16s%2Fm%2F05q4n12?entry=ttu&g_ep=EgoyMDI1MDMyMy4wIKXMDSoASAFQAw%3D%3D");
            }else if (i == 1) {
                googleMapsLinks.add("https://www.google.com/maps/place/46+B%E1%BA%A1ch+%C4%90%E1%BA%B1ng,+H%E1%BA%A3i+Ch%C3%A2u+1,+H%E1%BA%A3i+Ch%C3%A2u,+%C4%90%C3%A0+N%E1%BA%B5ng+550000,+Vi%E1%BB%87t+Nam/@16.0745019,108.2217385,17z/data=!3m1!4b1!4m6!3m5!1s0x31421830732669df:0x4b8bb529043b1cf3!8m2!3d16.0745019!4d108.2243134!16s%2Fg%2F11c1_6hvh1?entry=ttu&g_ep=EgoyMDI1MDMyMy4wIKXMDSoASAFQAw%3D%3D");
            }
            else if (i == 2) {
                googleMapsLinks.add("https://www.google.com/maps/place/Ba+Na+Hills/@15.9951364,107.9935643,17z/data=!3m1!4b1!4m6!3m5!1s0x3141f7b5026661c9:0x847bab3e51ad7ea2!8m2!3d15.9951364!4d107.9961392!16s%2Fm%2F0gfdsrk?entry=ttu&g_ep=EgoyMDI1MDMyMy4wIKXMDSoASAFQAw%3D%3D");
            }
            else if (i == 3) {
                googleMapsLinks.add("https://www.google.com/maps/place/C%C3%B4ng+vi%C3%AAn+Bi%E1%BB%83n+%C4%90%C3%B4ng/@16.0703173,108.2432619,17z/data=!3m1!4b1!4m6!3m5!1s0x3142171781bc61df:0x88236faf8a5f84f8!8m2!3d16.0703173!4d108.2458368!16s%2Fg%2F11fsn2h8z8?entry=ttu&g_ep=EgoyMDI1MDMyMy4wIKXMDSoASAFQAw%3D%3D");
            }
            event.setGoogleMapsLinks(googleMapsLinks);
        }

        // Thêm sự kiện vào request
        request.setAttribute("events", events);
        
        
        request.setAttribute("bookstores", bookstores);
        request.getRequestDispatcher(ProjectPaths.JSP_SERVICE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ProjectPaths.JSP_SERVICE_PATH).forward(request, response);
    }
}
