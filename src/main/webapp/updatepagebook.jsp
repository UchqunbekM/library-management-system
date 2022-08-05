<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title> UPdate BOOK</title>


    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">

    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>


</head>
<body>
<h1>Update  BOOK</h1>
<br/>

<div class="row">
    <div class="col-md-6 offset-3">


<form action="/update" method="post" enctype="multipart/form-data">

    <div class="form-group">

    <input  value="${book.getId()}" type="hidden" name="id">


    <label for="title">Title:</label>

    <input id="title"  value="${book.getTitle()}" type="text" name="title">
    </div>

    <div class="form-group">
    <label for="description">Description:</label>

        <textarea  id="description"  name="description">${book.getDescription()}</textarea></div>

    <label for="authorsIds">Title:</label>
    <select id="authorsIds" multiple name="authorsIds">
        <option value="0">Select authors:</option>
        <c:forEach items="${authors}" var="author">
            <option value="${author.getId()}">${author.getFullName()}</option>
        </c:forEach>
    </select>

    <br>
    <label for="categoryId">Select category:</label>

    <select id="categoryId" name="categoryId "  required>
        <option disabled value="0">Select category:</option>
        <c:forEach items="${categoryList}" var="category">
            <option value="${category.getId()}">${category.getName()}</option>
        </c:forEach>
    </select>

    <br>
    <label for="isbn">Isbn:</label>

    <input id="isbn" value="${book.getIsbn()}" type="text" name="isbn">

    <br>
    <label for="year">Year:</label>

    <input id="year" value="${book.getYear()}" type="number" name="year">

    <br>

    <label for="quantity">Quantity:</label>

    <input id="quantity" value="${book.getQuantity()}" type="number" name="quantity">

    <br>


    <label for="image">Upload cover image:</label>

    <input  id="image"  value="null" type="file" name="image">

    <input  value="${book.getImgUrl()}"  type="text"  name="url">

    <br>

    <input type="submit" value="Saqlash">


</form>
    </div>
</div>
</body>
</html>