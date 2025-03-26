
<%-- 
    Document   : productDetailsPage
    Created on : Mar 15, 2025
    Author     : Grok 3 (xAI)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>ZEROLI - Product Details</title>

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    </head>
    <body>

        <div>
            <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="product-container">
                        <div class="row">
                            <div class="col-md-5">
                                <img src="${product.imageURL}" class="product-image" alt="${product.productName}">
                            </div>
                            <div class="col-md-7">
                                <h2 class="product-title">${product.productName}</h2>
                                <p class="price">
                                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /> VNĐ
                                </p>
                                <p class="product-info">
                                    <strong>Brand:</strong> ${product.brandName}<br>
                                    <strong>Stock:</strong> ${product.stock} units<br>
                                    <strong>Status:</strong> ${product.productStatus}<br>
                                    <strong>Description:</strong> ${product.description}
                                </p>
                                <div>
                                    <a href="cart?action=add&productID=${product.productId}" class="btn-buy">MUA NGAY</a>
                                    <a href="cart?action=add&productID=${product.productId}" class="btn-cart">THÊM VÀO GIỎ HÀNG</a>
                                </div>
                            </div>
                        </div>

                        <div class="gift-box">
                            <h5>🎁 Bạn sẽ nhận được:</h5>
                            <ul>
                                <li>Chuột HP Z3700 Wireless</li>
                                <li>Tai nghe HyperX Cloud Stinger Core II</li>
                                <li>Túi đựng laptop Targus 15.6"</li>
                                <li>Giảm 200.000đ khi mua phần mềm M365 Personal</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="sidebar-box">
                        <div class="company-info">
                            <h6>CÔNG TY CỔ PHẦN THƯƠNG MẠI DỊCH VỤ ZEROLI <span class="verified">✅</span></h6>
                        </div>
                        <h6 class="fw-bold">Chính sách bán hàng</h6>
                        <ul class="policy-list">
                            <li>Miễn phí giao hàng từ 5 triệu <a href="#">Xem chi tiết</a></li>
                            <li>Cam kết hàng chính hãng 100%</li>
                            <li>Đổi trả trong vòng 10 ngày <a href="#">Xem chi tiết</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="container mt-4">
            <div class="row">
                <div class="col-lg-8">
                    <div class="review-container">
                        <h5>Đánh giá sản phẩm</h5>

                        <form action="main" method="post" class="mb-3">
                            <input type="hidden" name="action" value="add-review">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <label for="userName">Tên người dùng:</label>
                            <input type="text" name="userName" class="form-control mt-2" placeholder="Nhập tên người dùng..." required>
                            <select name="rating" id="rating" class="form-select w-auto d-inline-block">
                                <option value="5">5 ★</option>
                                <option value="4">4 ★</option>
                                <option value="3">3 ★</option>
                                <option value="2">2 ★</option>
                                <option value="1">1 ★</option>
                            </select>
                            <textarea name="comment" class="form-control mt-2" placeholder="Nhập đánh giá của bạn..." required></textarea>

                            <button type="submit" class="btn btn-primary mt-2">Gửi đánh giá</button>
                        </form>

                        <c:choose>
                            <c:when test="${not empty reviews}">
                                <c:forEach var="review" items="${reviews}">
                                    <div class="review-box" id="reviewBox-${review.viewId}">
                                        <div class="review-header">
                                            <div class="avatar">
                                                <span>${fn:substring(review.userName, 0, 1)}</span>
                                            </div>
                                            <div class="user-info">
                                                <span class="userName">${review.userName}</span>
                                                <div class="user-rating">
                                                    <span class="review-stars" id="ratingStars-${review.viewId}">
                                                        <c:forEach begin="1" end="${review.rating}">★</c:forEach>
                                                        <c:forEach begin="${review.rating + 1}" end="5">☆</c:forEach>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <p class="review-comment" id="commentText-${review.viewId}">${review.content}</p>

                                        <form action="main" method="post" class="d-none edit-form">
                                            <input type="hidden" name="action" value="update-review">
                                            <input type="hidden" name="viewId" value="${review.viewId}">
                                            <input type="hidden" name="productId" value="${product.productId}">
                                            <textarea name="newContent" class="form-control mb-2">${review.content}</textarea>
                                            <div class="edit-rating mb-2">
                                                <select name="rating" id="rating" class="form-select w-auto d-inline-block">
                                                    <option value="5">5 ★</option>
                                                    <option value="4">4 ★</option>
                                                    <option value="3">3 ★</option>
                                                    <option value="2">2 ★</option>
                                                    <option value="1">1 ★</option>
                                                </select>
                                            </div>
                                            <div class="review-actions" id="reviewActions-${review.viewId}">
                                                <button type="submit" class="btn btn-sm btn-warning me-2" onclick="toggleEdit(${review.viewId})">Sửa</button>
                                            </div>
                                        </form>

                                        <div class="review-actions" id="reviewActions-${review.viewId}">
                                            <form action="main" method="post" class="d-inline" onsubmit="return confirmDelete();">
                                                <input type="hidden" name="action" value="delete-review">
                                                <input type="hidden" name="viewId" value="${review.viewId}">
                                                <input type="hidden" name="productId" value="${product.productId}">
                                                <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p class="text-muted">Chưa có đánh giá nào.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>                        

    </body>
</html>

<style>
    /* Reset (scoped to avoid navbar interference) */
    body, html {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        min-height: 100vh;
    }

    body {
        padding-top: 200px; /* Matches navbar height (100px logo + padding) */
    }

    /* Container for layout */
    .container {
        width: 100%;
        max-width: 1200px; /* Standard container width */
        margin: 0 auto;
        padding: 0 15px;
    }

    /* Row for flex layout */
    .row {
        display: flex;
        flex-wrap: wrap;
        margin: 0 -15px; /* Negative margin to offset column padding */
    }

    /* Columns */
    .col-lg-8, .col-lg-4 {
        padding: 0 15px; /* Standard padding for columns */
    }

    .col-lg-8 {
        flex: 0 0 66.666667%; /* 8/12 columns */
        max-width: 66.666667%;
    }

    .col-lg-4 {
        flex: 0 0 33.333333%; /* 4/12 columns */
        max-width: 33.333333%;
    }

    .col-md-3 {
        flex: 0 0 25%; /* 3/12 columns for footer */
        max-width: 25%;
    }

    .col-md-5, .col-md-7 {
        padding: 0 15px;
    }

    .col-md-5 {
        flex: 0 0 41.666667%; /* 5/12 columns */
        max-width: 41.666667%;
    }

    .col-md-7 {
        flex: 0 0 58.333333%; /* 7/12 columns */
        max-width: 58.333333%;
    }

    /* Product Container */
    .product-container {
        background: #fff;
        padding: 20px;
        border-radius: 15px;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }

    .product-image {
        width: 100%;
        height: 250px;
        object-fit: contain;
        border-radius: 10px;
        padding: 1rem;
        border: 1px solid #e0e0e0;
    }

    .product-title {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
    }

    .price {
        font-size: 1.6rem;
        font-weight: 700;
        color: #e02447;
        margin-bottom: 15px;
    }

    .product-info {
        font-size: 0.95rem;
        color: #555;
        margin-bottom: 15px;
        line-height: 1.6;
    }

    .product-info strong {
        color: #333;
        font-weight: 600;
    }

    .btn-buy {
        background: #e02447;
        border: none;
        border-radius: 20px;
        padding: 0.75rem 1.5rem;
        color: white;
        font-size: 0.9rem;
        font-weight: 500;
        transition: background-color 0.3s ease;
        text-decoration: none;
        display: inline-block;
        text-transform: uppercase;
        margin-right: 10px;
    }

    .btn-buy:hover {
        background: #c01e3d;
    }

    .btn-cart {
        background: #007bff;
        border: none;
        border-radius: 20px;
        padding: 0.75rem 1.5rem;
        color: white;
        font-size: 0.9rem;
        font-weight: 500;
        transition: background-color 0.3s ease;
        text-decoration: none;
        display: inline-block;
        text-transform: uppercase;
    }

    .btn-cart:hover {
        background: #0056b3;
    }

    /* Gift Box */
    .gift-box {
        margin-top: 20px;
        padding: 15px;
        border: 2px dashed #e02447;
        border-radius: 10px;
        background: #fff3f3;
    }

    .gift-box h5 {
        color: #e02447;
        font-weight: bold;
        font-size: 1rem;
        margin-bottom: 10px;
    }

    .gift-box ul {
        list-style: none;
        padding: 0;
    }

    .gift-box ul li {
        font-size: 0.9rem;
        padding: 5px 0;
        display: flex;
        align-items: center;
    }

    .gift-box ul li::before {
        content: "🎁";
        margin-right: 8px;
    }

    /* Sidebar */
    .sidebar-box {
        margin-top: 103px;
        background: white;
        padding: 15px;
        border-radius: 10px;
        box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        position: absolute;
        top: 100px;
    }

    .company-info {
        text-align: center;
        margin-bottom: 15px;
    }

    .company-info img {
        width: 50px;
        height: auto;
        margin-bottom: 5px;
    }

    .company-info h6 {
        font-weight: bold;
        color: #333;
        font-size: 0.9rem;
        margin: 0;
        line-height: 1.4;
    }

    .company-info h6 .verified {
        color: #28a745;
        font-size: 0.8rem;
    }

    .policy-list {
        list-style: none;
        padding: 0;
    }

    .policy-list li {
        padding: 5px 0;
        font-size: 0.9rem;
        display: flex;
        align-items: center;
    }

    .policy-list li::before {
        content: "✔️";
        margin-right: 8px;
    }

    .policy-list li a {
        text-decoration: none;
        color: #007bff;
        margin-left: 5px;
    }

    .policy-list li a:hover {
        text-decoration: underline;
    }

    /* Footer */
    footer {
        background: linear-gradient(135deg, #1e3a8a, #3b82f6);
        color: #f8f9fa;
        padding: 30px 0;
    }

    footer h6 {
        font-size: 1rem;
        margin-bottom: 15px;
    }

    footer ul {
        padding: 0;
    }

    footer ul li {
        list-style: none;
        margin-bottom: 8px;
    }

    footer ul li a {
        color: #bbb;
        text-decoration: none;
        transition: color 0.3s;
    }

    footer ul li a:hover {
        color: #007bff;
        text-decoration: underline;
    }

    footer .border-top {
        border-color: rgba(255, 255, 255, 0.2) !important;
    }

    .text-center img {
        height: 50px;
        border-radius: 50px;
    }

    /* Review Box */
    .review-box {
        border-bottom: 1px solid #ddd;
        padding: 15px 0;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .review-box:last-child {
        border-bottom: none;
    }

    /* Review Header */
    .review-header {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .user-info {
        display: flex;
        flex-direction: column;
    }

    .userName {
        color: #007bff;
        font-weight: bold;
        font-size: 1rem;
    }

    .review-stars {
        color: #f5c518;
        font-size: 1rem;
    }

    .review-comment {
        font-size: 1rem;
        color: #555;
        margin: 5px 0;
    }

    /* Edit Form Styling */
    .edit-form {
        margin-top: 10px;
    }

    .edit-form textarea {
        resize: vertical;
        min-height: 80px;
        width: 100%; /* Đảm bảo textarea rộng toàn bộ */
    }

    .edit-rating {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .edit-rating label {
        font-size: 0.9rem;
        color: #333;
    }

    /* Review Actions */
    .review-actions {
        display: flex;
        gap: 10px;
    }

    .review-actions .btn {
        padding: 8px 15px;
        font-size: 0.9rem;
        border-radius: 5px;
    }

    .btn-warning {
        background-color: #ffc107;
        border: none;
        color: #212529;
    }

    .btn-warning:hover {
        background-color: #e0a800;
    }

    .btn-danger {
        background-color: #dc3545;
        border: none;
        color: white;
    }

    .btn-danger:hover {
        background-color: #c82333;
    }

    /* Đảm bảo căn chỉnh vị trí nút theo hàng ngang */
    .review-box {
        display: flex;
        flex-direction: column;
        gap: 5px; /* Khoảng cách giữa các nút */
    }

    .review-actions {
        display: flex;
        gap: 5px; /* Khoảng cách giữa nút Sửa và Xóa */
    }

    .btn-primary {
        background: #007bff;
        color: white;
        border: none;
    }

    .btn-outline-primary {
        background: white;
        color: #007bff;
        border: 1px solid #007bff;
    }

    /* Avatar */
    .avatar {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background-color: #4CAF50;
        color: white;
        font-size: 18px;
        text-transform: uppercase;
        margin-right: 10px;
    }

    /* Tên người dùng */
    .userName {
        color: #007bff;
        font-weight: bold;
    }

    /* Căn chỉnh tên người dùng và sao đánh giá */
    .user-rating {
        display: flex;
        align-items: center;
        justify-content: flex-start;
    }

    .review-stars {
        color: #f5c518;
        margin-left: 5px;
    }

    /* Đảm bảo tên người dùng nằm ở vị trí bên cạnh avatar */
    .userName {
        font-weight: bold;
        margin-right: 10px;
    }
</style>

<script>
    function editReview(reviewID) {
        // Ẩn đoạn văn bản cũ
        document.getElementById("commentText-" + reviewID).style.display = "none";
        // Hiện ô sửa đánh giá
        document.getElementById("editForm-" + reviewID).classList.remove("d-none");
    }

    function cancelEdit(reviewID) {
        // Hiện lại đoạn văn bản cũ
        document.getElementById("commentText-" + reviewID).style.display = "block";
        // Ẩn ô sửa đánh giá
        document.getElementById("editForm-" + reviewID).classList.add("d-none");
    }

    function saveEdit(reviewID) {
        // Lấy giá trị từ textarea
        var newComment = document.querySelector("#editForm-" + reviewID + " textarea").value;

        // Cập nhật nội dung hiển thị
        document.getElementById("commentText-" + reviewID).innerText = newComment;

        // Hiện lại đoạn văn bản cũ
        document.getElementById("commentText-" + reviewID).style.display = "block";
        // Ẩn ô sửa đánh giá
        document.getElementById("editForm-" + reviewID).classList.add("d-none");
    }

    function confirmDelete() {
        return confirm("Bạn có chắc muốn xóa đánh giá này?");
    }
</script>
