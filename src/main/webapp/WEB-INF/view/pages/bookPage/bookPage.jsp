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
            @import url('https://fonts.googleapis.com/css2?family=Playball&display=swap');
            #title {
                text-align: center;
                margin: 30px 0;
                font-size: 3.5rem;
                font-family: "Playball";
                border: none;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
            }
            /* Định dạng chung */
            .container {
                width: 90%;
                margin: auto;
                font-family: Arial, sans-serif;
            }

            .category-section {
                margin-bottom: 40px;
                padding: 20px;
                background: #f9f9f9;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .category-title {
                font-size: 24px;
                font-weight: bold;
                color: #333;
                margin-bottom: 10px;
                text-align: center;
            }

            .carousel-container {
                position: relative;
                display: flex;
                align-items: center;
                overflow: hidden;
                padding: 10px;
                height: 35%;
            }

            .book-suggestions {
                display: flex;
                overflow-x: auto;
                scroll-behavior: smooth;
                white-space: nowrap;
                width: calc(100% - 50px);
            }

            .book-item {
                display: inline-block;
                width: 150px;
                margin: 10px;
                padding: 10px;
                background: #fff;
                border-radius: 8px;
                text-align: center;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s;
                position: relative;
            }
            .book-item a{
                text-decoration: none;
            }
            .book-item:hover {
                transform: scale(1.05);
            }

            .book-img {
                width: 100px;
                height: 150px;
                object-fit: cover;
                border-radius: 5px;
                margin-bottom: 10px;
            }

            h3 {
                font-size: 16px;
                margin: 5px 0;
                color: #444;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                max-width: 140px;
                text-align: center;
                position: relative;
            }

            h3:hover {
                white-space: normal;
                color: #black;
                padding: 5px;
            }

            p {
                font-size: 14px;
                color: #777;
            }

            .btn {
                display: inline-block;
                padding: 5px 10px;
                background: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 5px;
                transition: background 0.3s;
            }

            .btn:hover {
                background: #0056b3;
            }

            .carousel-btn {
                background: #e6b800;
                color: white;
                border: none;
                padding: 10px;
                cursor: pointer;
                font-size: 20px;
                border-radius: 50%;
                transition: background 0.3s;
            }

            .carousel-btn:hover {
                background: #0056b3;
            }

            .prev-btn {
                position: relative;
                left: 0;
                margin-right:  10px;

            }

            .next-btn {
                position: relative;
                right: 0;
                margin-left:  10px;
            }

        </style>
    </head>
    <body>
        <!-- Gọi navbar -->
        <jsp:include page="../../components/navbar.jsp" />


        <a href="#" style="text-decoration: none; color: black;
           ">
            <h1 id="title" >Thể loại sách
            </h1>
        </a> 

        <div class="container">
            <%--    <h1>Gợi ý Cho bạn</h1>--%>
            <%--    <p>Số lượng sách: ${booksByCategory.values().stream().mapToInt(List::size).sum()}</p>--%>

            <c:forEach var="entry" items="${booksByCategory}">
                <div class="category-section">
                    <div class="category-title">${entry.key}</div>
                    <div class="carousel-container">
                        <!-- Nút cuộn trái -->
                        <button class="carousel-btn prev-btn" onclick="scrollCarousel('${entry.key.replace(' ', '_')}-carousel', -200, event)">❮</button>

                        <!-- Danh sách sách -->
                        <div class="book-suggestions" id="${entry.key.replace(' ', '_')}-carousel">
                            <c:forEach var="book" items="${entry.value}">
                                <div class="book-item">
                                    <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_BOOKDETAIL%>&bookId=${book.bookId}">
                                        <img src="${book.imageBook}" alt="${book.bookName}" class="book-img" onerror="this.style.display='none';">
                                        <h3>${book.bookName}</h3>
                                        <p>${book.author}</p>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Nút cuộn phải -->
                        <button class="carousel-btn next-btn" onclick="scrollCarousel('${entry.key.replace(' ', '_')}-carousel', 200, event)">❯</button>

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
                    if (newScroll < 0)
                        newScroll = 0;
                    if (newScroll > maxScroll)
                        newScroll = maxScroll;

                    carousel.scrollTo({
                        left: newScroll,
                        behavior: 'smooth' // Cuộn mượt
                    });
                } else {
                    console.error("Không tìm thấy carousel với id: " + carouselId);
                }
            }

            function scrollCarousel(carouselId, scrollAmount, event) {
                if (event)
                    event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>

                const carousel = document.getElementById(carouselId);
                if (carousel) {
                    const currentScroll = carousel.scrollLeft;
                    const maxScroll = carousel.scrollWidth - carousel.clientWidth;
                    let newScroll = currentScroll + scrollAmount;

                    newScroll = Math.max(0, Math.min(newScroll, maxScroll));

                    carousel.scrollTo({
                        left: newScroll,
                        behavior: 'smooth'
                    });
                }
            }
        </script>
    </body>
</html>

