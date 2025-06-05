<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<h2>Đăng nhập</h2>
<form action="/bt1" method="post">
    <input type="text" name="username" placeholder="Nhập tên người dùng..." required><br><br>
    <input type="password" name="password" placeholder="Nhập mật khẩu..." required><br><br>
    <input type="submit" value="Đăng nhập">
</form>
<br>
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<p style="color: red;"><%= error %></p>
<% } %>
</body>
</html>
