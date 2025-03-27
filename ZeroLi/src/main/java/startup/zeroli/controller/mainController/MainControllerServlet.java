package startup.zeroli.controller.mainController;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.model.Canvas;
import startup.zeroli.service.canvas.CanvasServiceImpl;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="MainControllerServlet", urlPatterns={"/main"})
public class MainControllerServlet extends HttpServlet {
    // display servlet list
    public static final String HOMEPAGE_REDIRECT = "homePage";
    public static final String LOGINPAGE_REDIRECT = "loginPage";
    public static final String CANVASPAGE_REDIRECT = "CanvasPage";
    public static final String CANVASDETAILPAGE_REDIRECT = "CanvasDetailPage";

    // redirect to each servlet
    public static final String HOMEPAGE_SERVLET = "/" + HOMEPAGE_REDIRECT;
    public static final String LOGINPAGE_SERVLET = "/" + LOGINPAGE_REDIRECT;
    public static final String CANVASPAGE_SERVLET = "/" + CANVASPAGE_REDIRECT;
    public static final String CANVASDETAILPAGE_SERVLET = "/" + CANVASDETAILPAGE_REDIRECT;
    // main?action=
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_SEARCH_CANVAS = "searchCanvas";
    public static final String ACTION_ADD_COMMENT_CANVAS = "addCommentCanvas";
    public static final String ACTION_DELETE_COMMENT = "deleteComment";
    public static final String ACTION_EDIT_COMMENT = "editComment";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action").trim();

        switch (action) {
            case ACTION_LOGIN ->
                    request.getRequestDispatcher(LOGINPAGE_REDIRECT).forward(request, response);
            case ACTION_ADD_COMMENT_CANVAS,ACTION_DELETE_COMMENT,ACTION_EDIT_COMMENT ->
                    request.getRequestDispatcher(CANVASDETAILPAGE_REDIRECT).forward(request, response);
            default ->
                    response.sendRedirect("errorAtMainController.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = HOMEPAGE_REDIRECT;
        }

        switch (action) {
            case HOMEPAGE_REDIRECT, LOGINPAGE_REDIRECT,CANVASPAGE_REDIRECT,CANVASDETAILPAGE_REDIRECT ->
                    request.getRequestDispatcher(action).forward(request, response);
            case ACTION_SEARCH_CANVAS -> {
                request.getRequestDispatcher(CANVASPAGE_REDIRECT).forward(request,response);
            }
//            default ->
//                    response.sendRedirect("errorAtMainController.jsp");
        }
    }
}
