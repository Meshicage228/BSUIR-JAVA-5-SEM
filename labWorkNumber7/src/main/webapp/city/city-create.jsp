<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <meta charset="UTF-8">
    <link href="/css/App.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>

<form action="/citySinglePage" method="post" name="createCity">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">City title:</label>
        <input type="text" name="title" class="form-control" id="exampleInputEmail1">
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Total city square:</label>
        <input type="number" name="square" class="form-control" id="exampleInputPassword1">
    </div>
    <div class="mb-3">
        <label for="foundationYear" class="form-label">Year of foundation:</label>
        <input type="date" name="foundationYear" class="form-control" id="foundationYear">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>