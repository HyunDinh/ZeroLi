<%-- 
    Document   : checkout
    Created on : Mar 25, 2025, 1:29:11 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zeroli - Thanh Toán</title>
        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <!-- Popup thông báo -->
        <div id="successPopup" class="popup">
            <div class="popup-content">
                <h4>localhost:9999 cho biết</h4>
                <p>Đặt hàng thành công!</p>
                <button id="closePopup" class="popup-btn">OK</button>
            </div>
        </div>

        <!-- Nhúng Navbar -->
        <div>
            <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>
        </div>

        <!-- Checkout Section -->
        <section class="checkout-section">
            <div class="container">
                <h2 class="margin-bottom-4 fw-bold text-primary">Thông tin thanh toán</h2>
                <div class="checkout-row">
                    <!-- Checkout Form -->
                    <div class="checkout-col-8">
                        <div class="checkout-form">
                            <h4 class="margin-bottom-3">Thông tin khách hàng</h4>
                            <form id="checkoutForm" action="main" method="POST" onsubmit="return validateForm()">
                                <input type="hidden" name="action" value="confirm-checkout">
                                <!-- Họ tên -->
                                <div class="margin-bottom-3">
                                    <label for="fullName" class="custom-label">Họ và tên *</label>
                                    <input type="text" class="custom-input" id="fullName" name="fullName" required>
                                    <div id="fullNameError" class="error-message">Vui lòng nhập họ tên.</div>
                                </div>

                                <!-- Số điện thoại -->
                                <div class="margin-bottom-3">
                                    <label for="phone" class="custom-label">Số điện thoại *</label>
                                    <input type="tel" class="custom-input" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="Nhập số điện thoại 10 số">
                                    <div id="phoneError" class="error-message">Vui lòng nhập số điện thoại hợp lệ (10 số).</div>
                                </div>

                                <!-- Địa chỉ -->
                                <div class="margin-bottom-3">
                                    <label for="address" class="custom-label">Địa chỉ *</label>
                                    <input type="text" class="custom-input" id="address" name="address" required placeholder="Nhập địa chỉ cụ thể (ví dụ: Số nhà, Phường, Quận, Thành phố)">
                                    <div id="addressError" class="error-message">Vui lòng nhập địa chỉ.</div>
                                </div>

                                <!-- Phương thức vận chuyển -->
                                <div class="margin-bottom-3">
                                    <label class="custom-label">Phương thức vận chuyển *</label>
                                    <div>
                                        <div class="custom-check">
                                            <input type="radio" class="custom-check-input" id="deliveryStandard" name="deliveryMethod" value="standard" checked required>
                                            <label class="custom-check-label" for="deliveryStandard">Giao hàng tiết kiệm - 27.000đ</label>
                                        </div>
                                        <div class="custom-check">
                                            <input type="radio" class="custom-check-input" id="deliveryNormal" name="deliveryMethod" value="normal">
                                            <label class="custom-check-label" for="deliveryNormal">Giao hàng tiêu chuẩn - 39.000đ</label>
                                        </div>
                                        <div class="custom-check">
                                            <input type="radio" class="custom-check-input" id="deliveryFast" name="deliveryMethod" value="fast">
                                            <label class="custom-check-label" for="deliveryFast">Giao hàng nhanh - 55.000đ</label>
                                        </div>
                                    </div>
                                    <div id="deliveryError" class="error-message">Vui lòng chọn phương thức vận chuyển.</div>
                                </div>

                                <!-- Lời nhắn -->
                                <div class="margin-bottom-3">
                                    <label for="note" class="custom-label">Lời nhắn cho Shop</label>
                                    <textarea class="custom-input" id="note" name="note" rows="3" placeholder="Để lại lời nhắn..."></textarea>
                                </div>

                                <!-- Phương thức thanh toán -->
                                <div class="margin-bottom-3">
                                    <label class="custom-label">Phương thức thanh toán *</label>
                                    <div>
                                        <div class="custom-check">
                                            <input type="radio" class="custom-check-input" id="paymentCash" name="paymentMethod" value="cash" checked required>
                                            <label class="custom-check-label" for="paymentCash">Thanh toán khi nhận hàng (COD)</label>
                                        </div>
                                        <div class="custom-check">
                                            <input type="radio" class="custom-check-input" id="paymentOnline" name="paymentMethod" value="online">
                                            <label class="custom-check-label" for="paymentOnline">Thanh toán online</label>
                                        </div>
                                    </div>
                                    <div id="paymentError" class="error-message">Vui lòng chọn phương thức thanh toán.</div>
                                </div>

                                <form action="main" method="POST" onsubmit="return validateForm()">
                                    <input type="hidden" name="action" value="confirm-checkout">

                                    <c:forEach var="entry" items="${cart}">
                                        <input type="hidden" name="productId" value="${entry.key.productId}">
                                        <input type="hidden" name="quantity" value="${entry.value}">
                                    </c:forEach>

                                    <button type="submit" class="custom-btn checkout-btn">Xác nhận thanh toán</button>
                                </form>

                            </form>
                        </div>
                    </div>

                    <!-- Cart Summary -->
                    <div class="checkout-col-4">
                        <div class="cart-summary">
                            <h4 class="margin-bottom-3">Tóm tắt đơn hàng</h4>
                            <c:if test="${empty cart}">
                                <p>Không có sản phẩm nào được chọn để thanh toán.</p>
                            </c:if>
                            <c:forEach var="entry" items="${cart.entrySet()}">
                                <c:set var="product" value="${entry.key}" />
                                <c:set var="quantity" value="${entry.value}" />
                                <div class="cart-item">
                                    <img src="${product.imageURL}" alt="${product.productName}" class="cart-item-img">
                                    <div class="cart-item-details">
                                        <h5>${product.productName}</h5>
                                        <p>Số lượng: ${quantity}</p>
                                        <p class="price"><fmt:formatNumber value="${sessionScope.totalPrice}" type="currency" currencySymbol="" groupingUsed="true" maxFractionDigits="0" /> VNĐ</p>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="summary-details">
                                <p>Tổng tiền hàng <span><fmt:formatNumber value="${sessionScope.totalPrice}" type="currency" currencySymbol="" groupingUsed="true" maxFractionDigits="0" /> VNĐ</span></p>
                                <p>Phí vận chuyển <span id="shippingFee">27.000</span></p>
                                <p class="total">Tổng thanh toán <span id="totalAmount"><fmt:formatNumber value="${sessionScope.totalPrice + 27000}" type="currency" currencySymbol="" groupingUsed="true" maxFractionDigits="0" /> VNĐ</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>

    <style>
        /* Reset mặc định */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            background-color: #f5f5f5;
            padding-bottom: 50px;
        }

        /* Container chính */
        .container {
            padding-top: 150px;
            max-width: 1200px;
            margin: 0 auto;
        }

        /* Tiêu đề */
        h2 {
            font-size: 28px;
            color: #2a5298;
            margin-bottom: 20px;
            font-weight: bold;
        }

        h4 {
            font-size: 20px;
            color: #2a5298;
            font-weight: 600;
        }

        /* Thay thế class Bootstrap */
        .checkout-row {
            display: flex;
            flex-wrap: wrap;
            margin-left: -15px;
            margin-right: -15px;
        }

        .checkout-col-8 {
            flex: 0 0 66.666667%;
            max-width: 66.666667%;
            padding-left: 15px;
            padding-right: 15px;
        }

        .checkout-col-4 {
            flex: 0 0 33.333333%;
            max-width: 33.333333%;
            padding-left: 15px;
            padding-right: 15px;
        }

        .margin-bottom-3 {
            margin-bottom: 1rem;
        }

        .margin-bottom-4 {
            margin-bottom: 1.5rem;
        }

        .fw-bold {
            font-weight: 700;
        }

        .text-primary {
            color: #2a5298;
        }

        .custom-label {
            font-weight: 500;
            color: #333;
            display: block;
            margin-bottom: 5px;
        }

        .custom-input {
            width: 100%;
            border-radius: 8px;
            border: 1px solid #d0d0d0;
            padding: 10px;
            font-size: 16px;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        .custom-input:focus {
            border-color: #2a5298;
            box-shadow: 0 0 5px rgba(42, 82, 152, 0.2);
            outline: none;
        }

        .custom-check {
            margin-bottom: 10px;
        }

        .custom-check-input {
            cursor: pointer;
            margin-right: 8px;
        }

        .custom-check-label {
            font-size: 16px;
            color: #555;
        }

        .custom-check-input:checked {
            background-color: #2a5298;
            border-color: #2a5298;
        }

        .custom-btn {
            display: inline-block;
            font-weight: 400;
            text-align: center;
            vertical-align: middle;
            user-select: none;
            border: 1px solid transparent;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: 0.25rem;
            transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }

        /* Checkout Section */
        .checkout-section {
            padding: 2rem 0;
            background-color: #f9f9f9;
        }

        .checkout-form {
            background: #fff;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
            border: 1px solid #e0e0e0;
            transition: box-shadow 0.3s ease;
        }

        .checkout-form:hover {
            box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);
        }

        .error-message {
            color: #dc3545;
            font-size: 14px;
            margin-top: 5px;
            display: none;
        }

        /* Cart Summary */
        .cart-summary {
            background: #fff;
            padding: 1.5rem;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
            border: 1px solid #e0e0e0;
            transition: box-shadow 0.3s ease;
        }

        .cart-summary:hover {
            box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);
        }

        .cart-item {
            display: flex;
            align-items: center;
            padding: 1rem 0;
            border-bottom: 1px solid #e8e8e8;
        }

        .cart-item:last-child {
            border-bottom: none;
        }

        .cart-item-img {
            width: 80px;
            height: auto;
            margin-right: 1rem;
            border-radius: 8px;
            object-fit: cover;
        }

        .cart-item-details h5 {
            font-size: 16px;
            font-weight: 600;
            color: #333;
            margin-bottom: 5px;
        }

        .cart-item-details p {
            font-size: 14px;
            color: #666;
            margin-bottom: 5px;
        }

        .cart-item-details .price {
            font-size: 16px;
            font-weight: 600;
            color: #ff2d55;
        }

        .summary-details {
            margin-top: 1rem;
            padding-top: 1rem;
            border-top: 1px solid #e8e8e8;
        }

        .summary-details p {
            display: flex;
            justify-content: space-between;
            font-size: 16px;
            margin-bottom: 10px;
            color: #333;
        }

        .summary-details .total {
            font-size: 20px;
            font-weight: 700;
            color: #ff2d55;
        }

        .summary-details .total span {
            color: #ff2d55;
        }

        /* Nút Xác nhận thanh toán */
        .checkout-btn {
            background-color: #2a5298;
            color: #fff;
            font-weight: 600;
            padding: 12px;
            border-radius: 8px;
            text-align: center;
            display: block;
            text-decoration: none;
            border: none;
            width: 100%;
            text-transform: uppercase;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .checkout-btn:hover {
            background-color: #1e3a6b;
            transform: translateY(-2px);
        }

        /* Popup */
        .popup {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }

        .popup-content {
            background-color: #333;
            color: #fff;
            padding: 20px;
            border-radius: 8px;
            width: 300px;
            text-align: center;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

        .popup-content h4 {
            font-size: 16px;
            margin-bottom: 10px;
            font-weight: normal;
        }

        .popup-content p {
            font-size: 14px;
            margin-bottom: 20px;
        }

        .popup-btn {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 8px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
        }

        .popup-btn:hover {
            background-color: #0056b3;
        }
    </style>

    <script>
        // Validate form
        function validateForm() {
            let isValid = true;

            // Reset error messages
            document.querySelectorAll('.error-message').forEach(error => error.style.display = 'none');

            // Check full name
            const fullName = document.getElementById('fullName').value.trim();
            if (!fullName) {
                document.getElementById('fullNameError').style.display = 'block';
                isValid = false;
            }

            // Check phone
            const phone = document.getElementById('phone').value.trim();
            const phonePattern = /^[0-9]{10}$/;
            if (!phone || !phonePattern.test(phone)) {
                document.getElementById('phoneError').style.display = 'block';
                isValid = false;
            }

            // Check address
            const address = document.getElementById('address').value.trim();
            if (!address) {
                document.getElementById('addressError').style.display = 'block';
                isValid = false;
            }

            // Check delivery method
            const deliveryMethod = document.querySelector('input[name="deliveryMethod"]:checked');
            if (!deliveryMethod) {
                document.getElementById('deliveryError').style.display = 'block';
                isValid = false;
            } else {
                let shippingFee;
                switch (deliveryMethod.value) {
                    case 'standard':
                        shippingFee = 27000;
                        break;
                    case 'normal':
                        shippingFee = 39000;
                        break;
                    case 'fast':
                        shippingFee = 55000;
                        break;
                }
                document.getElementById('shippingFee').textContent = shippingFee.toLocaleString('vi-VN') + ' VNĐ';
                const totalAmount = ${sessionScope.totalPrice} + shippingFee;
                document.getElementById('totalAmount').textContent = totalAmount.toLocaleString('vi-VN') + ' VNĐ';
            }

            // Check payment method
            const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked');
            if (!paymentMethod) {
                document.getElementById('paymentError').style.display = 'block';
                isValid = false;
            }

            return isValid;
        }

// Dynamic update when delivery method changes
        document.addEventListener("DOMContentLoaded", function () {
            // Initial calculation
            function updateShippingAndTotal() {
                const deliveryMethod = document.querySelector('input[name="deliveryMethod"]:checked');
                let shippingFee;

                switch (deliveryMethod.value) {
                    case "standard":
                        shippingFee = 27000;
                        break;
                    case "normal":
                        shippingFee = 39000;
                        break;
                    case "fast":
                        shippingFee = 55000;
                        break;
                }

                document.getElementById("shippingFee").textContent = shippingFee.toLocaleString('vi-VN') + ' VNĐ';
                const totalAmount = ${sessionScope.totalPrice} + shippingFee;
                document.getElementById("totalAmount").textContent = totalAmount.toLocaleString('vi-VN') + ' VNĐ';
            }

            // Add event listeners to all delivery radio buttons
            const deliveryRadios = document.querySelectorAll('input[name="deliveryMethod"]');
            deliveryRadios.forEach(radio => {
                radio.addEventListener("change", updateShippingAndTotal);
            });

            // Initial call to set correct values
            updateShippingAndTotal();
        });

        // Show popup on form submission
        window.onload = function () {
            const checkoutForm = document.getElementById('checkoutForm');
            if (checkoutForm) {
                checkoutForm.addEventListener('submit', function (event) {
                    if (!validateForm()) {
                        event.preventDefault();
                        return;
                    }
                    event.preventDefault();

                    // Hiển thị popup
                    const popup = document.getElementById('successPopup');
                    popup.style.display = 'flex';

                    // Đóng popup khi bấm nút OK
                    const closePopupBtn = document.getElementById('closePopup');
                    closePopupBtn.onclick = function () {
                        popup.style.display = 'none';
                        // Redirect to homepage after successful checkout
                        window.location.href = '<%= startup.zeroli.config.ProjectPaths.HREF_TO_HOMEPAGE %>';
                    };
                });
            }
        };
    </script>
</html>