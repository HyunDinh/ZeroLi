<%-- 
    Document   : homePage
    Created on : Mar 8, 2025, 6:02:23 PM
    Author     : Hyundinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomePage</title>
    </head>
    <body>
        <% request.getRequestDispatcher("/WEB-INF/view/components/navbar.jsp").include(request, response); %>
    </body>
</html>
