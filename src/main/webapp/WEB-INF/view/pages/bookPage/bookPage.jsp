<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 14/03/2025
  Time: 10:26 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="startup.zeroli.config.ProjectPaths" %>
<%@ page import="startup.zeroli.controller.mainController.MainControllerServlet" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Sách</title>
    <style>
        body {
            padding-top: 150px; /* Để tránh chồng lên navbar */
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
        }
        .container {
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
        }
        h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
            border-bottom: 2px solid #ff5722; /* Viền cam giống giao diện bạn gửi */
            padding-bottom: 10px;
        }
        .category-section {
            margin-bottom: 30px;
        }
        .category-title {
            font-size: 22px;
            font-weight: bold;
            color: #333;
            padding: 10px 0;
            margin-bottom: 10px;
            border-bottom: 2px solid #ff5722; /* Viền cam */
        }
        .carousel-container {
            position: relative;
            width: 100%;
            overflow: hidden;
        }
        .book-suggestions {
            display: flex;
            flex-wrap: nowrap; /* Sắp xếp ngang, không xuống dòng */
            gap: 15px;
            overflow-x: auto;
            padding: 10px 0;
            scroll-behavior: smooth; /* Cuộn mượt */
            -webkit-overflow-scrolling: touch; /* Hỗ trợ cuộn trên iOS */
        }
        .book-suggestions::-webkit-scrollbar {
            display: none; /* Ẩn thanh cuộn mặc định */
        }
        .book-item {
            min-width: 180px; /* Độ rộng cố định cho mỗi sách */
            text-align: center;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.2s;
        }
        .book-item:hover {
            transform: scale(1.05); /* Hiệu ứng zoom khi hover */
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        .book-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .book-item h3 {
            font-size: 14px;
            margin: 8px 0;
            color: #333;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            padding: 0 5px;
        }
        .book-item p {
            font-size: 12px;
            color: #666;
            margin: 0 0 8px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            padding: 0 5px;
        }
        .btn {
            display: inline-block;
            padding: 6px 12px;
            background-color: #e4b700; /* Màu hồng như giao diện bạn gửi */
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 10px;
            font-size: 12px;
        }
        .btn:hover {
            background-color: #e4b700;
        }
        /* Nút cuộn trái/phải */
        .carousel-btn {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            background-color: rgba(0, 0, 0, 0.5);
            color: white;
            border: none;
            border-radius: 50%;
            width: 40px;
            height: 40px;
            font-size: 20px;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1;
            transition: background-color 0.3s;
        }
        .carousel-btn:hover {
            background-color: rgba(0, 0, 0, 0.7);
        }
        .prev-btn {
            left: 0;
        }
        .next-btn {
            right: 0;
        }
        /* Responsive */
        @media (max-width: 768px) {
            .book-item {
                min-width: 150px;
            }
        }
    </style>
</head>
<body>
<!-- Gọi navbar -->
<jsp:include page="../../components/navbar.jsp" />

<div class="container">
<%--    <h1>Gợi ý Cho bạn</h1>--%>
<%--    <p>Số lượng sách: ${booksByCategory.values().stream().mapToInt(List::size).sum()}</p>--%>

    <c:forEach var="entry" items="${booksByCategory}">
        <div class="category-section">
            <div class="category-title">${entry.key}</div>
            <div class="carousel-container">
                <!-- Nút cuộn trái -->
                <button class="carousel-btn prev-btn" onclick="scrollCarousel('${entry.key.replace(' ', '_')}-carousel', -200)">❮</button>
                <!-- Danh sách sách -->
                <div class="book-suggestions" id="${entry.key.replace(' ', '_')}-carousel">
                    <c:forEach var="book" items="${entry.value}">
                        <div class="book-item">
                            <img src="${book.imageBook}" alt="${book.bookName}" class="book-img" onerror="this.style.display='none';">
                            <h3>${book.bookName}</h3>
                            <p>${book.author}</p>
<%--                            <a href="<%= ProjectPaths.HREF_TO_BOOKDETAILPAGE%>&bookId=${book.bookId}" class="btn">Xem chi tiết</a>--%>
                            <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER%>"></a>
                            <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_BOOKDETAIL%>&bookId=${book.bookId}">
                        </div>
                    </c:forEach>
                </div>
                <!-- Nút cuộn phải -->
                <button class="carousel-btn next-btn" onclick="scrollCarousel('${entry.key.replace(' ', '_')}-carousel', 200)">❯</button>
            </div>
        </div>
    </c:forEach>
</div>

<script>
    function scrollCarousel(carouselId, scrollAmount) {
        const carousel = document.getElementById(carouselId);
        if (carousel) {
            // Lấy vị trí hiện tại và chiều rộng cuộn tối đa
            const currentScroll = carousel.scrollLeft;
            const maxScroll = carousel.scrollWidth - carousel.clientWidth;

            // Điều chỉnh scrollAmount để tránh cuộn quá giới hạn
            let newScroll = currentScroll + scrollAmount;
            if (newScroll < 0) newScroll = 0;
            if (newScroll > maxScroll) newScroll = maxScroll;

            carousel.scrollTo({
                left: newScroll,
                behavior: 'smooth' // Cuộn mượt
            });
        } else {
            console.error("Không tìm thấy carousel với id: " + carouselId);
        }
    }
</script>
</body>
</html>

