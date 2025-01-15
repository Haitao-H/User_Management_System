<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management System</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/changePwd.css">
    <link rel="stylesheet" href="../css/userView.css">
</head>

<body>
    <header>
        <h1>User Management System</h1>
        <div>
            <p><span style="color: #fff21b"> ${userSession.userCode }</span> , welcome！</p>
            <a href="${pageContext.request.contextPath}/logout.do">Logout</a>
        </div>
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="${pageContext.request.contextPath}/jsp/user.do?method=query">
                <i class="fas fa-users"></i> User Management
            </a>
            <a href="${pageContext.request.contextPath}/jsp/changePwd.jsp">
                <i class="fas fa-key"></i> Change Password
            </a>
            <a href="${pageContext.request.contextPath}/logout.do">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>