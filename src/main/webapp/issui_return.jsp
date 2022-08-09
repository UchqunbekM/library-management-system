<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="includes/style_view.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>

<body>
<div class="card">
  <div class="table-title">
    <h2>Issue-return Book </h2>

  </div>
  <div class="button-container"><span><a href="Admin_panel.html" >Back to panel</a></span>
    <button class="danger" title="Delete Selected">
      <svg viewBox="0 0 448 512" width="16" title="trash-alt">
        <path d="M32 464a48 48 0 0 0 48 48h288a48 48 0 0 0 48-48V128H32zm272-256a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zm-96 0a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zm-96 0a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zM432 32H312l-9.4-18.7A24 24 0 0 0 281.1 0H166.8a23.72 23.72 0 0 0-21.4 13.3L136 32H16A16 16 0 0 0 0 48v32a16 16 0 0 0 16 16h416a16 16 0 0 0 16-16V48a16 16 0 0 0-16-16z"></path>
      </svg>
    </button>
    <button class="primary" title="Add New Data">
      <svg viewBox="0 0 512 512" width="16" title="plus-circle">
        <path d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm144 276c0 6.6-5.4 12-12 12h-92v92c0 6.6-5.4 12-12 12h-56c-6.6 0-12-5.4-12-12v-92h-92c-6.6 0-12-5.4-12-12v-56c0-6.6 5.4-12 12-12h92v-92c0-6.6 5.4-12 12-12h56c6.6 0 12 5.4 12 12v92h92c6.6 0 12 5.4 12 12v56z"></path>
      </svg>
    </button>
  </div>
  <div class="table-concept">
    <input class="table-radio" type="radio" name="table_radio" id="table_radio_0" checked="checked"/>
    <div class="table-display">
    </div>
    <table>
      <thead>
      <tr>
        <th>User's Name</th>
        <th>Book's Name</th>
        <th>Date</th>
        <th>Status</th>
        <th>Delete</th>
        <th>Update</th>

      </tr>
      </thead>
      <tbody>
      <c:forEach var="list" items="${list}">
        <tr>
          <td><strong></strong>${list.username}</td>
          <td>${list.title}</td>
          <td>${list.date}</td>
          <td>${list.issued}</td>
         <td> <a href="delissu?id=${list.getId()}"><b>Delete</b></a></td>
          <td> <a href="upissu?id=${list.getId()}"><b>Update</b></a></td>
          <td>${list.issued}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
