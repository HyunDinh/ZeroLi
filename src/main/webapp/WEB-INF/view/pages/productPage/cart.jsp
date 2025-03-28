<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="startup.zeroli.config.ProjectPaths" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zeroli - Giỏ Hàng</title>
    </head>
    <body>
        <div>
            <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>
        </div>
        <!-- Cart Section -->
        <section class="cart-section">
            <div class="container">
                <h2>Giỏ hàng của bạn</h2>
                <div class="cart-layout">
                    <!-- Danh sách sản phẩm -->
                    <div class="cart-items">
                        <c:forEach var="entry" items="${cart.entrySet()}">
                            <c:set var="product" value="${entry.key}" />
                            <c:set var="quantity" value="${entry.value}" />
                            <div class="cart-item">
                                <!-- Thêm checkbox -->
                                <input type="checkbox" class="select-item" 
                                       data-product-id="${product.productId}" 
                                       data-price="${product.price}" 
                                       data-quantity="${quantity}" 
                                       onchange="updateTotalPrice()">
                                <img src="${product.imageURL}" alt="${product.productName}">
                                <div class="cart-item-details">
                                    <h5>${product.productName}</h5>
                                    <div class="quantity-control">
                                        <form action="main" method="POST">
                                            <input type="hidden" name="action" value="decrease-quantity">
                                            <input type="hidden" name="productId" value="${product.productId}">
                                            <button type="submit">-</button>
                                        </form>
                                        <span>${quantity}</span>
                                        <form action="main" method="POST">
                                            <input type="hidden" name="action" value="increase-quantity">
                                            <input type="hidden" name="productId" value="${product.productId}">
                                            <button type="submit">+</button>
                                        </form>
                                    </div>
                                    <p class="price">Giá: <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" maxFractionDigits="0" /> VNĐ</p>
                                    <p>Tổng: <fmt:formatNumber value="${product.price * quantity}" type="currency" currencySymbol="" groupingUsed="true" /> VNĐ</p>
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${empty cart}">
                            <p>Giỏ hàng của bạn trống.</p>
                        </c:if>
                    </div>

                    <!-- Tóm tắt đơn hàng -->
                    <div class="col-md-4">
                        <div class="summary-details">
                            <p>Tổng cộng <span><fmt:formatNumber value="${sessionScope.cartService.totalPrice}" type="currency" currencySymbol="" groupingUsed="true" maxFractionDigits="0" /> VNĐ</span></p>
                        </div>
                        <form id="checkoutForm" action="main" method="get">
                            <input type="hidden" name="action" value="checkoutPage">
                            <input type="hidden" name="userId" value="${sessionScope.userId != null ? sessionScope.userId : 0}">
                            <input type="hidden" name="selectedProductIds" id="checkoutSelectedProductIds">
                            <button type="submit" class="checkout-btn" onclick="prepareCheckout()">Mua Hàng</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>

<style>
    /* Reset mặc định */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
    }

    body {
        background-color: #f5f5f5;
    }

    /* Container chính */
    .container {
        padding-top: 150px;
        max-width: 1200px;
        margin: 0 auto;
    }
    .col-md-4 {
        flex: 0 0 33.33%; /* 4/12 columns */
        max-width: 33.33%;
    }

    /* Tiêu đề */
    h2 {
        font-size: 28px;
        color: #2a5298;
        margin-bottom: 20px;
        font-weight: bold;
    }

    /* Layout chính */
    .cart-layout {
        display: flex;
        gap: 20px;
    }

    /* Danh sách sản phẩm */
    .cart-items {
        flex: 2;
    }

    .cart-item {
        display: flex;
        align-items: center;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 15px;
        margin-bottom: 15px;
    }

    .cart-item input[type="checkbox"] {
        margin-right: 10px;
        width: 20px;
        height: 20px;
        cursor: pointer;
    }

    .cart-item input[type="checkbox"]:checked {
        accent-color: #2a5298; /* Màu xanh khi checkbox được chọn */
    }

    .cart-item img {
        width: 80px;
        height: auto;
        margin-right: 15px;
    }

    .cart-item-details {
        flex-grow: 1;
    }

    .cart-item-details h5 {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .cart-item-details .price {
        font-size: 16px;
        font-weight: bold;
        color: #333;
        margin: 5px 0;
    }

    .cart-item-details .total {
        font-size: 14px;
        color: #555;
    }

    /* Điều khiển số lượng */
    .quantity-control {
        display: flex;
        align-items: center;
        gap: 8px;
        margin: 5px 0;
    }

    .quantity-control button {
        text-decoration: none;
        padding: 5px 10px;
        border: 1px solid #2a5298;
        border-radius: 4px;
        color: #2a5298;
        font-weight: bold;
    }


    .quantity-control span {
        font-size: 16px;
        min-width: 30px;
        text-align: center;
    }

    /* Phần voucher */
    .voucher-section {
        margin-top: 10px;
    }

    .voucher-card {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border: 1px solid #ddd;
        padding: 10px;
        border-radius: 8px;
        background-color: #fff;
    }

    .voucher-text {
        font-size: 16px;
        font-weight: bold;
        color: #2a5298;
    }

    .voucher-btn {
        background-color: #2a5298;
        color: #fff;
        padding: 8px 15px;
        border-radius: 5px;
        text-decoration: none;
        font-weight: bold;
        display: flex;
        align-items: center;
        gap: 5px;
        cursor: pointer;
    }

    .voucher-btn:hover {
        background-color: #1e3a6b;
    }

    .voucher-btn .toggle-icon::before {
        content: '+';
    }

    .voucher-btn.selected .toggle-icon::before {
        content: '−';
    }

    .voucher-options {
        display: none;
        margin-top: 10px;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: #fff;
    }

    .voucher-options.show {
        display: block;
    }

    .voucher-option {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 8px 0;
        border-bottom: 1px solid #ddd;
    }

    .voucher-option:last-child {
        border-bottom: none;
    }

    .voucher-action-btn {
        padding: 5px 10px;
        border-radius: 5px;
        font-weight: bold;
        text-decoration: none;
        cursor: pointer;
        font-size: 16px;
        width: 35px;
        text-align: center;
    }

    .voucher-action-btn.apply {
        background-color: #28a745;
        color: #fff;
        display: inline-block;
    }

    .voucher-action-btn.apply:hover {
        background-color: #218838;
    }

    .voucher-action-btn.cancel {
        background-color: #dc3545;
        color: #fff;
        display: none;
    }

    .voucher-action-btn.cancel:hover {
        background-color: #c82333;
    }

    .voucher-option.applied .voucher-action-btn.apply {
        display: none;
    }

    .voucher-option.applied .voucher-action-btn.cancel {
        display: inline-block;
    }

    .voucher-actions {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
        margin-top: 10px;
    }

    .voucher-actions .btn-cancel,
    .voucher-actions .btn-confirm {
        padding: 8px 15px;
        border-radius: 5px;
        font-weight: bold;
        text-decoration: none;
    }

    .voucher-actions .btn-cancel {
        background-color: #dc3545;
        color: #fff;
    }

    .voucher-actions .btn-cancel:hover {
        background-color: #c82333;
    }

    .voucher-actions .btn-confirm {
        background-color: #28a745;
        color: #fff;
    }

    .voucher-actions .btn-confirm:hover {
        background-color: #218838;
    }

    .applied-voucher {
        color: #28a745;
        font-weight: bold;
        margin-top: 5px;
        display: none;
    }

    /* Tóm tắt đơn hàng */
    .summary-details {
        flex: 1;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 20px;
    }

    .summary-details p {
        display: flex;
        justify-content: space-between;
        font-size: 16px;
        margin-bottom: 10px;
    }

    .summary-details .total {
        font-size: 20px;
        font-weight: bold;
        color: #ff2d55;
    }

    .summary-details .total span {
        color: #ff2d55;
    }

    .checkout-btn {
        background-color: #2a5298;
        color: #fff;
        font-weight: bold;
        padding: 12px;
        border-radius: 8px;
        text-align: center;
        display: block;
        text-decoration: none;
        margin-top: 20px;
    }

    .checkout-btn:hover {
        background-color: #1e3a6b;
    }

</style>

<!-- Scripts -->
<script>
    // Hàm tính tổng giá của các sản phẩm được chọn
    function updateTotalPrice() {
        const selectedItems = document.querySelectorAll('.select-item:checked');
        let totalPrice = 0;

        selectedItems.forEach(item => {
            const price = parseFloat(item.getAttribute('data-price'));
            const quantity = parseInt(item.getAttribute('data-quantity'));
            totalPrice += price * quantity;
        });

        // Cập nhật tổng giá vào phần "Tổng tạm tính"
        const totalPriceElement = document.querySelector('.summary-details p span');
        totalPriceElement.textContent = totalPrice.toLocaleString('vi-VN') + ' VNĐ';
    }

    // Các hàm khác giữ nguyên
    function toggleVoucherOptions(event, voucherId, button) {
        event.preventDefault();
        const voucherOptions = document.getElementById(voucherId);
        const isVisible = voucherOptions.classList.contains('show');

        if (isVisible) {
            voucherOptions.classList.remove('show');
            button.classList.remove('selected');
        } else {
            voucherOptions.classList.add('show');
            button.classList.add('selected');
        }
    }

    function toggleVoucher(event, button, voucherText, voucherId) {
        event.preventDefault();
        const voucherOptions = document.getElementById(voucherId);
        const appliedVoucher = document.getElementById(`applied-voucher-${voucherId.split('-')[2]}`);
        const voucherOption = button.closest('.voucher-option');
        const isApplied = button.classList.contains('apply');

        if (isApplied) {
            voucherOption.classList.add('applied');
            appliedVoucher.style.display = 'block';
            appliedVoucher.textContent = `Đã áp dụng: ${voucherText}`;
            voucherOptions.classList.remove('show');
            const mainButton = voucherOptions.parentElement.querySelector('.voucher-btn');
            mainButton.classList.remove('selected');
        } else {
            voucherOption.classList.remove('applied');
            appliedVoucher.style.display = 'none';
            appliedVoucher.textContent = '';
            voucherOptions.classList.remove('show');
            const mainButton = voucherOptions.parentElement.querySelector('.voucher-btn');
            mainButton.classList.remove('selected');
        }
    }

    function cancelVoucherSelection(event, voucherId) {
        event.preventDefault();
        const voucherOptions = document.getElementById(voucherId);
        const button = voucherOptions.parentElement.querySelector('.voucher-btn');
        const appliedVoucher = document.getElementById(`applied-voucher-${voucherId.split('-')[2]}`);

        voucherOptions.classList.remove('show');
        button.classList.remove('selected');
        appliedVoucher.style.display = 'none';
        appliedVoucher.textContent = '';
    }

    function applyVoucher(event, voucherId) {
        event.preventDefault();
        const voucherOptions = document.getElementById(voucherId);
        const button = voucherOptions.parentElement.querySelector('.voucher-btn');
        button.classList.remove('selected');
    }

    // Gọi hàm updateTotalPrice khi trang được tải để đảm bảo tổng giá ban đầu
    window.onload = function () {
        updateTotalPrice();
    };
</script>