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
import java.util.List;
import startup.zeroli.model.User;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="ChatServlet", urlPatterns={"/ChatServlet"})
public class ChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

//        List<User> friends = UserDAO.getFriends(user.getId());
//        List<Message> messages = MessageDAO.getMessagesByUserId(user.getId());
//
//        request.setAttribute("friends", friends);
//        request.setAttribute("messages", messages);
        request.getRequestDispatcher("chat.jsp").forward(request, response);
    }
}