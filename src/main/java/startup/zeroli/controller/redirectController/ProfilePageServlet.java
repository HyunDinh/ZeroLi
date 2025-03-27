package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import startup.zeroli.config.AbsolutePaths;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.User;

@WebServlet(name = "ProfilePageServlet", urlPatterns = {MainControllerServlet.PROFILEPAGAE_SERVLET})
public class ProfilePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(MainControllerServlet.SS_ATTR_USER);
        
        // Clear cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String avatarPath = AbsolutePaths.AVATAR_DIR + user.getEmail() + ".png";
        File avatarFile = new File(avatarPath);
        boolean hasAvatar = avatarFile.exists();
        
        long lastModified = hasAvatar ? avatarFile.lastModified() : System.currentTimeMillis();
        
        request.setAttribute("hasAvatar", hasAvatar);
        request.setAttribute("user", user);
        request.setAttribute("lastModified", lastModified);
        
        // Forward to JSP
        request.getRequestDispatcher(ProjectPaths.JSP_PROFILEPAGE_PATH).forward(request, response);
    }
}