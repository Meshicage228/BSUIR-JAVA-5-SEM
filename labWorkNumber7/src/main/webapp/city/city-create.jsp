<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cities</title>
    <style> <%@include file="/WEB-INF/static/style-create-city.css" %> </style>
</head>
<body>

<h1>Создать Город</h1>

<form action="/cities/create" method="post">
    <label for="title" title="Название города:">
        Название города:
        <input type="text" name="title" id="title" required />
    </label>

    <label for="square" title="Площадь города:">
        Площадь города:
        <input type="number" name="square" id="square" required />
    </label>

    <label for="foundationYear" title="Дата основания">
        Дата основания:
        <input name="foundationYear" id="foundationYear" type="date" required />
    </label>

    <button type="submit">Сохранить город</button>
</form>

<div class="back-button">
    <button type="button"> <a href="/cities">На главную</a></button>
</div>
</body>
</html>