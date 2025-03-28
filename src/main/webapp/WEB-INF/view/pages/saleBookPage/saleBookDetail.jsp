<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Detail - ${book.bookName}</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 1200px;
                margin: 20px auto;
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 15px rgba(0,0,0,0.1);
            }
            .book-header {
                display: flex;
                margin-bottom: 30px;
            }
            .book-image-container {
                flex: 0 0 300px;
                padding: 15px;
                border: 1px solid #eee;
                border-radius: 5px;
                margin-right: 30px;
            }
            .book-image {
                width: 100%;
                height: auto;
                max-height: 400px;
                object-fit: contain;
            }
            .book-info {
                flex: 1;
            }
            .book-title {
                font-size: 28px;
                font-weight: bold;
                color: #333;
                margin-bottom: 10px;
            }
            .book-author {
                font-size: 18px;
                color: #555;
                margin-bottom: 15px;
            }
            .book-price {
                font-size: 24px;
                color: #e63946;
                font-weight: bold;
                margin: 15px 0;
            }
            .book-meta {
                margin: 20px 0;
            }
            .meta-item {
                margin-bottom: 8px;
                font-size: 16px;
            }
            .meta-label {
                font-weight: bold;
                color: #333;
                display: inline-block;
                width: 120px;
            }
            .book-description {
                margin-top: 30px;
                padding-top: 20px;
                border-top: 1px solid #eee;
            }
            .description-title {
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 15px;
                color: #333;
            }
            .description-content {
                line-height: 1.6;
                text-align: justify;
            }
            .back-button {
                display: inline-block;
                padding: 10px 20px;
                background-color: #0066cc;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 20px;
                transition: background-color 0.3s;
            }
            .back-button:hover {
                background-color: #0052a3;
            }





            .momo-payment-button:hover {
                background-color: #8A0056 !important;
            }

            .payment-options {
                margin-top: 25px;
                border-top: 1px solid #eee;
                padding-top: 20px;
            }
        </style>
    </head>
    <body>

        <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>

        <div class="container">
            <div class="book-header">
                <div class="book-image-container">
                    <img src="${book.imageBook}" alt="${book.bookName}" class="book-image">
                </div>
                <div class="book-info">
                    <h1 class="book-title">${book.bookName}</h1>
                    <div class="book-author">Tác giả: ${book.author}</div>
                    <div class="book-price">Giá: ${book.price} VNĐ</div>

                    <div class="book-meta">
                        <div class="meta-item">
                            <span class="meta-label">Nhà xuất bản:</span>
                            <span>${book.publisher}</span>
                        </div>
                        <div class="meta-item">
                            <span class="meta-label">Nhà cung cấp:</span>
                            <span>${book.supplier}</span>
                        </div>
                        <div class="meta-item">
                            <span class="meta-label">Năm xuất bản:</span>
                            <span>${book.publishYear}</span>
                        </div>
                        <div class="meta-item">
                            <span class="meta-label">Thể loại:</span>
                            <span>${book.category}</span>
                        </div>
                    </div>

                    <!--                <form action="cart" method="post">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="bookId" value="${book.bookId}">
                                        <button type="submit" class="back-button">Thêm vào giỏ hàng</button>
                                    </form>-->

                    <div class="payment-options">
                        <form action="payment" method="post" id="momo-payment-form">
                            <input type="hidden" name="action" value="process-momo-payment">
                            <input type="hidden" name="bookId" value="${book.bookId}">
                            <input type="hidden" name="amount" value="${book.price}">
                            <input type="hidden" name="bookName" value="${book.bookName}">

                            <button type="submit" class="momo-payment-button" style="
                                    background-color: #A50064;
                                    color: white;
                                    border: none;
                                    padding: 12px 24px;
                                    border-radius: 5px;
                                    font-size: 16px;
                                    cursor: pointer;
                                    margin-top: 15px;
                                    display: flex;
                                    align-items: center;
                                    gap: 10px;">
                                <img src="data:image/png;base64,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" 
                                     alt="MoMo" style="width: 30px;">
                                Thanh toán bằng MoMo
                            </button>
                        </form>
                    </div>


                </div>
            </div>

            <div class="book-description">
                <h2 class="description-title">Mô tả sản phẩm</h2>
                <div class="description-content">${book.descriptions}</div>
            </div>

            <a href="javascript:history.back()" class="back-button">Quay lại</a>
        </div>
    </body>
</html>