package startup.zeroli.controller.redirectController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "HomePageServlet", urlPatterns = {MainControllerServlet.HOMEPAGE_SERVLET})
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ProjectPaths.JSP_HOMEPAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ProjectPaths.JSP_HOMEPAGE_PATH).forward(request, response);
    }

}
