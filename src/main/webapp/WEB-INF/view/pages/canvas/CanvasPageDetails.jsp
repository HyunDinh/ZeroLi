<%@ page import="startup.zeroli.config.ProjectPaths" %>
<%@ page import="startup.zeroli.controller.mainController.MainControllerServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chi tiết Canvas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            overflow-x: hidden; /* Ngăn cuộn ngang */
        }

        /* Action Bar (Giữ nguyên) */
        .action-bar {
            position: absolute;
            top: 120px;
            left: 0;
            width: 100%;
            display: flex;
            justify-content: center;
            gap: 20px;
            padding: 10px 0;
            background-color: rgba(36, 36, 36, 0.9);
            z-index: 999;
        }

        .action-button {
            padding: 10px 20px;
            background-color: #e6b800;
            color: #242424;
            text-decoration: none;
            font-weight: bold;
            border-radius: 25px;
            transition: all 0.3s ease;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        .action-button:hover {
            background-color: #ffd700;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }

        /* Container chính (Thiết kế lại) */
        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 20px;
            padding-top: 60px;
            max-width: 1200px;
            margin: 0 auto;
            margin-top: 160px; /* Điều chỉnh để tránh chồng lấp với action-bar */
            gap: 30px; /* Khoảng cách giữa hình ảnh và chi tiết */
        }

        .image-section {
            flex: 1;
            max-width: 550px; /* Tăng kích thước tối đa để nổi bật */
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
            transition: transform 0.3s ease;
        }

        .image-section img {
            width: 100%;
            height: auto;
            display: block;
            border-radius: 15px;
        }

        .image-section:hover {
            transform: scale(1.02); /* Hiệu ứng phóng to khi hover */
        }

        .details-section {
            flex: 1;
            max-width: 600px;
            padding: 25px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
            position: relative;
        }

        .details-section h2 {
            font-size: 28px;
            color: #333;
            margin-bottom: 15px;
            font-weight: 600;
        }

        .details-section p {
            font-size: 16px;
            color: #555;
            line-height: 1.6;
            margin-bottom: 15px;
        }

        .interaction-bar {
            display: flex;
            align-items: center;
            gap: 20px;
            margin-top: 20px;
        }

        .interaction-bar .save-button {
            padding: 12px 25px;
            background-color: #e60023;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 25px;
            transition: background-color 0.3s ease, transform 0.3s ease;
            box-shadow: 0 4px 8px rgba(230, 0, 35, 0.3);
        }

        .interaction-bar .save-button:hover {
            background-color: #ff3333;
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(230, 0, 35, 0.4);
        }

        /* Responsive */
        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                align-items: center;
                margin-top: 140px; /* Điều chỉnh cho màn hình nhỏ */
                padding: 15px;
            }

            .image-section {
                margin-bottom: 20px;
                max-width: 100%;
            }

            .details-section {
                width: 100%;
                max-width: 100%;
                padding: 20px;
            }

            .action-bar {
                flex-wrap: wrap;
                gap: 10px;
                padding: 10px;
                top: 100px; /* Điều chỉnh cho màn hình nhỏ */
            }

            .action-button {
                padding: 8px 15px;
                font-size: 14px;
            }

            .interaction-bar {
                flex-direction: column;
                align-items: stretch;
                gap: 10px;
            }

            .interaction-bar .save-button {
                width: 100%;
                text-align: center;
            }
        }

        @media (max-width: 480px) {
            .action-bar {
                flex-direction: column;
                align-items: center;
                top: 80px;
            }

            .details-section h2 {
                font-size: 22px;
            }

            .details-section p {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>

<div class="action-bar">
    <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_SEARCH_CANVAS %>&search=car" class="action-button">Car</a>
    <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_SEARCH_CANVAS %>&search=animal" class="action-button">Animal</a>
    <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_SEARCH_CANVAS %>&search=bike" class="action-button">Bike</a>
    <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_SEARCH_CANVAS %>&search=natural" class="action-button">Natural</a>
</div>

<div class="container">
    <div class="image-section">
        <img src="${selectedCanvas.link}" alt="${selectedCanvas.title}">
    </div>
    <div class="details-section">
        <h2>${selectedCanvas.title}</h2>
        <p><strong>Tác giả:</strong> ${selectedCanvas.author}</p>
        <p>${selectedCanvas.desc}</p>
        <div class="interaction-bar">
            <a href="#" class="save-button">Lưu</a>
        </div>
    </div>
</div>
</body>
</html>