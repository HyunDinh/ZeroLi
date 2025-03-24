package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.Canvas;
import startup.zeroli.service.canvas.CanvasServiceImpl;
import startup.zeroli.utils.ErrDialog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CanvasPageServlet", urlPatterns = {MainControllerServlet.CANVASPAGE_SERVLET})
public class CanvasControllerServlet extends HttpServlet {
    private final CanvasServiceImpl canvasService = new CanvasServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case MainControllerServlet.ACTION_SEARCH_CANVAS ->
                searchByCategory(req,resp);
            case MainControllerServlet.CANVASPAGE_REDIRECT ->{
                String id = req.getParameter("id");
                if (id != null && !id.isEmpty()){
                    showDetailCanvas(req,resp);
                } else {
                    showListCanvas(req,resp);
                }
            }
        }
    }

    private void showDetailCanvas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id của canvas");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }
        Canvas canvas = canvasService.getById(id);
        if (canvas == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy canvas");
            return;
        }
        request.setAttribute("selectedCanvas", canvas); // Truyền canvas chi tiết
        request.getRequestDispatcher(ProjectPaths.JSP_CANVASDETAILSPAGE_PATH).forward(request, response); // Chuyển đến CanvasPageDetails.jsp
    }

    private void showListCanvas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Canvas> canvas = canvasService.getAllCanvas();
        request.setAttribute("canvas", canvas);
        request.getRequestDispatcher(ProjectPaths.JSP_CANVASPAGE_PATH).forward(request, response);
    }

    private void searchByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Canvas> canvas = canvasService.getByCategory(search);
        request.setAttribute("canvas", canvas);
        request.getRequestDispatcher(ProjectPaths.JSP_CANVASPAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}