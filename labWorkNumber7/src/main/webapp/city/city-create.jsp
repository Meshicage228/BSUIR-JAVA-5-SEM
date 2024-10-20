<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Create City</h1>

<form action="/cities/create" method="post">
    <label for="title" title="Название городаasfasfafsa:">
        <input type="text" name="title" id="title" required />
    </label>

    <label for="square" title="Площадь города:">
        <input type="number" name="square" id="square" required />
    </label>

    <label for="foundationYear" title="Дата основания">
        <input name="foundationYear" id="foundationYear" type="date" required />
    </label>

    <button type="submit">Сохранить город</button>
</form>

<button type="button" name="back"> <a href="/cities">На главную</a></button>

</body>
</html>