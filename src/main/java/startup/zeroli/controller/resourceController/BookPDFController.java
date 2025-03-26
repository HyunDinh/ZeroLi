package startup.zeroli.controller.resourceController;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "BookPDFController", urlPatterns = {"/bookPDF"})
public class BookPDFController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Đường dẫn tuyệt đối đến file PDF
        String absolutePath = "D:\\code\\University\\ZeroLi\\src\\main\\webapp\\resources\\bookpdf\\";
        String pdfName = request.getParameter("name").trim();
        String filePath = absolutePath + pdfName;

        File file = new File(filePath);
        if (!file.exists()) {
            ErrDialog.showFullError("file not found : " + filePath);
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        response.setContentLength((int) file.length());

        try (InputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }

}
