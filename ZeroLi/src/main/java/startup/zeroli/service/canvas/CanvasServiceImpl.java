package startup.zeroli.service.canvas;

import startup.zeroli.model.Canvas;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CanvasServiceImpl implements CanvasService {
    private final int option = 0;


    // 0 : txt file
    // 1 : JPA
    String filePath = "C:\\Users\\ADMIN\\Desktop\\ZeroLi\\ZeroLi\\src\\main\\java\\startup\\zeroli\\service\\canvas\\data_with_ids.csv";
    String fileComment = "C:\\Users\\ADMIN\\Desktop\\ZeroLi\\ZeroLi\\src\\main\\java\\startup\\zeroli\\service\\canvas\\comment.csv";

    @Override
    public List<Canvas> getAllCanvas() {
        List<Canvas> canvasList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

    @Override
    public List<Canvas> getCanvasImage() {
        List<Canvas> canvases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
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

    @Override
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

    @Override
    public Canvas getById(int id) {
        List<Canvas> canvasList = getAllCanvas();
        for (Canvas canvas : canvasList) {
            if (canvas.getId() == id) {
                return canvas;
            }
        }
        return null;
    }
    

    }