<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý đơn hàng</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            max-width: 800px;
            margin: 30px auto;
            background: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgb(0 0 0 / 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            margin-bottom: 25px;
            background: white;
            padding: 15px 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgb(0 0 0 / 0.05);
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
            align-items: center;
        }
        form input[type=text],
        form input[type=number] {
            padding: 10px;
            font-size: 16px;
            border: 1.5px solid #ddd;
            border-radius: 5px;
            flex: 1 1 150px;
            transition: border-color 0.3s;
        }
        form input[type=text]:focus,
        form input[type=number]:focus {
            border-color: #007bff;
            outline: none;
        }
        form input[type=submit] {
            background-color: #007bff;
            border: none;
            color: white;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            flex: 0 0 auto;
            transition: background-color 0.3s;
        }
        form input[type=submit]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 10px rgb(0 0 0 / 0.1);
        }
        th, td {
            padding: 12px 18px;
            border-bottom: 1px solid #eee;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
            font-weight: 600;
        }
        tr:hover {
            background-color: #f1faff;
        }

        .btn {
            padding: 6px 14px;
            font-size: 14px;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            user-select: none;
            margin-right: 8px;
        }
        .btn-edit {
            background-color: #ffc107;
            color: #333;
            border: none;
        }
        .btn-edit:hover {
            background-color: #e0a800;
        }
        .btn-delete {
            background-color: #dc3545;
            color: white;
            border: none;
        }
        .btn-delete:hover {
            background-color: #b02a37;
        }
    </style>
</head>
<body>

<h1>Quản lý đơn hàng</h1>

<form action="bt5/addOrUpdate" method="post">
    <input type="text" name="id" placeholder="Mã đơn hàng" required
           value="${editOrder != null ? editOrder.id : ''}" ${editOrder != null ? 'readonly' : ''} />
    <input type="text" name="name" placeholder="Tên sản phẩm" required
           value="${editOrder != null ? editOrder.name : ''}" />
    <input type="number" name="quantity" placeholder="Số lượng" required min="1"
           value="${editOrder != null ? editOrder.quantity : ''}" />
    <input type="submit" value="${editOrder != null ? 'Cập nhật' : 'Thêm đơn hàng'}" />
</form>

<table>
    <thead>
    <tr>
        <th>Mã</th>
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.name}</td>
            <td>${order.quantity}</td>
            <td>
                <a class="btn btn-edit" href="bt5?editId=${order.id}">Sửa</a>
                <a class="btn btn-delete" href="bt5/delete?id=${order.id}"
                   onclick="return confirm('Bạn có chắc muốn xóa đơn hàng này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty orders}">
        <tr><td colspan="4" style="text-align:center;">Chưa có đơn hàng nào.</td></tr>
    </c:if>
    </tbody>
</table>

</body>
</html>
