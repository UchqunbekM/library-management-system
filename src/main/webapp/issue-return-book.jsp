
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BOOK PAGE</title>

    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body>

<%--<%@include file="includes/issuedNavbar.jsp" %>--%>

<section class="container mt-4 ">
    <form action="/addissue" method="post">
    <label for="userId">Select Student:</label>

    <select id="userId" name="userId">
        <option disabled value="0">Select user:</option>
        <c:forEach items="${userList}" var="user">
            <option value="${user.getId()}">${user.getUsername()}</option>
        </c:forEach>
    </select>
    <label for="bookId">Select book:</label>
    <select id="bookId" name="bookId">
        <option disabled value="0">Select book:</option>
        <c:forEach items="${bookList}" var="book">
            <option value="${book.getId()}">${book.getTitle()}</option>
        </c:forEach>
    </select>
    <fieldset>
        <legend>Select Operation:</legend>
        <div>
            <input type="radio" id="huey" name="issued" value="true" checked>
            <label for="huey">Issued</label>
        </div>
        <div>
            <input type="radio" id="dewey" name="issued" value="false">
            <label for="dewey">Returned</label>
        </div>
    </fieldset>
        <input type="submit" name="ADD">
    </form>

</section>
</body>
</html>
