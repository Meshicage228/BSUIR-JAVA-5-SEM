<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cities</title>
    <style> <%@include file="/WEB-INF/static/main-page-style.css" %> </style>
</head>
<body>
<div class="container">
    <div class="sidebar">
        <h2>Поиск и фильтрация</h2>
        <form action="/search/citizenByLanguage" method="get">
            <label for="language">Жители, говорящие на языке в городе:</label>
            <input type="text" id="language" name="language" placeholder="Введите язык">
            <input type="text" id="city" name="cityTitle" placeholder="Введите город">
            <button type="submit">Поиск</button>
        </form>

        <form action="/search/citiesByCitizenType" method="get">
            <label for="citizenType">Города с жителями типа:</label>
            <input type="text" id="citizenType" name="citizenType" placeholder="Введите тип">
            <button type="submit">Поиск</button>
        </form>

        <form action="/search/cityByPopulation" method="get">
            <label for="population">Город с населением:</label>
            <input type="text" id="population" name="population" placeholder="Введите число">
            <button type="submit">Поиск</button>
        </form>

        <form action="/search/oldestCitizenType" method="get" class="button">
            <button type="submit">Самый древний тип жителей</button>
        </form>

        <div class="button">
            <button type="button">
                <a href="/cities">Все города</a>
            </button>
        </div>

        <div class="button">
            <button type="button">
                <a href="/cities/create">Добавить город</a>
            </button>
        </div>
    </div>

    <div class="city-list">
        <h1>Список городов</h1>
        <div class="towns">
            <c:forEach items="${cities}" var="city">
                <div class="city-item">
                    <h2>${city.title}</h2>
                    <strong>Типы жителей:</strong>
                    <c:if test="${city.citizenTypesList.size() == 0}">
                        <p>Не указаны</p>
                    </c:if>
                    <c:if test="${city.citizenTypesList.size() != 0}">
                        <div class="citizen-type">
                            <c:forEach items="${city.citizenTypesList}" var="citizenType">
                                <div class="citizen-card">
                                    <p style="font-weight: bold;">Тип: ${citizenType.type}</p>
                                    <p>Язык: ${citizenType.spokenLanguage}</p>
                                    <p>Население типа: ${citizenType.population}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <p><strong>Площадь:</strong> ${city.square} кв. км</p>
                    <p><strong>Год основания:</strong> ${city.foundationYear}</p>
                    <a href="/city/update/${city.id}">Изменить</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>