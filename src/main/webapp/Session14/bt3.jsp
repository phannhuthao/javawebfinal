<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.lang.value != null ? cookie.lang.value : 'en'}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>BT3</title>
</head>
<body>
<h1><fmt:message key="greeting"/></h1>
<p><fmt:message key="instruction"/></p>

<form action="/changeLanguage" method="post">
    <select name="lang" onchange="this.form.submit()">
        <option value="en" ${cookie.lang.value == 'en' ? 'selected' : ''}>English</option>
        <option value="vi" ${cookie.lang.value == 'vi' ? 'selected' : ''}>Tiếng Việt</option>
    </select>
</form>
</body>
</html>
