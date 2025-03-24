package startup.zeroli.service.canvas;

import startup.zeroli.model.Canvas;

import java.util.List;

public interface CanvasService {
    List<Canvas> getAllCanvas();
    List<Canvas> getCanvasImage();
    List<Canvas> getByCategory(String category);
    Canvas getById(int id);
}