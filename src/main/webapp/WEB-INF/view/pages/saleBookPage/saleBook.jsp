<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="startup.zeroli.config.ProjectPaths" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sale Book</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }

            .supplier-filter {
                display: flex;
                align-items: center;
                gap: 10px;
                background: #f8f9fa;
                padding: 15px;
                border-radius: 8px;
                border: 1px solid #ddd;
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
                max-width: 55%;
                margin: 20px auto;
            }

            .supplier-filter label {
                font-weight: bold;
                color: #333;
            }

            .supplier-filter select {
                background-color: white;
                /* Màu nền mặc định */
                border: 2px solid #f0ad4e;
                /* Màu viền vàng */
                padding: 10px;
                border-radius: 6px;
                cursor: pointer;
                transition: border-color 0.3s, box-shadow 0.3s;
            }

            /* Khi bấm vào dropdown */
            .supplier-filter select:focus {
                background-color: #fff3cd;
                /* Màu nền vàng nhạt */
                border-color: #ffc107;
                /* Màu viền vàng đậm */
                box-shadow: 0 0 5px rgba(255, 193, 7, 0.75);
            }

            .supplier-filter select:focus {
                outline: none;
                border-color: #e4ca3b;
                box-shadow: 0 0 5px rgba(213, 201, 124, 0.75);
            }

            .supplier-filter select option:hover {
                background-color: #ffeb99 !important;
                /* Màu vàng khi hover */
            }

            /* Đổi màu nền của option được chọn */
            .supplier-filter select option:checked {
                background-color: #f0ad4e !important;
                /* Màu vàng khi chọn */
                color: black;
            }

            .book-list {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 20px;
            }

            .book-card {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 15px;
                transition: transform 0.3s;
                height: 100%;
            }

            .book-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .book-image {
                width: 100%;
                height: 200px;
                object-fit: contain;
                margin-bottom: 10px;
            }

            .book-title {
                font-weight: bold;
                margin-bottom: 5px;
                color: #0066cc;
            }

            .book-price {
                color: #e63946;
                font-weight: bold;
                margin: 5px 0;
            }

            .book-supplier {
                font-style: italic;
                color: #666;
            }

            .no-books {
                text-align: center;
                padding: 20px;
                color: #666;
            }
        </style>
    </head>
    <body>

        <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>

        <div class="container">
            <h1>Book Suppliers</h1>

            <div class="supplier-filter">
                <form action="main" method="post">
                    <input type="hidden" name="action" value="search-booksupplier-products">
                    <label for="supplier">Chọn nhà cung cấp:</label>
                    <select name="supplier" id="supplier" onchange="this.form.submit()">
                        <option value="">-- Tất cả nhà cung cấp --</option>
                        <c:forEach items="${suppliers}" var="supplier">
                            <option value="${supplier}" ${supplier eq selectedSupplier ? 'selected' : ''}>
                                ${supplier}
                            </option>
                        </c:forEach>
                    </select>
                </form>
            </div>

            <div class="book-list">
                <c:choose>
                    <c:when test="${empty books}">
                        <div class="no-books">
                            <p>Không có sách nào thuộc nhà cung cấp này.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${books}" var="book">
                            <a href="<%= ProjectPaths.HREF_TO_SALEBOOKDETAILPAGE%>&id=${book.bookId}" style="text-decoration: none; color: inherit;">
                                <div class="book-card">
                                    <img src="${book.imageBook}" alt="${book.bookName}" class="book-image">
                                    <div class="book-title">${book.bookName}</div>
                                    <div class="book-author">Tác giả: ${book.author}</div>
                                    <div class="book-price">Giá: ${book.price}</div>
                                    <div class="book-supplier">Nhà cung cấp: ${book.supplier}</div>
                                </div>
                            </a>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>