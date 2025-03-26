/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package startup.zeroli.controller.redirectController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import startup.zeroli.config.JSPFFName;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.User;
import startup.zeroli.service.user.UserServiceImpl;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "LoginPageServlet", urlPatterns = {MainControllerServlet.LOGINPAGE_SERVLET})
public class LoginPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ProjectPaths.JSP_LOGINPAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action").trim();

        if (action.equals(MainControllerServlet.ACTION_LOGIN)) {
            login(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(JSPFFName.EMAIL);
        String passw = request.getParameter(JSPFFName.PASSWORD);
        User user = new UserServiceImpl().getUserByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(passw)) {
                HttpSession session = request.getSession(true);
                session.setAttribute(MainControllerServlet.SS_ATTR_USER, user);
                request.getRequestDispatcher(MainControllerServlet.HOMEPAGE_REDIRECT).forward(request, response);
            } else {
                request.setAttribute("ERROR", "Wrong password for this email!");
                request.getRequestDispatcher(ProjectPaths.JSP_LOGINPAGE_PATH).forward(request, response);
            }
        } else {
            request.setAttribute("ERROR", "Email does not exist!");
            request.getRequestDispatcher(ProjectPaths.JSP_LOGINPAGE_PATH).forward(request, response);
        }
    }

}
