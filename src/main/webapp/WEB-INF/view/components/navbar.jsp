<%-- 
    Document   : navbar
    Created on : Mar 8, 2025, 5:43:41 PM
    Author     : Hyundinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="startup.zeroli.config.ProjectPaths" %>

<header class="header">
    <div class="navbar-header">
        <a href="#" class="logo" title="logo">
            <img src="resources/logo_name.png" alt="ZeroLi Logo" height="100px">
        </a>

        <ul class="nav-links">
            <li><a href="<%= ProjectPaths.HREF_TO_HOMEPAGE %>">HomePage</a></li>
            <li><a href="<%= ProjectPaths.HREF_TO_FRIENDPAGE %>">Friends</a></li>


            <li class="dropdown-icon">
                <a href="#">Art
                    <ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="<%= ProjectPaths.HREF_TO_CANVASPAGE%>">Canvas</a>
                        <a href="#">Showroom</a>
                    </div>
                </div>
            </li>



            <li class="dropdown-icon">
                <a href="#">Books<ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="<%= ProjectPaths.HREF_TO_BOOKPAGE%>">Read Books</a>
                        <a href="<%= ProjectPaths.HREF_TO_SALEBOOKPAGE%>">Buy Books</a>
                        <a href="<%= ProjectPaths.HREF_TO_SERVICE%>">BookStores</a>
                        <a href="#">AI-Service</a>
                    </div>
                </div>
            </li>
            
            <li class="dropdown-icon">
                <a href="#">Others<ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="<%= ProjectPaths.HREF_TO_PRODUCTPAGE%>">Electronic Shop</a>
                    </div>
                </div>
            </li>

            <li><a href="#">Announcements</a></li>
            
            <li class="dropdown-icon">
                <a href="#">Personality<ion-icon name="caret-down-outline" style="margin-bottom: -4px;"></ion-icon>
                </a>
                <div class="dropdown">
                    <div class="dropdown-category">
                        <a href="<%= ProjectPaths.HREF_TO_PROFILEPAGE%>">Profile</a>
                        <a href="<%= ProjectPaths.HREF_TO_CARTPAGE %>">Shopping Cart</a>
                        <a href="#">History</a>
                    </div>
                </div>
            </li>
        </ul>

        <div class="icons">
            <a href="#"><ion-icon name="search-outline"></ion-icon></a>
            <!-- Icon specifically for login -->
            <a href="<%= ProjectPaths.HREF_TO_LOGINPAGE%>"><ion-icon name="person-circle-outline"></ion-icon></a>
        </div>
    </div>
</header>

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
    }
    .header {
        position: relative;
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
        transition: opacity 0.3s ease, transform 0.3s ease, visibility 10s ease;
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
</style>

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
