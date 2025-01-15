<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - UMS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        .login-container h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .login-container form {
            display: flex;
            flex-direction: column;
        }

        .login-container form label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }

        .login-container form input {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .login-container form button {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .login-container form button:hover {
            background-color: #0056b3;
        }

        .login-container .info {
            color: red;
            font-size: 14px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>User Management System</h1>
    <form action="${pageContext.request.contextPath}/login.do" method="post">

        <div class="info">${error}</div>

        <label for="userCode">Username</label>
        <input type="text" id="userCode" name="userCode" placeholder="Enter your username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>

        <input type="submit" value="Login">
        <input type="reset" value="Reset">
    </form>
</div>
</body>
</html>
