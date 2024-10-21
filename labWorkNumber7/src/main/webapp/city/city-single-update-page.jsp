<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update City</title>
    <style> <%@include file="/WEB-INF/static/update-city-style.css" %> </style>
</head>
<body>

<div class="city-details">
    <h1>Обновить Город</h1>
    <c:if test="${not empty city}">
        <form action="/city/update/${city.id}" method="post">
            <div class="detail-item">
                <strong>Название города:</strong>
                <input type="text" name="title" value="${city.title}" required>
            </div>
            <div class="detail-item">
                <strong>Площадь:</strong>
                <input type="number" name="square" value="${city.square}" required>
            </div>
            <div class="detail-item">
                <strong>Год основания:</strong>
                <input type="date" name="foundationYear" value="${city.foundationYear}" required>
            </div>
            <input type="submit" value="Обновить">
        </form>
    </c:if>
    <c:if test="${empty city}">
        <div>Город не найден.</div>
    </c:if>

    <div class="back-button">
        <button><a href="/cities">На главную</a></button>
    </div>
</div>
</body>
</html>
