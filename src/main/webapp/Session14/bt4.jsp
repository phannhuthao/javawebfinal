<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>BT4 - Giỏ hàng</title>
  <style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
      padding: 8px;
    }
    th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
<h2>📝 Thêm sản phẩm vào giỏ hàng</h2>
<form method="post" action="/bt4Show">
  <input type="text" name="name" placeholder="Tên sản phẩm" required>
  <input type="number" name="quantity" placeholder="Số lượng" required>
  <input type="submit" value="Thêm vào giỏ hàng">
</form>

<hr/>

<h2>Giỏ hàng của bạn:</h2>
<c:if test="${not empty cart}">
  <table>
    <tr>
      <th>STT</th>
      <th>Tên và số lượng</th>
      <th>Hành động</th>
    </tr>
    <c:forEach items="${cart}" var="item" varStatus="loop">
      <tr>
        <td>${loop.index + 1}</td>
        <td>${item}</td>
        <td><a href="/remove?index=${loop.index}">Xóa</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<c:if test="${empty cart}">
  <p>Giỏ hàng đang trống.</p>
</c:if>

<hr/>

<h2>Sản phẩm từ Cookie:</h2>
<c:if test="${not empty products}">
  <table>
    <tr>
      <th>STT</th>
      <th>Tên sản phẩm (Cookie)</th>
    </tr>
    <c:forEach items="${products}" var="prod" varStatus="loop">
      <tr>
        <td>${loop.index + 1}</td>
        <td>${prod}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<c:if test="${empty products}">
  <p>Không có sản phẩm nào được lưu trong cookie.</p>
</c:if>

</body>
</html>
