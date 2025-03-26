<%@ page import="startup.zeroli.config.ProjectPaths" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách nhà sách</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Playball&display=swap');
            #title {
                text-align: center;
                margin: 30px 0;
                font-size: 3.5rem;
                font-family: "Playball";
            }
            /* General layout */
            .container {
                display: flex;
                flex-direction: row;
                padding: 20px;
                gap: 20px;
            }

            /* Sidebar styles */
            .sidebar {
                width: 260px;
                background: #fff;
                padding: 20px;
                border-radius: 15px;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
                z-index: 900;
                position: fixed;
                left: 0;
                opacity: 0.8;
                transition: transform 0.3s ease-in-out;
            }

            .sidebar h3 {
                text-align: center;
                margin-bottom: 20px;
                font-size: 22px;
                color: black;
                font-weight: 600;
                text-transform: uppercase;
            }

            .sidebar ul {
                list-style: none;
                padding: 0;
            }

            .sidebar ul li {
                padding: 12px;
                display: flex;
                align-items: center;
                transition: background 0.3s, color 0.3s;
                border: 1px solid black;
                border-radius: 20px;
                margin-bottom: 10px;
            }

            .sidebar ul li:hover {
                border: 1px solid black;
                border-radius: 20px;
            }

            .sidebar ul li ion-icon {
                margin-right: 10px;
            }

            .sidebar ul li a {
                text-decoration: none;
                color: black; /* Đổi màu chữ sang trắng giống menu trong hình */
                font-size: 18px;
                transition: color 0.3s;
            }

            .sidebar ul li a:hover {
                color: #f1c40f; /* Màu vàng khi hover */

            }

            /* Bookstore container */
            .bookstore-container {
                margin-left: 280px;
                padding: 30px;
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
                gap: 40px;
                width: calc(100% - 280px);
            }

            .bookstore-card {
                background: white;
                border-radius: 20px;
                border: 1px solid black;
                box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                transition: all 0.4s ease;
                position: relative;
                display: flex;
                flex-direction: column;
                height: 100%;
                padding: 5px;
            }

            .bookstore-card:hover {
                transform: translateY(-5px) scale(1.01);
                box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
            }

            .bookstore-image {
                width: 100%;
                height: 220px;
                object-fit: cover;
                transition: transform 0.4s ease;
                border-radius: 20px;
                }

            .bookstore-card:hover .bookstore-image {
                transform: scale(1.05);
            }
            .bookstore-link{
                text-decoration: none;
            }
            .bookstore-content {
                padding: 20px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                gap: 10px;
            }

            .bookstore-name {
                font-size: 1.5em;
                color: #2c3e50;
                font-weight: bold;
                transition: color 0.3s ease;
            }

            .bookstore-card:hover .bookstore-name {
                color: #e6b800;
            }

            .bookstore-address {
                font-size: 0.9em;
                color: #7f8c8d;
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

            @media (max-width: 1024px) {
                .container {
                    flex-direction: column;
                    align-items: center;
                }
                .sidebar {
                    left: -260px;
                    width: 260px;
                    height: 100vh;
                    transition: transform 0.3s ease-in-out;
                }
                .sidebar.active {
                    transform: translateX(260px);
                }
                .bookstore-container {
                    margin-left: 0;
                    width: 100%;
                }
            }

            @media (max-width: 768px) {
                .bookstore-card {
                    width: 100%;
                }
            }

        </style>
    </head>

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

    <body>
        <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response);%>

        <a href="#" style="text-decoration: none; color: black;
           "><h1 id="title" >BookStores</h1>
        </a> 
        <div class="container">
            <!-- Thanh menu dọc -->
            <aside class="sidebar">
                <h3>Areas</h3>
                <ul>
                    <li>
                    <ion-icon name="location-outline" color="white"></ion-icon>
                    <a href="#hanoi">Hà Nội</a>
                    </li>
                    <li>
                    <ion-icon name="location-outline" color="white"></ion-icon>
                    <a href="#danang">Đà Nẵng</a>
                    </li>
                    <li>
                    <ion-icon name="location-outline" color="white"></ion-icon>
                    <a href="#hcm">Hồ Chí Minh</a>
                    </li>
                    <li>
                    <ion-icon name="location-outline" color="white"></ion-icon>
                    <a href="#cantho">Cần Thơ</a>
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
                    <div class="no-bookstores">No bookstore to display</div>
                </c:if>
            </div>
        </div>
    </body>
</html>