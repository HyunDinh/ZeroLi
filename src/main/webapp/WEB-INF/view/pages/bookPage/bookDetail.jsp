<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 15/03/2025
  Time: 5:28 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="startup.zeroli.controller.mainController.MainControllerServlet" %>
<%@ page import="startup.zeroli.config.ProjectPaths" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${book.bookName}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding-top: 150px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .breadcrumb {
            font-size: 14px;
            color: #666;
            margin-bottom: 20px;
        }
        .breadcrumb a {
            color: #1e90ff;
            text-decoration: none;
        }
        .book-detail {
            display: flex;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 30px;
        }
        .book-image {
            flex: 0 0 300px;
            margin-right: 30px;
        }
        .book-image img {
            width: 100%;
            height: auto;
            border-radius: 8px;
        }
        .book-info {
            flex: 1;
        }
        .book-info h1 {
            font-size: 28px;
            color: #333;
            margin: 0 0 10px;
        }
        .book-info .category {
            font-size: 14px;
            color: #1e90ff;
            margin-bottom: 10px;
        }
        .book-info .author {
            font-size: 16px;
            color: #666;
            margin-bottom: 20px;
        }
        .book-info .details {
            margin-bottom: 20px;
        }
        .book-info .details p {
            margin: 5px 0;
            font-size: 14px;
            color: #333;
        }
        .book-info .details p span {
            color: #666;
        }
        .book-info .read-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #e4b700;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            margin-right: 10px;
        }
        .book-info .read-btn:hover {
            background-color: #e4b700;
        }
        .related-books {
            margin-top: 30px;
        }
        .related-books h2 {
            font-size: 22px;
            color: #333;
            margin-bottom: 10px;
            border-bottom: 2px solid #e4b700;
            padding-bottom: 10px;
        }
        .related-books .carousel-container {
            position: relative;
            width: 100%;
            overflow: hidden;
        }
        .related-books .book-suggestions {
            display: flex;
            flex-wrap: nowrap;
            gap: 15px;
            overflow-x: auto;
            padding: 10px 0;
            scroll-behavior: smooth;
            -webkit-overflow-scrolling: touch;
        }
        .related-books .book-suggestions::-webkit-scrollbar {
            display: none;
        }
        .related-books .book-item {
            min-width: 180px;
            text-align: center;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.2s;
        }
        .related-books .book-item:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .related-books .book-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .related-books .book-item h3 {
            font-size: 14px;
            margin: 8px 0;
            color: #333;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            padding: 0 5px;
        }
        .related-books .book-item p {
            font-size: 12px;
            color: #666;
            margin: 0 0 8px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            padding: 0 5px;
        }
        .related-books .carousel-btn {
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
        .related-books .carousel-btn:hover {
            background-color: rgba(0, 0, 0, 0.7);
        }
        .related-books .prev-btn {
            left: 0;
        }
        .related-books .next-btn {
            right: 0;
        }
        @media (max-width: 768px) {
            .book-detail {
                flex-direction: column;
            }
            .book-image {
                flex: none;
                margin-right: 0;
                margin-bottom: 20px;
            }
            .related-books .book-item {
                min-width: 150px;
            }
        }
        .success-message {
            color: green;
            display: none;
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #d4edda;
            background-color: #f8f9fa;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="../../components/navbar.jsp" />
<%--<div class="container">--%>
    <div class="breadcrumb">
        <a href="<%= ProjectPaths.HREF_TO_BOOKPAGE%>">Trang Chủ</a> >
        <a href="<%= ProjectPaths.HREF_TO_BOOKPAGE%>">${book.category}</a> >
        <span>${book.bookName}</span>
    </div>
    <div class="book-detail">
        <div class="book-image">
            <img src="${book.imageBook}" alt="${book.bookName}" onerror="this.style.display='none';">
        </div>
        <div class="book-info">
            <h1>${book.bookName}</h1>
            <div class="category">Thể loại: <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_BOOK%>>">${book.category}</a></div>
            <div class="author">Tác giả: ${book.author}</div>
            <div class="details">
                <p>Định dạng: <span>Sách PDF</span></p>
                <p>Lượt xem: <span>${Math.floor(Math.random() * 1000) + 100}</span></p>
                <p>Kích thước: <span>${Math.floor(Math.random() * 5) + 1}.0 MB</span></p>
                <p>Số trang: <span>${Math.floor(Math.random() * 300) + 200}</span></p>
            </div>
            <!-- Thay form bằng liên kết <a> -->
            <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_FAVORITE_BOOK%>${book.bookId}" class="read-btn favorite-link">Thêm vào yêu thích</a>
<%--            <a href="${pageContext.request.contextPath}/main?action=${MainControllerServlet.BOOKDETAILPAGE_REDIRECT}&bookId=${book.bookId}&action=download" class="read-btn">Tải xuống</a>--%>
            <a href="${book.pdfPath}" class="read-btn">Đọc sách</a>
            <div id="favoriteMessage" class="success-message"></div>
        </div>
    </div>
    <div class="related-books">
        <h2>Gợi Ý Danh Mục</h2>
        <div class="carousel-container">
            <button class="carousel-btn prev-btn" onclick="scrollCarousel('related-carousel', -200)">❮</button>
            <div class="book-suggestions" id="related-carousel">
                <c:forEach var="relatedBook" items="${relatedBooks}">
                    <div class="book-item">
                        <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_BOOKDETAIL%>&bookId=${relatedBook.bookId}">
                            <img src="${relatedBook.imageBook}" alt="${relatedBook.bookName}" class="book-img" onerror="this.style.display='none';">
                            <h3>${relatedBook.bookName}</h3>
                            <p>${relatedBook.author}</p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <button class="carousel-btn next-btn" onclick="scrollCarousel('related-carousel', 200)">❯</button>
        </div>
    </div>
</div>
<script>
    function scrollCarousel(carouselId, scrollAmount) {
        const carousel = document.getElementById(carouselId);
        if (carousel) {
            const currentScroll = carousel.scrollLeft;
            const maxScroll = carousel.scrollWidth - carousel.clientWidth;
            let newScroll = currentScroll + scrollAmount;
            if (newScroll < 0) newScroll = 0;
            if (newScroll > maxScroll) newScroll = maxScroll;
            carousel.scrollTo({ left: newScroll, behavior: 'smooth' });
        } else {
            console.error("Không tìm thấy carousel với id: " + carouselId);
        }
    }

    // Xử lý click vào liên kết "Thêm vào yêu thích" với AJAX
    document.querySelectorAll('.favorite-link').forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // Ngăn chuyển hướng mặc định
            const url = this.href; // Lấy URL từ href của liên kết
            fetch(url, {
                method: "GET"
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response was not ok: " + response.statusText);
                    }
                    // Không cần parse JSON, chỉ hiển thị thông báo nếu request thành công
                    const favoriteMessageDiv = document.getElementById("favoriteMessage");
                    favoriteMessageDiv.style.display = "block";
                    favoriteMessageDiv.innerText = "Đã thêm sách vào danh sách yêu thích!";
                    setTimeout(() => { favoriteMessageDiv.style.display = "none"; }, 3000);
                })
                .catch(error => {
                    console.error("Error:", error);
                    const favoriteMessageDiv = document.getElementById("favoriteMessage");
                    favoriteMessageDiv.style.display = "block";
                    favoriteMessageDiv.innerText = "Đã có lỗi xảy ra!";
                    setTimeout(() => { favoriteMessageDiv.style.display = "none"; }, 3000);
                });
        });
    });
</script>
</body>
</html>