
<%-- 
    Document   : productDetailsPage
    Created on : Mar 15, 2025
    Author     : Grok 3 (xAI)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>ZEROLI - Product Details</title>

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

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

            /* Ensure content doesn't overlap with navbar */
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

        </style>
    </head>
    <body>

        <!-- Navbar (included, styles isolated) -->
        <div>
            <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>
        </div>

        <!-- Main Container -->
        <div class="container">
            <div class="row">
                <!-- Product Details -->
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

                        <!-- Gift Box -->
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

                <!-- Sidebar (Company Info and Policies) -->
                <div class="col-lg-4">
                    <div class="sidebar-box">
                        <div class="company-info">
                            <!--<img src="resources/logo_name.png" alt="ZeroLi Logo" height="100px">-->
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

        <%--<c:choose>
                    <c:when test="${not empty reviews}">
                        <c:forEach var="review" items="${reviews}">
                            <div class="review-box" id="reviewBox-${review.viewId}">
                                <p class="review-comment">${review.content}</p>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="text-muted">Chưa có đánh giá nào cho sản phẩm này.</p>
                    </c:otherwise>
                </c:choose>--%>
        <div class="container mt-4">
            <div class="row">
                <div class="col-lg-8">
                    <div class="review-container">
                        <h5>Đánh giá sản phẩm</h5>

                        <c:if test="${not empty sessionScope.userId and sessionScope.userRole == 'customer' and not hasReviewed}">
                            <form action="add-review" method="post" class="mb-3">
                                <input type="hidden" name="productID" value="${product.productID}">
                                <label for="rating">Đánh giá:</label>
                                <select name="rating" id="rating" class="form-select w-auto d-inline-block">
                                    <option value="5">5 ★</option>
                                    <option value="4">4 ★</option>
                                    <option value="3">3 ★</option>
                                    <option value="2">2 ★</option>
                                    <option value="1">1 ★</option>
                                </select>
                                <textarea name="comment" class="form-control mt-2" placeholder="Nhập đánh giá của bạn..."
                                          required></textarea>
                                <button type="submit" class="btn btn-primary mt-2">Gửi đánh giá</button>
                            </form>
                        </c:if>


                        <c:choose>
                            <c:when test="${not empty reviews}">
                                <c:forEach var="review" items="${reviews}">
                                    <div class="review-box" id="reviewBox-${review.reviewID}">


                                        <div class="review-header">
                                            <span class="review-stars">
                                                <c:forEach begin="1" end="${review.rating}">&#9733;</c:forEach>
                                                <c:forEach begin="${review.rating + 1}" end="5">&#9734;</c:forEach>
                                                </span>
                                            </div>
                                            <p class="review-comment" id="commentText-${review.reviewID}">${review.content}</p>
                                        <form id="editForm-${review.reviewID}" action="edit-review" method="post"
                                              class="d-none">
                                            <input type="hidden" name="reviewID" value="${review.reviewID}">
                                            <input type="hidden" name="productID" value="${product.productID}">
                                            <textarea name="newComment" class="form-control">${review.content}</textarea>
                                            <button type="submit" class="btn btn-sm btn-success mt-2"
                                                    onclick="saveEdit(${review.reviewID})">Lưu
                                            </button>
                                            <button type="button" class="btn btn-sm btn-secondary mt-2"
                                                    onclick="cancelEdit(${review.reviewID})">Hủy
                                            </button>
                                        </form>
                                        <div class="review-actions">
                                            <button type="button" class="btn btn-sm btn-warning"
                                                    onclick="editReview(${review.reviewID})">Sửa
                                            </button>
                                            <form action="delete-review" method="post" class="d-inline"
                                                  onsubmit="return confirmDelete();">
                                                <input type="hidden" name="reviewID" value="${review.reviewID}">
                                                <input type="hidden" name="productID" value="${product.productID}">
                                                <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                                            </form>
                                        </div>

                                    </div>
                                </c:forEach>

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
                            </c:when>
                            <c:otherwise>
                                <p class="text-muted">Chưa có đánh giá nào.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>                        


        <!-- Footer -->
        <footer>
            <div class="container">
                <div class="row">
                    <!-- Hỗ trợ khách hàng -->
                    <div class="col-md-3">
                        <h6>Hỗ trợ Khách hàng</h6>
                        <ul>
                            <li><a href="#">Thẻ ưu đãi</a></li>
                            <li><a href="#">Hướng dẫn mua online</a></li>
                            <li><a href="#">Ưu đãi doanh nghiệp</a></li>
                            <li><a href="#">Chính sách trả góp</a></li>
                            <li><a href="#">Dịch vụ sửa chữa</a></li>
                        </ul>
                    </div>

                    <!-- Chính sách mua hàng -->
                    <div class="col-md-3">
                        <h6>Chính sách mua hàng</h6>
                        <ul>
                            <li><a href="#">Điều kiện giao dịch</a></li>
                            <li><a href="#">Chính sách bảo hành</a></li>
                            <li><a href="#">Chính sách đổi trả</a></li>
                            <li><a href="#">Chính sách bảo mật</a></li>
                            <li><a href="#">Quy định đặt cọc</a></li>
                        </ul>
                    </div>

                    <!-- Thông tin về cửa hàng -->
                    <div class="col-md-3">
                        <h6>Thông tin ZEROLI</h6>
                        <ul>
                            <li><a href="#">Giới thiệu ZEROLI</a></li>
                            <li><a href="#">Hệ thống cửa hàng</a></li>
                            <li><a href="#">Trung tâm bảo hành</a></li>
                            <li><a href="#">Hỏi đáp</a></li>
                            <li><a href="#">Tuyển dụng</a></li>
                        </ul>
                    </div>

                    <!-- Liên hệ -->
                    <div class="col-md-3">
                        <h6>Liên hệ</h6>
                        <ul>
                            <li>Email: <a href="mailto:cskh@zerolip.vn">cskh@zeroli.vn</a></li>
                            <li>Hotline: <a href="tel:18006867">18006867</a></li>
                            <li>Facebook: <a href="#">ZEROLI</a></li>
                            <li>Zalo: <a href="#">OA ZEROLI</a></li>
                        </ul>
                    </div>
                </div>

                <!-- Phương thức thanh toán -->
                <div class="text-center mt-4">
                    <h6>Phương thức thanh toán</h6>
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqTUF7qFMSIVVx1kJ7OIvLlIcX_g9E36llUg&s" alt="Phương thức thanh toán" class="img-fluid" style="max-width: 300px;">
                </div>

                <!-- Bản quyền -->
                <div class="text-center border-top mt-3">
                    <p class="mb-0">© 1997 - 2025 CÔNG TY CỔ PHẦN THƯƠNG MẠI - DỊCH VỤ ZEROLI. All Rights Reserved.</p>
                </div>
            </div>
        </footer>
    </body>
</html>