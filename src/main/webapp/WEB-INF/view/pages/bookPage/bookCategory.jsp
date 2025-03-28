<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 24/03/2025
  Time: 4:08 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="startup.zeroli.config.ProjectPaths" %>
<%@ page import="startup.zeroli.controller.mainController.MainControllerServlet" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sách theo danh mục</title>
    <style>
        body {
            padding-top: 150px;
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
            border-bottom: 2px solid #ff5722;
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
            border-bottom: 2px solid #ff5722;
        }
        .book-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
            gap: 15px;
            padding: 10px 0;
        }
        .book-item {
            text-align: center;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.2s;
            padding: 10px;
        }
        .book-item:hover {
            transform: scale(1.05);
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
            background-color: #e4b700;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 10px;
            font-size: 12px;
        }
        .btn:hover {
            background-color: #e4b700;
        }
        @media (max-width: 768px) {
            .book-item {
                min-width: 150px;
            }
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
    </style>
</head>
<body>
    <jsp:include page="../../components/navbar.jsp" />
<div class="breadcrumb">
    <a href="<%= ProjectPaths.HREF_TO_BOOKPAGE%>">Trang Chủ</a> >
    <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_BOOKDETAIL%>&bookId=${book.bookId}">${book.name}</a>
</div>

<div class="container">
    <h1>Sách thuộc danh mục: ${param.category}</h1>
    <c:forEach var="entry" items="${booksByCategory}">
        <div class="category-section">
            <div class="category-title">${entry.key}</div>
            <div class="book-grid">
                <c:forEach var="book" items="${entry.value}">
                    <div class="book-item">
                        <img src="${book.imageBook}" alt="${book.bookName}" class="book-img" onerror="this.style.display='none';">
                        <h3>${book.bookName}</h3>
                        <p>${book.author}</p>
                        <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_BOOKDETAIL%>&bookId=${book.bookId}" class="btn">Xem chi tiết</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    <c:if test="${empty booksByCategory}">
        <p>Không tìm thấy sách trong danh mục này.</p>
    </c:if>
</div>
</body>
</html>