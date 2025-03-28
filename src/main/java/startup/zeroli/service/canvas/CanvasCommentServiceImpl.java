package startup.zeroli.service.canvas;

import startup.zeroli.model.CanvasComment;
import startup.zeroli.utils.ErrDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import startup.zeroli.config.AbsolutePaths;

public class CanvasCommentServiceImpl implements CanvasCommnetService {
    
    @Override
    public List<CanvasComment> listCommentsById(int id) {
        List<CanvasComment> comments = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.COMMENT_CANVAS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\*\\*\\*\\*", -1);
                if (values.length >= 4) {
                    try {
                        int commentId = Integer.parseInt(values[0].trim());
                        int canvasId = Integer.parseInt(values[1].trim());
                        if (canvasId == id) {
                            String username = values[2].trim();
                            String review = values[3].trim();
                            if (!review.isEmpty()) {
                                List<String> reviews = new ArrayList<>();
                                reviews.add(review);
                                CanvasComment comment = new CanvasComment(commentId, username, reviews);
                                comments.add(comment);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Lỗi chuyển đổi ID trong dòng: " + line);
                    }
                }
            }
        } catch (IOException e) {
            ErrDialog.showFullError(e.getMessage());
        }
        return comments;
    }

    @Override
    public void addComment(int canvasId, String username, String commentText) {
        int newCommentId = getNextCommentId();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AbsolutePaths.COMMENT_CANVAS_PATH, true))) {
            bw.write(newCommentId + "****" + canvasId + "****" + username + "****" + commentText);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi bình luận: " + e.getMessage());
            throw new RuntimeException("Lỗi khi lưu bình luận", e);
        }
    }

    @Override
    public void deleteComment(int commentId) {
        List<String> lines = new ArrayList<>();
        boolean commentFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.COMMENT_CANVAS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\*\\*\\*\\*", -1);
                int id = Integer.parseInt(values[0].trim());
                if (id != commentId) {
                    lines.add(line);
                } else {
                    commentFound = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file bình luận: " + e.getMessage());
            throw new RuntimeException("Lỗi khi đọc file bình luận", e);
        }

        if (!commentFound) {
            throw new RuntimeException("Không tìm thấy bình luận với ID: " + commentId);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AbsolutePaths.COMMENT_CANVAS_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file bình luận: " + e.getMessage());
            throw new RuntimeException("Lỗi khi ghi file bình luận", e);
        }
    }

    @Override
    public void editComment(int commentId, String updatedComment) {
        List<String> lines = new ArrayList<>();
        boolean commentFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.COMMENT_CANVAS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\*\\*\\*\\*", -1);
                int id = Integer.parseInt(values[0].trim());
                if (id == commentId) {
                    lines.add(values[0] + "****" + values[1] + "****" + values[2] + "****" + updatedComment);
                    commentFound = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file bình luận: " + e.getMessage());
            throw new RuntimeException("Lỗi khi đọc file bình luận", e);
        }

        if (!commentFound) {
            throw new RuntimeException("Không tìm thấy bình luận với ID: " + commentId);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AbsolutePaths.COMMENT_CANVAS_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file bình luận: " + e.getMessage());
            throw new RuntimeException("Lỗi khi ghi file bình luận", e);
        }
    }

    private int getNextCommentId() {
        int maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(AbsolutePaths.COMMENT_CANVAS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\*\\*\\*\\*");
                if (values.length >= 1) {
                    int id = Integer.parseInt(values[0].trim());
                    if (id > maxId) maxId = id;
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file comment: " + e.getMessage());
        }
        return maxId + 1;
    }

}