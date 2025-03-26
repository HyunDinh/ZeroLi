package startup.zeroli.controller.redirectController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.User;
import startup.zeroli.utils.ErrDialog;

@WebServlet(name = "ProfilePageServlet", urlPatterns = {MainControllerServlet.PROFILEPAGAE_SERVLET})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ProfilePageServlet extends HttpServlet {

    // Đường dẫn tuyệt đối đến thư mục lưu avatar
    private static final String AVATAR_DIR = "D:\\code\\University\\ZeroLi\\src\\main\\webapp\\resources\\avatarUser\\";
    
    // Đường dẫn tương đối để hiển thị trong JSP
    private static final String AVATAR_RELATIVE_DIR = "resources/avatarUser/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute(MainControllerServlet.SS_ATTR_USER);
        
        if (u == null) {
            response.sendRedirect(request.getContextPath() + MainControllerServlet.LOGINPAGE_SERVLET);
            return;
        }

        // Kiểm tra xem có avatar không
        String avatarPath = AVATAR_DIR + u.getEmail() + ".png";
        boolean hasAvatar = new File(avatarPath).exists();
        
        request.setAttribute("hasAvatar", hasAvatar);
        request.setAttribute("user", u);
        request.getRequestDispatcher(ProjectPaths.JSP_PROFILEPAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute(MainControllerServlet.SS_ATTR_USER);

        if (u == null) {
            response.sendRedirect(request.getContextPath() + MainControllerServlet.LOGINPAGE_SERVLET);
            return;
        }

        // Xử lý upload avatar
        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            handleAvatarUpload(u.getEmail(), filePart);
            request.setAttribute("success", "Cập nhật avatar thành công");
        }

        doGet(request, response);
    }

    private void handleAvatarUpload(String email, Part filePart) throws IOException {
        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(AVATAR_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = email + ".png";
        String filePath = AVATAR_DIR + fileName;

        // Xóa file cũ nếu tồn tại
        File oldFile = new File(filePath);
        if (oldFile.exists()) {
            oldFile.delete();
        }

        // Lưu file mới
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}