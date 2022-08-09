<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>CodePen - CSS-Only Accessible Floating Labels</title>
    <link rel="stylesheet" href="includes/demo.css">

</head>
<body>
<body>
<div class="session">
    <div class="left">
        <?xml version="1.0" encoding="UTF-8"?>
        <svg enable-background="new 0 0 300 302.5" version="1.1" viewBox="0 0 300 302.5" xml:space="preserve" xmlns="http://www.w3.org/2000/svg">
<style type="text/css">
    .st0{fill:#fff;}
</style>
            <path class="st0" d="m126 302.2c-2.3 0.7-5.7 0.2-7.7-1.2l-105-71.6c-2-1.3-3.7-4.4-3.9-6.7l-9.4-126.7c-0.2-2.4 1.1-5.6 2.8-7.2l93.2-86.4c1.7-1.6 5.1-2.6 7.4-2.3l125.6 18.9c2.3 0.4 5.2 2.3 6.4 4.4l63.5 110.1c1.2 2 1.4 5.5 0.6 7.7l-46.4 118.3c-0.9 2.2-3.4 4.6-5.7 5.3l-121.4 37.4zm63.4-102.7c2.3-0.7 4.8-3.1 5.7-5.3l19.9-50.8c0.9-2.2 0.6-5.7-0.6-7.7l-27.3-47.3c-1.2-2-4.1-4-6.4-4.4l-53.9-8c-2.3-0.4-5.7 0.7-7.4 2.3l-40 37.1c-1.7 1.6-3 4.9-2.8 7.2l4.1 54.4c0.2 2.4 1.9 5.4 3.9 6.7l45.1 30.8c2 1.3 5.4 1.9 7.7 1.2l52-16.2z"></path>
</svg>
    </div>
    <form action="updateuser"  class="log-in" autocomplete="off">
        <h4>Welcome!<span></span></h4>
        <p>Welcome! To Update user:</p>
        <c:forEach var="userlist" items="${list}">
        <div class="floating-label">
            <input type="text" value="${userlist.username}" name="username" id="username">
            <label for="username">Name:</label>
        </div>
        <div class="floating-label">
            <input value="${userlist.lastname}" type="text" name="lastname" id="lastname" autocomplete="off">
            <label for="lastname">Lastname:</label>
        </div>
        <div class="floating-label">
            <input value="${userlist.password}" type="password" name="password" id="password" autocomplete="off">
            <label for="password">Password:</label>
        </div>
            <button type="submit">Save</button>
        </c:forEach>
    </form>
</div>
</body>


</body>
</html>