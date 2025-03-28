package startup.zeroli.controller.paymentController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.model.SaleBook;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import startup.zeroli.service.saleBook.SaleBookServiceImpl;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    private SaleBookServiceImpl saleBookService;

    @Override
    public void init() throws ServletException {
        super.init();
        saleBookService = new SaleBookServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("process-momo-payment".equals(action)) {
            processMomoPayment(request, response);
        }
    }

    private void processMomoPayment(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        try {
            String bookId = request.getParameter("bookId");
            SaleBook book = saleBookService.getBookById(Integer.parseInt(bookId));
            
            if (book == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
                return;
            }

            // Thông tin cấu hình Momo
            String partnerCode = "MOMO"; // Thay bằng partner code của bạn
            String accessKey = "F8BBA842ECF85"; // Thay bằng access key của bạn
            String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz"; // Thay bằng secret key của bạn
            String requestId = UUID.randomUUID().toString();
            String orderId = "ORDER_" + System.currentTimeMillis();
            String orderInfo = "Thanh toán cho sách: " + book.getBookName();
            String redirectUrl = "http://yourdomain.com/payment-success"; // URL sau khi thanh toán thành công
            String ipnUrl = "http://yourdomain.com/momo-ipn"; // URL nhận kết quả thanh toán từ Momo
            String amount = book.getPrice().replaceAll("[^0-9]", ""); // Lấy số từ chuỗi giá
            String requestType = "captureWallet";
            
            // Tạo chữ ký
            String rawHash = "accessKey=" + accessKey +
                    "&amount=" + amount +
                    "&extraData=" +
                    "&ipnUrl=" + ipnUrl +
                    "&orderId=" + orderId +
                    "&orderInfo=" + orderInfo +
                    "&partnerCode=" + partnerCode +
                    "&redirectUrl=" + redirectUrl +
                    "&requestId=" + requestId +
                    "&requestType=" + requestType;
            
            String signature = hmacSHA256(rawHash, secretKey);
            
            // Tạo JSON request
            String jsonRequest = "{" +
                    "\"partnerCode\":\"" + partnerCode + "\"," +
                    "\"partnerName\":\"Test\"," +
                    "\"storeId\":\"BookStore\"," +
                    "\"requestId\":\"" + requestId + "\"," +
                    "\"amount\":" + amount + "," +
                    "\"orderId\":\"" + orderId + "\"," +
                    "\"orderInfo\":\"" + orderInfo + "\"," +
                    "\"redirectUrl\":\"" + redirectUrl + "\"," +
                    "\"ipnUrl\":\"" + ipnUrl + "\"," +
                    "\"lang\":\"vi\"," +
                    "\"extraData\":\"\"," +
                    "\"requestType\":\"" + requestType + "\"," +
                    "\"signature\":\"" + signature + "\"" +
                    "}";
            
            // Gửi request đến API Momo
            String momoEndpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
            String result = sendPostRequest(momoEndpoint, jsonRequest);
            
            // Chuyển hướng đến trang thanh toán Momo
            response.sendRedirect(getPaymentUrlFromResponse(result));
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("payment-error.jsp");
        }
    }

    private String hmacSHA256(String data, String secretKey) throws Exception {
        // Implement HMAC SHA256
        // Bạn có thể sử dụng thư viện hoặc tự implement
        // Đây là ví dụ đơn giản, bạn nên sử dụng thư viện bảo mật
        javax.crypto.Mac sha256_HMAC = javax.crypto.Mac.getInstance("HmacSHA256");
        javax.crypto.spec.SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    private String sendPostRequest(String endpoint, String json) throws IOException {
        // Implement HTTP POST request
        java.net.URL url = new java.net.URL(endpoint);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        
        try (java.io.OutputStream os = conn.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        
        try (java.io.BufferedReader br = new java.io.BufferedReader(
                new java.io.InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    private String getPaymentUrlFromResponse(String jsonResponse) {
        // Parse JSON response để lấy payUrl
        com.google.gson.JsonObject jsonObject = new com.google.gson.JsonParser().parse(jsonResponse).getAsJsonObject();
        return jsonObject.get("payUrl").getAsString();
    }
}