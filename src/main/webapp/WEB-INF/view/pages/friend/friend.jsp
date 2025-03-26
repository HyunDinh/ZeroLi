<%@ page import="java.util.List, model.Message, model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/chat.js"></script>
</head>
<body>
    <h2>Chào, <%= ((User) session.getAttribute("user")).getUsername() %>!</h2>
    <a href="logout">Đăng xuất</a>

    <h3>Danh sách bạn bè</h3>
    <% List<User> friends = (List<User>) request.getAttribute("friends");
       for (User f : friends) { %>
        <p><%= f.getUsername() %></p>
    <% } %>

    <h3>Tin nhắn</h3>
    <div id="chat-box">
        <% List<Message> messages = (List<Message>) request.getAttribute("messages");
           for (Message msg : messages) { %>
            <p><b><%= msg.getSenderId() %>:</b> <%= msg.getContent() %></p>
        <% } %>
    </div>

    <input type="text" id="message" placeholder="Nhập tin nhắn...">
    <button onclick="sendMessage()">Gửi</button>
</body>
</html>
