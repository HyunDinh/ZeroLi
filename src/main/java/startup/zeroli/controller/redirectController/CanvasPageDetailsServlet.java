package startup.zeroli.controller.redirectController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.config.ProjectPaths;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.service.canvas.CanvasCommentServiceImpl;

import java.io.IOException;
import startup.zeroli.utils.ErrDialog;

@WebServlet(name = "CanvasPageDetailsServlet", urlPatterns = {MainControllerServlet.CANVASDETAILPAGE_SERVLET})
public class CanvasPageDetailsServlet extends HttpServlet {

    private final CanvasCommentServiceImpl canvasCommentService = new CanvasCommentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case MainControllerServlet.ACTION_ADD_COMMENT_CANVAS ->
                addComment(request, response);
            case MainControllerServlet.ACTION_DELETE_COMMENT ->
                deleteComment(request, response);
            case MainControllerServlet.ACTION_EDIT_COMMENT ->
                editComment(request, response);
        }
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String canvasIdStr = request.getParameter("canvasId");
        String username = request.getParameter("username").trim();
        String commentText = request.getParameter("comment").trim();

        if (canvasIdStr == null || username.isEmpty() || commentText.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu thông tin cần thiết để thêm bình luận");
            return;
        }

        int canvasId;
        try {
            canvasId = Integer.parseInt(canvasIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID canvas không hợp lệ");
            return;
        }

        try {
            canvasCommentService.addComment(canvasId, username, commentText);
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
        response.sendRedirect(ProjectPaths.HREF_TO_CANVASPAGE + "&id=" + canvasId);
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentIdStr = request.getParameter("commentId");
        String canvasIdStr = request.getParameter("canvasId");

        if (commentIdStr == null || canvasIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters for comment deletion");
            return;
        }

        int commentId, canvasId;
        try {
            commentId = Integer.parseInt(commentIdStr);
            canvasId = Integer.parseInt(canvasIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
            return;
        }
        canvasCommentService.deleteComment(commentId);
        response.sendRedirect(ProjectPaths.HREF_TO_CANVASPAGE + "&id=" + canvasId);
    }

    private void editComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentIdStr = request.getParameter("commentId");
        String canvasIdStr = request.getParameter("canvasId");
        String updatedComment = request.getParameter("updatedComment").trim();

        if (commentIdStr == null || canvasIdStr == null || updatedComment.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters for comment edit");
            return;
        }

        int commentId, canvasId;
        try {
            commentId = Integer.parseInt(commentIdStr);
            canvasId = Integer.parseInt(canvasIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
            return;
        }

        try {
            canvasCommentService.editComment(commentId, updatedComment);
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
        response.sendRedirect(ProjectPaths.HREF_TO_CANVASPAGE + "&id=" + canvasId);
    }
}
