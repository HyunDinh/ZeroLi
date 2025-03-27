package startup.zeroli.controller.resourceController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import startup.zeroli.config.AbsolutePaths;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.User;

@WebServlet(name = "UserAvatarController", urlPatterns = {"/avatar"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class UserAvatarController extends HttpServlet {

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(MainControllerServlet.SS_ATTR_USER) == null) {
            out.print("Session expired. Please login again.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = (User) session.getAttribute(MainControllerServlet.SS_ATTR_USER);
        Part filePart = request.getPart("avatar");
        
        if (filePart != null && filePart.getSize() > 0) {
            try {
                handleAvatarUpload(user.getEmail(), filePart);
                session.setAttribute("success", "Avatar updated successfully");
                out.print("success");
                response.sendRedirect(ProjectPaths.HREF_TO_PROFILEPAGE);
            } catch (IOException e) {
                session.setAttribute("error", "Failed to update avatar: " + e.getMessage());
                out.print("Failed to update avatar: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            session.setAttribute("error", "No file selected");
            out.print("No file selected");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void handleAvatarUpload(String email, Part filePart) throws IOException {
        File uploadDir = new File(AbsolutePaths.AVATAR_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = email + ".png";
        String filePath = AbsolutePaths.AVATAR_DIR + fileName;

        // Delete old avatar if exists
        File oldFile = new File(filePath);
        if (oldFile.exists()) {
            Files.delete(oldFile.toPath());
        }

        // Save new avatar
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}