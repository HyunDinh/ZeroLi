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
        <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response);%>

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
                        <h6 class="fw-bold" style="font-size: 12px; margin-bottom: 5px">Chính sách bán hàng</h6>
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
                        <h5 style="font-size: 2rem;margin-bottom:20px; font-family:sans-serif ">Đánh giá sản phẩm</h5>
                        <form action="main" method="post" class="review-form">
                            <input type="hidden" name="action" value="add-review">
                            <input type="hidden" name="productId" value="${product.productId}">

                            <label for="rating">Đánh giá:</label>
                            <select name="rating" id="rating" class="form-select">
                                <option value="5">5 ★</option>
                                <option value="4">4 ★</option>
                                <option value="3">3 ★</option>
                                <option value="2">2 ★</option>
                                <option value="1">1 ★</option>
                            </select>

                            <label for="comment">Nội dung đánh giá:</label>
                            <textarea name="comment" class="form-control" placeholder="Nhập đánh giá của bạn..." required></textarea>

                            <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                        </form>

                        <c:choose>
                            <c:when test="${not empty reviews}">
                                <c:forEach var="review" items="${reviews}">
                                    <div class="review-box" id="reviewBox-${review.viewId}">
                                        <div class="review-header">

                                            <div class="avatar">
                                                <c:set var="emailKey" value="${fn:replace(review.user.email, '@', '_at_')}"/>
                                                <img class="avatar-img" 
                                                     src="${pageContext.request.contextPath}/resources/avatarUser/${review.user.email}.png?version=${requestScope['lastModified_'.concat(emailKey)]}"
                                                     onerror="this.src='${pageContext.request.contextPath}/resources/images/default-avatar.png'"
                                                     alt="User Avatar">
                                            </div>

                                            <div class="user-info">
                                                <span class="userName">${review.user.username}</span>
                                                <div class="user-rating">
                                                    <span class="review-stars" id="ratingStars-${review.viewId}">
                                                        <c:forEach begin="1" end="${review.rating}">★</c:forEach>
                                                        <c:forEach begin="${review.rating + 1}" end="5">☆</c:forEach>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <p class="review-comment" id="commentText-${review.viewId}">${review.content}</p>

                                        <!-- Form edit va delete se khong xuat hien neu sessionScope.user.id != review.user.id -->
                                        <c:if test="${sessionScope.aaaaab.id == review.user.id}">
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
                                        </c:if>
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

        <!-- Keep the same CSS and JavaScript as before -->
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

            /* Container for layout */
            .container {
                width: 100%;
                max-width: 1200px; /* Standard container width */
                margin: 50px auto;
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
                transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #fff; /* Tạo nền trắng giúp ảnh dễ nhìn hơn */
                overflow: hidden; /* Tránh ảnh bị tràn ra ngoài khi phóng to */
            }

            .product-image:hover {
                transform: scale(1.05);
                box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2); /* Hiệu ứng bóng khi hover */
            }

            .product-title {
                font-size: 1.5rem;
                font-weight: 700;
                color: #333;
                margin-bottom: 15px;
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
                line-height: 1.8;
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
                margin-top: 10px;
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
                position: fixed;
                top: 70px;
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
                gap: 10px;
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
            /* Review Box */
            .review-box {
                background: #fff;
                border-radius: 10px;
                padding: 15px;
                margin-bottom: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: all 0.3s ease-in-out;
                position: relative;
            }

            .review-box:hover {
                transform: translateY(-3px);
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            }

            /* Header Review */
            .review-header {
                display: flex;
                align-items: center;
                gap: 12px;
                margin-bottom: 10px;
            }

            /* Avatar */
            .avatar {
                width: 40px;
                height: 40px;
                background: #007bff;
                color: white;
                font-weight: bold;
                font-size: 18px;
                display: flex;
                align-items: center;
                justify-content: center;
                border-radius: 50%;
                text-transform: uppercase;
            }

            .avatar-img {
                width: 50px !important;
                height: 50px !important;
                min-width: 50px !important;
                min-height: 50px !important;
                border-radius: 50% !important;
                aspect-ratio: 1/1 !important;
                object-fit: cover !important;
                display: block !important;
            }

            /* User Info */
            .user-info {
                flex-grow: 1;
            }

            .userName {
                font-weight: bold;
                font-size: 16px;
                color: #333;
            }

            .user-rating {
                display: flex;
                align-items: center;
                font-size: 16px;
                color: #FFD700; /* Màu vàng cho sao */
            }

            /* Review Stars */
            .review-stars {
                font-size: 18px;
                color: #f5c518;
                margin-left: 5px;
            }

            /* Nội dung review */
            .review-comment {
                font-size: 15px;
                color: #444;
                line-height: 1.5;
                margin-top: 5px;
            }

            /* Hành động (Sửa, Xóa) */
            .review-actions {
                margin-top: 10px;
                display: flex;
                gap: 10px;
            }

            .review-actions form {
                display: inline-block;
            }

            /* Button Styling */
            .review-actions button {
                padding: 6px 12px;
                font-size: 14px;
                border-radius: 5px;
                transition: all 0.2s;
            }

            .review-actions .btn-warning {
                background-color: #ffc107;
                border-color: #ffc107;
            }

            .review-actions .btn-warning:hover {
                background-color: #e0a800;
            }

            .review-actions .btn-danger {
                background-color: #dc3545;
                border-color: #dc3545;
            }

            .review-actions .btn-danger:hover {
                background-color: #c82333;
            }

            /* Edit Form Styling */
            .edit-form {
                margin-top: 10px;
                display: block;
            }

            .edit-form textarea {
                resize: vertical;
                min-height: 80px;
                width: 100%;
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 8px;
                font-size: 15px;
            }

            .edit-rating {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-top: 5px;
            }

            .edit-rating label {
                font-size: 0.9rem;
                color: #333;
            }

            /* Hiển thị khi chỉnh sửa */
            .review-box.editing .edit-form {
                display: block;
            }

            .review-box.editing .review-comment,
            .review-box.editing .review-actions {
                display: none;
            }

            /* Responsive */
            @media (max-width: 600px) {
                .review-box {
                    padding: 12px;
                }

                .review-actions {
                    flex-direction: column;
                }

                .review-actions button {
                    width: 100%;
                }
            }

            /* Review Form */
            .review-form {
                background: #fff;
                border-radius: 10px;
                padding: 20px;
                margin: 20px 0;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: all 0.3s ease-in-out;

            }

            .review-form:hover {
                transform: translateY(-3px);
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            }

            .review-form label {
                font-weight: bold;
                color: #333;
                display: block;
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .review-form .form-control,
            .review-form .form-select {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ddd;
                font-size: 16px;
                transition: 0.3s ease-in-out;
            }

            .review-form .form-control:focus,
            .review-form .form-select:focus {
                border-color: #007bff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            }

            .review-form textarea {
                resize: vertical;
                min-height: 100px;
            }

            .review-form button {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                font-weight: bold;
                margin-top: 15px;
                border-radius: 5px;
                transition: all 0.2s;
            }

            .review-form .btn-primary {
                background: #007bff;
                color: white;
                border: none;
            }

            .review-form .btn-primary:hover {
                background: #0056b3;
            }

            /* Responsive */
            @media (max-width: 600px) {
                .review-form {
                    padding: 15px;
                }

                .review-form button {
                    font-size: 14px;
                }
            }


        </style>

        <script>
            function editReview(reviewID) {
                // Ẩn đoạn văn bản cũ
                document.getElementById("commentText-" + reviewID).style.display = "none";
                // Ẩn nút sửa/xóa
                document.getElementById("reviewActions-" + reviewID).style.display = "none";
                // Hiện form sửa
                const editForm = document.querySelector("#reviewBox-" + reviewID + " .edit-form");
                editForm.classList.remove("d-none");
            }

            function cancelEdit(reviewID) {
                // Hiện lại đoạn văn bản cũ
                document.getElementById("commentText-" + reviewID).style.display = "block";
                // Hiện lại nút sửa/xóa
                document.getElementById("reviewActions-" + reviewID).style.display = "block";
                // Ẩn form sửa
                const editForm = document.querySelector("#reviewBox-" + reviewID + " .edit-form");
                editForm.classList.add("d-none");
            }

            function confirmDelete() {
                return confirm("Bạn có chắc muốn xóa đánh giá này?");
            }
        </script>
    </body>
</html>
