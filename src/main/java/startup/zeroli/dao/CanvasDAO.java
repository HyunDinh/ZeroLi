package startup.zeroli.dao;

import startup.zeroli.model.Canvas;
import startup.zeroli.service.canvas.CanvasServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import startup.zeroli.config.AbsolutePaths;

public class CanvasDAO {
    
        
    
    public List<Canvas> getAllCanvas() {
        List<Canvas> canvasList = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.CANVAS_PATH))) {
                    String line;
                    boolean isFirstLine = true;
                    while ((line = br.readLine()) != null) {
                        if (isFirstLine) {
                            isFirstLine = false;
                            continue;
                        }
                        String[] values = line.split("\\*\\*\\*\\*", -1);

                        if (values.length >= 7) {
                            int id = Integer.parseInt(values[0].trim());
                            Canvas canvas = new Canvas(
                                    id,
                                    values[1], // PageLink
                                    values[2], // ImgURL
                                    values[3], // Title
                                    values[4], // Desc
                                    values[5], // Category
                                    values[6]  // Author
                            );
                            canvasList.add(canvas);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi chuyển đổi ID thành số nguyên: " + e.getMessage());
                }
        return canvasList;
    }
    public List<Canvas> getCanvasImage() {
        List<Canvas> canvases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(AbsolutePaths.CANVAS_PATH), "UTF-8"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\*\\*\\*\\*", -1);
                if (values.length > 2) {
                    String imageUrl = values[2];
                    canvases.add(new Canvas(imageUrl));
                } else {
                    System.out.println(" Dòng " + lineNumber + "lỗi.");
                }
                lineNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return canvases;
    }
    public List<Canvas> getByCategory(String category) {
        List<Canvas> canvas = getAllCanvas();
        List<Canvas> canvas1 = new ArrayList<>();
        if (category == null || category.trim().isEmpty()) {
            return canvas1;
        }
        String lowerCategory = category.toLowerCase();
        for (Canvas canvas2 : canvas) {
            if (canvas2.getCategories() != null && canvas2.getCategories().toLowerCase().contains(lowerCategory)) {
                canvas1.add(canvas2);
            }
        }
        return canvas1;
    }
    public Canvas getById(int id) {
        List<Canvas> canvasList = getAllCanvas();
        for (Canvas canvas : canvasList) {
            if (canvas.getId() == id) {
                return canvas;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        CanvasServiceImpl service = new CanvasServiceImpl();
        List<Canvas> canvasList = service.getAllCanvas();
        System.out.println("Danh sách dữ liệu Canvas:");
        for (Canvas canvas : canvasList) {
            System.out.println("ID: " + canvas.getId());
            System.out.println("Author: " + canvas.getCategories());
            System.out.println("------------------------");
        }
    }
}
