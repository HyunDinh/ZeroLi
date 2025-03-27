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
            overflow-x: hidden;
        }

        .action-bar {
            position: absolute;
            top: 120px; /* Adjust based on your navbar height */
            left: 0;
            width: 100%;
            display: flex;
            justify-content: center;
            gap: 15px;
            padding: 10px 0;
            background-color: rgba(36, 36, 36, 0.9);
            z-index: 999;
        }

        .action-button {
            padding: 8px 20px;
            background-color: #e6b800;
            color: #242424;
            text-decoration: none;
            font-weight: bold;
            border-radius: 20px;
            transition: all 0.3s ease;
            font-size: 14px;
        }

        .action-button:hover {
            background-color: #ffd700;
            transform: translateY(-2px);
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 20px;
            padding-top: 60px;
            max-width: 1200px;
            margin: 0 auto;
            margin-top: 160px;
            gap: 20px;
        }

        .image-section {
            flex: 1;
            max-width: 500px;
            border-radius: 15px;
            overflow: hidden;
            background-color: #fff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .image-section img {
            width: 100%;
            height: auto;
            display: block;
            border-radius: 15px;
        }

        .image-section:hover {
            transform: scale(1.02);
        }

        .details-section {
            flex: 1;
            max-width: 600px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .details-section h2 {
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
            font-weight: 600;
        }

        .details-section p {
            font-size: 14px;
            color: #555;
            line-height: 1.5;
            margin-bottom: 10px;
        }

        .details-section p strong {
            color: #333;
        }

        .comment-section {
            margin-top: 20px;
            padding-top: 15px;
            border-top: 1px solid #e0e0e0;
        }

        .comment-section .comment-list {
            margin-bottom: 20px;
        }

        .comment-section .comment {
            display: flex;
            align-items: flex-start;
            gap: 10px;
            margin-bottom: 15px;
            position: relative;
        }

        .comment-section .comment .avatar {
            width: 35px;
            height: 35px;
            border-radius: 50%;
            background-color: #ddd;
        }

        .comment-section .comment .comment-content {
            background-color: #f5f5f5;
            padding: 8px 12px;
            border-radius: 15px;
            font-size: 13px;
            color: #333;
            flex: 1;
        }

        .comment-section .comment .comment-content strong {
            color: #333;
        }

        .comment-section .comment .more-options {
            cursor: pointer;
            font-size: 16px;
            color: #555;
            padding: 5px;
        }

        .comment-section .comment .dropdown-menu {
            display: none;
            position: absolute;
            right: 0;
            top: 30px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }

        .comment-section .comment .dropdown-menu.active {
            display: block;
        }

        .comment-section .comment .dropdown-menu a {
            display: block;
            padding: 8px 12px;
            color: #333;
            text-decoration: none;
            font-size: 13px;
        }

        .comment-section .comment .dropdown-menu a:hover {
            background-color: #f0f0f0;
        }

        .comment-section .comment .edit-form {
            display: none;
            margin-top: 10px;
            flex: 1;
        }

        .comment-section .comment .edit-form.active {
            display: flex;
            gap: 10px;
        }

        .comment-section .comment .edit-form input {
            flex: 1;
            padding: 5px;
            border-radius: 10px;
            border: 1px solid #ddd;
            font-size: 13px;
        }

        .comment-section .comment .edit-form button {
            padding: 5px 15px;
            background-color: #e60023;
            color: white;
            border: none;
            border-radius: 10px;
            font-weight: bold;
            cursor: pointer;
            font-size: 13px;
        }

        .comment-section .comment .edit-form button:hover {
            background-color: #ff3333;
        }

        .comment-section .comment-input {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .comment-section .comment-input input {
            flex: 1;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 20px;
            font-size: 13px;
            outline: none;
        }

        .comment-section .comment-input button {
            padding: 8px 20px;
            background-color: #e60023;
            color: white;
            border: none;
            border-radius: 20px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 13px;
        }

        .comment-section .comment-input button:hover {
            background-color: #ff3333;
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                align-items: center;
                margin-top: 140px;
                padding: 15px;
            }
            .image-section { max-width: 100%; margin-bottom: 20px; }
            .details-section { width: 100%; max-width: 100%; padding: 15px; }
            .action-bar { flex-wrap: wrap; gap: 10px; padding: 10px; top: 100px; }
            .action-button { padding: 6px 15px; font-size: 13px; }
        }

        @media (max-width: 480px) {
            .action-bar { flex-direction: column; align-items: center; top: 80px; }
            .details-section h2 { font-size: 20px; }
            .details-section p { font-size: 13px; }
            .comment-section .comment .avatar { width: 30px; height: 30px; }
            .comment-section .comment-content { font-size: 12px; }
            .comment-section .comment-input input { font-size: 12px; }
            .comment-section .comment-input button { padding: 6px 15px; font-size: 12px; }
        }
    </style>
</head>
<body>
<% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>

<div class="action-bar">
    <a href="<%= ProjectPaths.HREF_TO_CANVASPAGE %>" class="action-button">All</a>
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
        <div class="comment-section">
            <div class="comment-list">
                <c:forEach var="comment" items="${comments}">
                    <c:forEach var="review" items="${comment.reviews}">
                        <div class="comment" data-comment-id="${comment.idProduct}">
                            <div class="avatar"></div>
                            <div class="comment-content">
                                <strong>${comment.username}:</strong> <span class="comment-text">${review}</span>
                            </div>
                            <div class="more-options">...</div>
                            <div class="dropdown-menu">
                                <a href="#" class="edit-comment">Sửa</a>
                                <a href="#" class="delete-comment" data-comment-id="${comment.idProduct}" data-canvas-id="${selectedCanvas.id}">Xóa</a>
                            </div>
                            <form action="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_EDIT_COMMENT %>" method="post" class="edit-form">
                                <input type="hidden" name="commentId" value="${comment.idProduct}" />
                                <input type="hidden" name="canvasId" value="${selectedCanvas.id}" />
                                <input type="text" name="updatedComment" value="${review}" required />
                                <button type="submit">Lưu</button>
                            </form>
                            <form action="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_DELETE_COMMENT %>" method="post" class="delete-form" style="display: none;">
                                <input type="hidden" name="commentId" value="${comment.idProduct}" />
                                <input type="hidden" name="canvasId" value="${selectedCanvas.id}" />
                            </form>
                        </div>
                    </c:forEach>
                </c:forEach>
            </div>
            <form action="<%= ProjectPaths.HREF_TO_MAINCONTROLLER + MainControllerServlet.ACTION_ADD_COMMENT_CANVAS %>" method="post" class="comment-input">
                <input type="hidden" name="action" value="<%= MainControllerServlet.ACTION_ADD_COMMENT_CANVAS %>">
                <input type="hidden" name="canvasId" value="${selectedCanvas.id}">
                <input type="text" placeholder="Tên của bạn..." name="username" required>
                <input type="text" placeholder="Thêm bình luận..." name="comment" required>
                <button type="submit">Gửi</button>
            </form>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Handle clicking the three dots
        document.querySelectorAll('.more-options').forEach(function (dots) {
            dots.addEventListener('click', function () {
                const dropdown = this.nextElementSibling;
                dropdown.classList.toggle('active');
            });
        });

        // Handle clicking outside to close dropdown
        document.addEventListener('click', function (e) {
            if (!e.target.classList.contains('more-options')) {
                document.querySelectorAll('.dropdown-menu').forEach(function (menu) {
                    menu.classList.remove('active');
                });
            }
        });

        // Handle edit comment
        document.querySelectorAll('.edit-comment').forEach(function (editLink) {
            editLink.addEventListener('click', function (e) {
                e.preventDefault();
                const commentDiv = this.closest('.comment');
                const editForm = commentDiv.querySelector('.edit-form');
                editForm.classList.toggle('active');
                commentDiv.querySelector('.dropdown-menu').classList.remove('active');
            });
        });

        // Handle delete comment
        document.querySelectorAll('.delete-comment').forEach(function (deleteLink) {
            deleteLink.addEventListener('click', function (e) {
                e.preventDefault();
                const commentDiv = this.closest('.comment');
                const deleteForm = commentDiv.querySelector('.delete-form');
                deleteForm.submit();
            });
        });
    });
</script>
</body>
</html>