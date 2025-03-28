package startup.zeroli.service.canvas;

import startup.zeroli.model.CanvasComment;

import java.util.List;

public interface CanvasCommnetService {
    List<CanvasComment> listCommentsById(int id);
    void addComment(int canvasId, String username, String commentText);
    void deleteComment(int commentId);
    void editComment(int commentId, String updatedComment);
}