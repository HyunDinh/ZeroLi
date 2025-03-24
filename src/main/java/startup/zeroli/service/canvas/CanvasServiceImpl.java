package startup.zeroli.service.canvas;

import startup.zeroli.dao.CanvasDAO;
import startup.zeroli.model.Canvas;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CanvasServiceImpl implements CanvasService {
    private final CanvasDAO canvasDAO = new CanvasDAO();

    private final int option = 0;


    // 0 : txt file
    // 1 : JPA

    @Override
    public List<Canvas> getAllCanvas() {
        return canvasDAO.getAllCanvas();
    }

    @Override
    public List<Canvas> getCanvasImage() {
        return canvasDAO.getCanvasImage();
    }

    @Override
    public List<Canvas> getByCategory(String category) {
        return canvasDAO.getByCategory(category);
    }

    @Override
    public Canvas getById(int id) {
        return canvasDAO.getById(id);
    }


}