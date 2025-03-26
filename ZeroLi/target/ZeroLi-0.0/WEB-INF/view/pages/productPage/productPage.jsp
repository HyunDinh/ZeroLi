<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="startup.zeroli.config.ProjectPaths" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product</title>
        <!-- Font Awesome -->        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <!-- Nhúng Navbar -->
        <div>
            <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>
        </div>

        <!-- Nội dung chính -->
        <div class="content-wrapper">
            <!-- Container cho thanh tìm kiếm và giỏ hàng -->
            <div class="search-cart-container">
                <form action="main" method="get" class="search-product">
                    <input type="hidden" name="action" value="search-product">
                    <input type="text" name="name" placeholder="Nhập tên sản phẩm..." value="${param.name}">
                    <button type="submit"><i class="fas fa-search"></i> Tìm kiếm</button>
                </form>
                <div class="cart">
                    <div class="cart-icon">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="cart-count" style="display: ${requestScope.cartSize > 0 ? 'block' : 'none'}">${requestScope.cartSize}</span>
                    </div>
                    <div class="cart-popup">
                        <h4>Sản Phẩm Mới Thêm</h4>
                        <div class="cart-items">
                            <!-- Danh sách sản phẩm sẽ được thêm bằng JavaScript -->
                        </div>

                        <a href="<%= ProjectPaths.HREF_TO_CARTPAGE %>"">
                            <button class="view-cart-btn">Xem Giỏ Hàng</button>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Thông báo lỗi nếu không có sản phẩm -->
            <c:if test="${empty productMap}">
                <p class="error-message">❌ Không có sản phẩm nào phù hợp với tìm kiếm của bạn.</p>
            </c:if>

            <!-- Hiển thị danh sách sản phẩm dạng grid -->
            <div class="product-grid">
                <c:forEach var="entry" items="${productMap.entrySet()}">
                    <div class="product-card">
                        <div class="product-img-container">
                            <img src="${entry.value.imageURL}" class="product-img" alt="${entry.value.productName}">
                        </div>
                        <div class="card-body">
                            <h5 class="product-name">${entry.value.productName}</h5>
                            <p class="product-price">
                                <fmt:formatNumber value="${entry.value.price}" type="number" groupingUsed="true" /> VNĐ
                            </p>
                            <div class="btn-container">
                                <a href="<%= ProjectPaths.HREF_TO_PRODUCTPAGE%>&id=${entry.value.productId}" class="btn btn-buy">Xem Chi Tiết</a>
                                <form action="main" method="POST" class="btn-cart-form">
                                    <input type="hidden" name="action" value="add-items">
                                    <input type="hidden" name="productId" value="${entry.value.productId}">
                                    <input type="hidden" name="productName" value="${entry.value.productName}">
                                    <input type="hidden" name="price" value="${entry.value.price}">
                                    <input type="hidden" name="imageURL" value="${entry.value.imageURL}">
                                    <button type="submit" class="btn btn-cart"><i class="fas fa-cart-plus"></i>Thêm Vào Giỏ Hàng</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

<style>
    /* CSS chung */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
    }

    body {
        background-color: #f4f4f4;
        min-height: 100vh;
    }

    .content-wrapper {
        padding-top: 150px;
        padding-bottom: 50px;
        max-width: 1200px;
        margin: 0 auto;
    }

    /* Form tìm kiếm (giữ nguyên) */
    .search-form {
        display: flex;
        justify-content: center;
        margin-bottom: 30px;
    }

    .search-form input[type="text"],
    .search-form input[type="number"] {
        padding: 8px 12px;
        margin: 0 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
        width: 200px;
    }

    .search-form button {
        padding: 8px 15px;
        background-color: #242424;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .search-form button:hover {
        background-color: #e6b800;
    }

    /* Container cho thanh tìm kiếm và giỏ hàng */
    .search-cart-container {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 30px;
        position: relative;
    }

    /* Thanh tìm kiếm */
    .search-product {
        display: flex;
        align-items: center;
        border: 2px solid #ff4d4f; /* Red border similar to Shopee */
        border-radius: 8px; /* Rounded corners */
        overflow: hidden; /* Ensure the border-radius applies to children */
        background-color: #fff; /* White background */
        width: 100%; /* Adjust as needed */
        max-width: 600px; /* Similar width to the image */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
    }

    .search-product input[type="text"] {
        flex: 1; /* Take up remaining space */
        border: none; /* Remove default border */
        outline: none; /* Remove default outline */
        padding: 10px 15px; /* Padding for comfort */
        font-size: 16px; /* Font size similar to the image */
        color: #333; /* Text color */
    }

    .search-product input[type="text"]::placeholder {
        color: #999; /* Light gray placeholder text */
        font-size: 16px;
    }

    .search-product button {
        background-color: #ff4d4f; /* Red background for the button */
        border: none; /* Remove default border */
        padding: 10px 15px; /* Padding for the button */
        cursor: pointer; /* Pointer cursor on hover */
        display: flex;
        align-items: center;
        justify-content: center;
        transition: background-color 0.3s ease; /* Smooth hover effect */
    }

    .search-product button i {
        color: #fff; /* White icon color */
        font-size: 18px; /* Icon size */
    }

    .search-product button:hover {
        background-color: #e63946; /* Slightly darker red on hover */
    }

    /* Giỏ hàng */
    .cart {
        margin-left: 10px; /* Khoảng cách giữa thanh tìm kiếm và giỏ hàng */
        display: flex;
        align-items: center;
        position: relative;
    }

    .cart-icon {
        position: relative;
        cursor: pointer;
        color: #ff4d4f; /* Màu đỏ giống Shopee */
        font-size: 24px;
    }

    .cart-count {
        position: absolute;
        top: -8px;
        right: -8px;
        background-color: #d81b60; /* Màu hồng đậm cho badge */
        color: #fff;
        border-radius: 50%;
        padding: 2px 6px;
        font-size: 12px;
    }

    .cart-popup {
        display: none;
        position: absolute;
        top: 100%; /* Đặt ngay dưới cart */
        right: 0; /* Căn phải với cart */
        background-color: #fff;
        width: 300px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border-radius: 4px;
        padding: 10px;
        z-index: 1000;
    }

    /* Sửa lỗi hover: Hiển thị popup khi hover vào cả .cart và .cart-popup */
    .cart:hover .cart-popup {
        display: block; /* Hiển thị popup khi hover vào .cart */
    }

    .cart-popup:hover {
        display: block; /* Giữ popup hiển thị khi hover vào chính nó */
    }

    .cart-popup h4 {
        font-size: 14px;
        font-weight: bold;
        margin-bottom: 10px;
        color: #333;
    }

    .cart-item {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }

    .cart-item img {
        width: 50px;
        height: 50px;
        margin-right: 10px;
    }

    .cart-item-info p {
        font-size: 12px;
        color: #333;
        margin-bottom: 5px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        max-width: 200px;
    }

    .cart-item-info span {
        font-size: 12px;
        color: #ff5722;
        font-weight: bold;
    }

    .view-cart-btn {
        width: 100%;
        padding: 10px;
        background-color: #ff5722;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
        text-align: center;
    }

    .view-cart-btn:hover {
        background-color: #e64a19;
    }

    /* Thông báo lỗi (giữ nguyên) */
    .error-message {
        font-size: 16px;
        margin: 20px 0;
        color: red;
        text-align: center;
        background: #fff;
        padding: 10px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        margin-left: auto;
        margin-right: auto;
    }

    /* Product Grid (giữ nguyên) */
    .product-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); /* Kích thước giống hình */
        gap: 1.5rem; /* Khoảng cách giữa các card giống hình */
        padding: 20px 0;
    }

    .product-card {
        background: #ffffff;
        border-radius: 20px; /* Bo góc tròn giống hình */
        box-shadow: 0 5px 25px rgba(0, 0, 0, 0.1); /* Shadow giống hình */
        transition: all 0.3s ease; /* Hiệu ứng mượt mà */
        overflow: hidden;
    }

    .product-card:hover {
        transform: translateY(-8px); /* Hiệu ứng nâng lên giống hình */
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15); /* Shadow đậm hơn khi hover */
    }

    .product-img-container {
        width: 100%;
        height: 220px; /* Chiều cao hình ảnh giống hình */
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 1rem; /* Padding giống hình */
        background: #ffffff; /* Nền trắng giống hình */
    }

    .product-img {
        max-height: 100%;
        max-width: 100%;
        object-fit: contain; /* Giữ tỷ lệ hình ảnh */
        transition: transform 0.3s ease; /* Hiệu ứng phóng to khi hover */
    }

    .product-card:hover .product-img {
        transform: scale(1.1); /* Phóng to hình ảnh khi hover */
    }

    .card-body {
        padding: 15px;
        text-align: center; /* Căn giữa giống hình */
    }

    .product-name {
        font-size: 1.1rem; /* Kích thước chữ giống hình */
        font-weight: 500;
        color: #333;
        height: 50px; /* Giới hạn chiều cao để hiển thị 2 dòng giống hình */
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        margin-bottom: 10px;
    }

    .product-price {
        font-size: 1.1rem;
        font-weight: bold;
        color: #ff2d55; /* Màu đỏ giống hình */
        margin-bottom: 15px;
    }

    .btn-container {
        display: flex;
        justify-content: center; /* Căn giữa giống hình */
        gap: 10px;
    }

    .btn-buy {
        background: #ff2d55; /* Màu đỏ giống hình */
        border: none;
        border-radius: 25px; /* Bo góc tròn giống hình */
        padding: 0.6rem 1.5rem; /* Kích thước nút giống hình */
        color: white;
        font-size: 13px;
        font-weight: 500;
        transition: background-color 0.3s ease;
        text-decoration: none;
        display: inline-block;
    }

    .btn-buy:hover {
        background: #e02447; /* Màu đỏ đậm hơn khi hover */
        color: white;
    }

    .btn-cart {
        background: #007bff; /* Màu xanh giống hình */
        border: none;
        border-radius: 25px; /* Bo góc tròn giống hình */
        padding: 0.6rem 1.5rem; /* Kích thước nút giống hình */
        color: white;
        font-size: 13px;
        font-weight: 500;
        transition: background-color 0.3s ease;
        text-decoration: none;
        display: flex;
        align-items: center;
        gap: 5px;
    }

    .btn-cart:hover {
        background: #0056b3; /* Màu xanh đậm hơn khi hover */
        color: white;
    }

    /* Responsive Design (giữ nguyên) */
    @media (max-width: 768px) {
        .content-wrapper {
            padding-top: 200px;
            padding-left: 15px;
            padding-right: 15px;
        }

        .product-grid {
            grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
            gap: 15px;
        }

        .product-img-container {
            height: 180px; /* Giảm chiều cao hình ảnh cho màn hình nhỏ */
        }
    }

    @media (max-width: 576px) {
        .product-grid {
            grid-template-columns: 1fr;
        }

        .product-card {
            max-width: 300px;
            margin: 0 auto;
        }

        .product-img-container {
            height: 160px; /* Giảm chiều cao hình ảnh cho màn hình rất nhỏ */
        }
    }
</style>

<script>
    let cartItems = [];

    function addToCart(productName, price, imageURL) {
        cartItems.push({name: productName, price: price, image: imageURL});

        const cartCount = document.querySelector('.cart-count');
        cartCount.textContent = cartItems.length;
        cartCount.style.display = cartItems.length > 0 ? 'block' : 'none';

        const cartItemsContainer = document.querySelector('.cart-items');
        cartItemsContainer.innerHTML = '';

        cartItems.forEach(item => {
            const cartItem = document.createElement('div');
            cartItem.classList.add('cart-item');
            cartItem.innerHTML = `
                <img src="${item.image}" alt="${item.name}">
                <div class="cart-item-info">
                    <p>${item.name}</p>
                    <span>₫${item.price.toLocaleString()}</span>
                </div>
            `;
            cartItemsContainer.appendChild(cartItem);
        });

        sessionStorage.setItem('cartItems', JSON.stringify(cartItems));

        // Gọi hàm thông báo thành công
        addToCartSuccess(productName);
    }

    function addToCartSuccess(productName) {
        const toast = document.createElement('div');
        toast.classList.add('toast-notification');
        toast.innerHTML = `
            <i class="fas fa-check-circle"></i>
            <span>Đã thêm "${productName}" vào giỏ hàng thành công!</span>
        `;
        document.body.appendChild(toast);

        setTimeout(() => {
            toast.classList.add('show');
        }, 100);

        setTimeout(() => {
            toast.classList.remove('show');
            setTimeout(() => {
                toast.remove();
            }, 300);
        }, 3000);
    }

    window.onload = function () {
        const savedCart = sessionStorage.getItem('cartItems');
        if (savedCart) {
            cartItems = JSON.parse(savedCart);
            const cartCount = document.querySelector('.cart-count');
            cartCount.textContent = cartItems.length;
            cartCount.style.display = cartItems.length > 0 ? 'block' : 'none';

            const cartItemsContainer = document.querySelector('.cart-items');
            cartItems.forEach(item => {
                const cartItem = document.createElement('div');
                cartItem.classList.add('cart-item');
                cartItem.innerHTML = `
                    <img src="${item.image}" alt="${item.name}">
                    <div class="cart-item-info">
                        <p>${item.name}</p>
                        <span>₫${item.price.toLocaleString()}</span>
                    </div>
                `;
                cartItemsContainer.appendChild(cartItem);
            });
        }

        const cartForms = document.querySelectorAll('.btn-cart-form');
        cartForms.forEach(form => {
            form.addEventListener('submit', function (event) {
                event.preventDefault();

                const productName = form.querySelector('input[name="productName"]').value;
                const price = parseFloat(form.querySelector('input[name="price"]').value);
                const imageURL = form.querySelector('input[name="imageURL"]').value;

                addToCart(productName, price, imageURL);

                setTimeout(() => {
                    form.submit();
                }, 100);
            });
        });
    };

    function validateForm() {
        const minPrice = document.getElementById("minPrice")?.value;
        const maxPrice = document.getElementById("maxPrice")?.value;

        if (minPrice !== "" && maxPrice !== "") {
            if (parseFloat(minPrice) > parseFloat(maxPrice)) {
                alert("Giá thấp nhất không thể lớn hơn giá cao nhất!");
                return false;
            }
        }

        if (minPrice !== "" && parseFloat(minPrice) < 0) {
            alert("Giá thấp nhất không thể nhỏ hơn 0!");
            return false;
        }
        if (maxPrice !== "" && parseFloat(maxPrice) < 0) {
            alert("Giá cao nhất không thể nhỏ hơn 0!");
            return false;
        }

        return true;
    }
</script>