<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
</head>
<body>
<h2>Thêm sản phẩm mới</h2>
<form action="/bt2" method="post">
    <input type="text" name="id" placeholder="Nhập mã sản phẩm..." required><br><br>
    <input type="text" name="name" placeholder="Nhập tên sản phẩm..." required><br><br>
    <input type="number" name="price" placeholder="Nhập giá..." required><br><br>
    <input type="submit" value="Thêm sản phẩm">
</form>

<h2>Danh sách sản phẩm</h2>
<%
    List<String> products = (List<String>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
        for (String product : products) {
            String[] parts = product.split(" - ");
            String productId = parts[0];
%>
<p><%= product %><a href="/bt2/delete?id=<%= productId %>" style="color:red;">[Xóa]</a> </p>
<%
    }
} else {
%>
<p>Chưa có sản phẩm nào.</p>
<%
    }
%>
</body>
</html>
