<%@ page import="startup.zeroli.config.ProjectPaths" %>
<%@ page import="startup.zeroli.controller.mainController.MainControllerServlet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HomePage</title>

    <style>
        .pinterest-grid {
            margin-top: 220px;
            column-count: 4;
            column-gap: 15px;
            padding: 0 20px;
        }

        .pinterest-grid .grid-item {
            break-inside: avoid;
            margin-bottom: 15px;
        }

        .pinterest-grid .grid-item img {
            width: 100%;
            height: auto;
            border-radius: 10px;
            display: block;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .pinterest-grid .grid-item img:hover {
            transform: scale(1.02);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

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

        .no-results {
            text-align: center;
            font-size: 18px;
            color: #666;
            margin-top: 20px;
        }

        @media (max-width: 1024px) {
            .pinterest-grid { column-count: 3; }
            .action-bar { gap: 15px; }
        }

        @media (max-width: 768px) {
            .pinterest-grid { column-count: 2; }
            .action-bar { flex-wrap: wrap; gap: 10px; padding: 15px; }
            .action-button { padding: 8px 15px; }
        }

        @media (max-width: 480px) {
            .pinterest-grid { column-count: 1; }
            .action-bar { flex-direction: column; align-items: center; }
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

<div class="pinterest-grid">
    <c:forEach var="canvas" items="${canvas}">
        <div class="grid-item">
            <a href="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_VIEW_CANVAS %>&id=${canvas.id}">
                <img src="${canvas.link}" alt="picture">
            </a>
        </div>
    </c:forEach>
</div>
</body>
</html>