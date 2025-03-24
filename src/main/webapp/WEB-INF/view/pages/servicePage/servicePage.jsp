<%@ page import="startup.zeroli.config.ProjectPaths" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách nhà sách</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Segoe UI', Arial, sans-serif;
    }

    /* Header CSS giữ nguyên */
    .header {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        z-index: 1000;
        background: transparent;
        padding: 10px 20px;
        background-color: #242424;
    }

    .list-header li {
        color: white;
    }

    .navbar-header {
        display: flex;
        justify-content: space-around;
        align-items: center;
        margin: 0 30px;
    }

    .nav-links {
        display: flex;
        list-style: none;
    }

    .nav-links li {
        margin: 0 15px;
    }

    .nav-links a {
        text-decoration: none;
        color: white;
        font-weight: bold;
        font-size: 16px;
        transition: 0.3s;
        padding: 10px;
        display: inline-block;
    }

    .dropdown {
        position: absolute;
        top: 100%;
        left: 0;
        visibility: hidden;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        padding: 15px;
        opacity: 0;
        display: flex;
        flex-direction: column;
        transform: translateY(-10px);
        transition: opacity 0.3s ease, transform 0.3s ease, visibility 0s 0.3s;
        border-radius: 15px;
        backdrop-filter: blur(10px);
    }

    .dropdown-category {
        width: max-content;
        padding: 10px;
    }

    .dropdown-icon {
        position: relative;
    }

    .dropdown-category a {
        display: block;
        padding: 5px 0;
        color: black;
        text-decoration: none;
        transition: color 0.3s ease;
    }

    .nav-links .dropdown-icon:hover .dropdown {
        opacity: 1;
        transform: translateY(0);
        visibility: visible;
        transition: opacity 0.3s ease, transform 0.3s ease, visibility 0s;
    }

    .nav-links .dropdown-icon .dropdown {
        opacity: 0;
        transform: translateY(-10px);
        visibility: hidden;
    }

    .nav-links a.active, .nav-links a:hover {
        color: #e6b800;
        border-bottom: 2px solid #e6b800;
    }

    .icons {
        display: flex;
        gap: 20px;
    }

    .icons a {
        color: white;
        font-size: 25px;
        position: relative;
    }

    ion-icon {
        color: whitesmoke;
        transition: color 0.3s ease, transform 0.3s ease;
    }

    ion-icon:hover {
        color: #ffd700;
        transform: scale(1.2);
    }

    /* CSS cải tiến cho phần nội dung */
    body {
        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
        padding-top: 120px;
        min-height: 100vh;
        margin-left: 300px; /* Để tránh nội dung bị che bởi thanh menu */
        transition: margin-left 0.3s ease;
    }

    h1 {
        text-align: center;
        color: #2c3e50;
        margin-bottom: 50px;
        font-size: 2.8em;
        font-weight: 700;
        text-transform: uppercase;
        letter-spacing: 3px;
        position: relative;
        margin-top: 40px;
    }

    h1::after {
        content: '';
        width: 80px;
        height: 4px;
        background: #e6b800;
        position: absolute;
        bottom: -15px;
        left: 50%;
        transform: translateX(-50%);
        border-radius: 2px;
    }

    /* Thanh menu dọc */
    .sidebar {
        position: absolute; /* Đảm bảo sidebar cố định khi cuộn */
        top: 300px; /* Cố định ở vị trí ngang hàng với item đầu tiên (dựa trên tính toán) */
        left: 20px; /* Cách lề trái một chút */
        width: 260px;
        background: #fff;
        padding: 20px;
        border-radius: 15px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        z-index: 900; /* Đảm bảo sidebar nằm trên các phần tử khác */
    }

    .sidebar h3 {
        font-size: 1.2em;
        color: #1e90ff; /* Màu xanh giống trong hình */
        font-weight: 600;
        margin-bottom: 15px;
        text-transform: uppercase;
    }

    .sidebar ul {
        list-style: none;
    }

    .sidebar ul li {
        padding: 10px 0;
        display: flex;
        align-items: center;
        transition: color 0.3s ease;
    }

    .sidebar ul li ion-icon {
        margin-right: 10px;
        font-size: 1.2em;
        color: #333;
    }

    .sidebar ul li a {
        text-decoration: none;
        color: #333;
        font-size: 1em;
        font-weight: 400;
    }

    .sidebar ul li:hover a,
    .sidebar ul li:hover ion-icon {
        color: #1e90ff; /* Màu xanh khi hover */
    }

    .sidebar ul li.active a {
        color: #1e90ff;
        font-weight: 500;
    }

    /* CSS cho phần danh sách nhà sách */
    .bookstore-container {
        max-width: 1300px;
        margin: 0 auto;
        padding: 30px;
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
        gap: 40px;
        align-items: start;
    }

    .bookstore-link {
        text-decoration: none;
        color: inherit;
        display: block;
        height: 100%;
    }

    .bookstore-card {
        background: #fff;
        border-radius: 20px;
        overflow: hidden;
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
        transition: all 0.4s ease;
        position: relative;
        animation: fadeIn 0.5s ease forwards;
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    .bookstore-card:hover {
        transform: translateY(-15px) scale(1.02);
        box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
    }

    .bookstore-image {
        width: 100%;
        height: 220px;
        object-fit: cover;
        transition: transform 0.4s ease;
    }

    .bookstore-card:hover .bookstore-image {
        transform: scale(1.05);
    }

    .bookstore-content {
        padding: 25px;
        flex-grow: 1;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .bookstore-name {
        font-size: 1.7em;
        color: #2c3e50;
        margin-bottom: 12px;
        font-weight: 600;
        transition: color 0.3s ease;
    }

    .bookstore-card:hover .bookstore-name {
        color: #e6b800;
    }

    .bookstore-address {
        font-size: 1em;
        color: #7f8c8d;
        margin-bottom: 15px;
        display: flex;
        align-items: center;
        font-style: italic;
    }

    .bookstore-address ion-icon {
        margin-right: 10px;
        color: #e67e22;
        font-size: 1.2em;
    }

    .bookstore-description {
        font-size: 0.95em;
        color: #636e72;
        line-height: 1.6;
        max-height: 80px;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
    }

    .bookstore-card::before {
        content: 'Xem bản đồ';
        position: absolute;
        bottom: 10px;
        right: 10px;
        background: #e6b800;
        color: white;
        padding: 6px 12px;
        border-radius: 12px;
        font-size: 0.9em;
        opacity: 0;
        transition: opacity 0.3s ease;
    }

    .bookstore-card:hover::before {
        opacity: 1;
    }

    .no-bookstores {
        text-align: center;
        font-size: 1.4em;
        color: #7f8c8d;
        padding: 60px;
        background: rgba(255, 255, 255, 0.8);
        border-radius: 20px;
        margin: 30px auto;
        max-width: 600px;
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

<body>
<h1>Danh sách nhà sách</h1>
<header class="header">
    <div class="navbar-header">
        <a href="#" class="logo" title="logo">
            <img src="resources/logo_name.png" alt="ZeroLi Logo" height="100px">
        </a>

        <ul class="nav-links">
            <li><a href="#" class="active">HomePage</a></li>
            <li><a href="#">Friends</a></li>
            <li class="dropdown-icon">
                <a href="#">BookCase
                    <ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="#">Books</a>
                        <a href="#">Canvas</a>
                        <a href="#">Showroom</a>
                    </div>
                </div>
            </li>
            <li class="dropdown-icon">
                <a href="#">Stores<ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="<%=ProjectPaths.HREF_TO_PRODUCTPAGE %>">Products</a>
                        <a href="<%=ProjectPaths.HREF_TO_SERVICE %>">Services</a>
                        <a href="#">AI-Service</a>
                    </div>
                </div>
            </li>
            <li><a href="#">Events</a></li>
            <li><a href="#">Announcements</a></li>
            <li class="dropdown-icon">
                <a href="#">Personality<ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="#">Profile</a>
                        <a href="#">Shopping Cart</a>
                        <a href="#">History</a>
                    </div>
                </div>
            </li>
        </ul>

        <div class="icons">
            <a href="#"><ion-icon name="search-outline"></ion-icon></a>
            <a href="<%= ProjectPaths.HREF_TO_LOGINPAGE %>"><ion-icon name="person-circle-outline"></ion-icon></a>
        </div>
    </div>
</header>

<!-- Thanh menu dọc -->
<aside class="sidebar">
    <h3>Khu vực</h3>
    <ul>
        <li>
            <ion-icon name="location-outline"></ion-icon>
            <a href="#hanoi">Nhà sách Hà Nội</a>
        </li>
        <li>
            <ion-icon name="location-outline"></ion-icon>
            <a href="#danang">Nhà sách Đà Nẵng</a>
        </li>
        <li>
            <ion-icon name="location-outline"></ion-icon>
            <a href="#hcm">Nhà sách Thành Phố Hồ Chí Minh</a>
        </li>
        <li>
            <ion-icon name="location-outline"></ion-icon>
            <a href="#cantho">Nhà sách Cần Thơ</a>
        </li>
    </ul>
</aside>

<div class="bookstore-container">
    <!-- Kiểm tra xem danh sách nhà sách có tồn tại -->
    <c:if test="${not empty bookstores}">
        <c:forEach var="bookstore" items="${bookstores}">
            <a href="${bookstore.googleMapsLinks[0]}" target="_blank" class="bookstore-link">
                <div class="bookstore-card">
                    <img src="${bookstore.imageUrl}" alt="${bookstore.name}" class="bookstore-image">
                    <div class="bookstore-content">
                        <h2 class="bookstore-name">${bookstore.name}</h2>
                        <p class="bookstore-address">
                            <ion-icon name="location-outline"></ion-icon>
                                ${bookstore.address}
                        </p>
                        <p class="bookstore-description">${bookstore.description}</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </c:if>

    <!-- Nếu không có nhà sách nào -->
    <c:if test="${empty bookstores}">
        <div class="no-bookstores">Không có nhà sách để hiển thị.</div>
    </c:if>
</div>
</body>
</html>