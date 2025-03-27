<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <style>
            /* Giữ nguyên phần CSS như cũ */
            :root {
                --primary-color: #4285f4;
                --primary-hover: #3367d6;
                --text-dark: #333;
                --text-medium: #555;
                --text-light: #777;
                --border-color: #e0e0e0;
                --success-bg: #e8f5e9;
                --success-text: #2e7d32;
                --error-bg: #ffebee;
                --error-text: #c62828;
                --shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f8f9fa;
                color: var(--text-dark);
                line-height: 1.6;
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }

            .main-content {
                flex: 1;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 2rem 1rem;
            }

            .profile-container {
                background: white;
                padding: 2.5rem;
                border-radius: 12px;
                box-shadow: var(--shadow);
                width: 100%;
                max-width: 900px;
                margin: 0 auto;
            }

            .profile-header {
                text-align: center;
                margin-bottom: 2rem;
                padding-bottom: 1rem;
                border-bottom: 1px solid var(--border-color);
            }

            .profile-header h1 {
                color: var(--text-dark);
                font-size: 2rem;
                font-weight: 600;
            }

            .profile-content {
                display: flex;
                gap: 3rem;
            }

            .avatar-section {
                flex: 0 0 250px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .avatar-img-container {
                position: relative;
                margin-bottom: 1.5rem;
            }

            .avatar-img {
                width: 200px;
                height: 200px;
                border-radius: 50%;
                object-fit: cover;
                border: 4px solid #f0f0f0;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
                transition: all 0.3s ease;
            }

            .avatar-img:hover {
                transform: scale(1.02);
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            }

            .avatar-upload {
                width: 100%;
                text-align: center;
            }

            .avatar-upload input[type="file"] {
                display: none;
            }

            .upload-btn {
                background-color: var(--primary-color);
                color: white;
                padding: 0.6rem 1.2rem;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 0.95rem;
                font-weight: 500;
                transition: all 0.3s ease;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                gap: 0.5rem;
            }

            .upload-btn:hover {
                background-color: var(--primary-hover);
                transform: translateY(-2px);
            }

            .profile-info {
                flex: 1;
                padding: 0.5rem;
            }

            .info-item {
                padding: 1rem 0;
                border-bottom: 1px solid var(--border-color);
                display: flex;
                align-items: center;
            }

            .info-item:last-child {
                border-bottom: none;
            }

            .info-label {
                font-weight: 600;
                min-width: 160px;
                color: var(--text-dark);
                font-size: 1rem;
            }

            .info-value {
                color: var(--text-medium);
                flex: 1;
                font-size: 1rem;
            }

            .message {
                padding: 1rem;
                margin: 1rem 0;
                border-radius: 6px;
                text-align: center;
                font-weight: 500;
            }

            .error-message {
                background-color: var(--error-bg);
                color: var(--error-text);
            }

            .success-message {
                background-color: var(--success-bg);
                color: var(--success-text);
            }

            @media (max-width: 768px) {
                .profile-content {
                    flex-direction: column;
                    gap: 2rem;
                }

                .avatar-section {
                    flex: 0 0 auto;
                    width: 100%;
                    margin-bottom: 1rem;
                }

                .info-item {
                    flex-direction: column;
                    align-items: flex-start;
                    gap: 0.3rem;
                }

                .info-label {
                    min-width: 100%;
                }
            }

            @media (max-width: 480px) {
                .profile-container {
                    padding: 1.5rem;
                }

                .avatar-img {
                    width: 150px;
                    height: 150px;
                }
            }
        </style>
    </head>
    <body>

        <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>

        <main class="main-content">
            <div class="profile-container">
                <div class="profile-header">
                    <h1>User Profile</h1>
                </div>

                <c:if test="${not empty error}">
                    <div class="message error-message">
                        ${error}
                    </div>
                </c:if>

                <c:if test="${not empty success}">
                    <div class="message success-message">
                        ${success}
                    </div>
                </c:if>

                <div class="profile-content">
                    <div class="avatar-section">


                        <div class="avatar-img-container">
                            <c:choose>
                                <c:when test="${hasAvatar}">
                                    <img class="avatar-img" 
                                         src="${pageContext.request.contextPath}/resources/avatarUser/${user.email}.png?version=${lastModified}"
                                         alt="User Avatar"
                                         onerror="this.src='${pageContext.request.contextPath}/resources/images/default-avatar.png?version=${lastModified}'">
                                </c:when>
                                <c:otherwise>
                                    <img class="avatar-img" 
                                         src="${pageContext.request.contextPath}/resources/avatarUser/default.jpg?version=${lastModified}"
                                         alt="Default Avatar">
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <form action="avatar" method="post" enctype="multipart/form-data" class="avatar-upload">
                            <input type="hidden" name="email" value="${user.email}">
                            <input type="file" id="avatar-upload" name="avatar" accept="image/*">
                            <label for="avatar-upload" class="upload-btn">
                                Change Avatar
                            </label>
                            <button type="submit" style="display: none;">Upload</button>
                        </form>

                    </div>

                    <div class="profile-info">
                        <div class="info-item">
                            <span class="info-label">Username:</span>
                            <span class="info-value">${user.username}</span>
                        </div>

                        <div class="info-item">
                            <span class="info-label">Email:</span>
                            <span class="info-value">${user.email}</span>
                        </div>

                        <div class="info-item">
                            <span class="info-label">Date of Birth:</span>
                            <span class="info-value">
                                <c:choose>
                                    <c:when test="${not empty user.dateOfBirth}">
                                        <fmt:formatDate value="${user.dateOfBirth}" pattern="dd/MM/yyyy"/>
                                    </c:when>
                                    <c:otherwise>Not available</c:otherwise>
                                </c:choose>
                            </span>
                        </div>

                        <div class="info-item">
                            <span class="info-label">User Type:</span>
                            <span class="info-value">${user.userType}</span>
                        </div>

                        <div class="info-item">
                            <span class="info-label">Status:</span>
                            <span class="info-value">
                                <c:choose>
                                    <c:when test="${user.status}">Active</c:when>
                                    <c:otherwise>Locked</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script>
            document.getElementById('avatar-upload').addEventListener('change', function () {
                if (this.files && this.files[0]) {
                    // Preview image
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        document.querySelector('.avatar-img').src = e.target.result;
                    }
                    reader.readAsDataURL(this.files[0]);

                    // Submit form
                    const form = this.closest('form');
                    const formData = new FormData(form);

                    // Show loading indicator
                    const uploadBtn = document.querySelector('.upload-btn');
                    const originalText = uploadBtn.textContent;
                    uploadBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Uploading...';
                    uploadBtn.disabled = true;

                    fetch(form.action, {
                        method: 'POST',
                        body: formData
                    })
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('Network response was not ok');
                                }
                                return response.text();
                            })
                            .then(result => {
                                if (result === 'success') {
                                    // Force reload the avatar image by adding timestamp
                                    const avatarImg = document.querySelector('.avatar-img');
                                    const newSrc = avatarImg.src.split('?')[0] + '?t=' + new Date().getTime();

                                    // Create new image element to preload
                                    const img = new Image();
                                    img.onload = function () {
                                        // After new image is loaded, update the src and redirect
                                        avatarImg.src = newSrc;
                                        setTimeout(() => {
                                            window.location.href = '${pageContext.request.contextPath}/profile';
                                        }, 500); // Small delay to ensure image is displayed
                                    };
                                    img.src = newSrc;
                                } else {
                                    throw new Error(result);
                                }
                            })
                            .catch(error => {
                                // Reset button
                                uploadBtn.innerHTML = originalText;
                                uploadBtn.disabled = false;
                            });
                }
            });
        </script>

    </body>
</html>