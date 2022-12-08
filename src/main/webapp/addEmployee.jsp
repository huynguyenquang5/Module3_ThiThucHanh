<%--
  Created by IntelliJ IDEA.
  User: huyng
  Date: 08/12/2022
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New Employee</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
  <script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container p-5 my-5">
  <h1>Create form</h1>
  <form action="/EmployeeServlet?action=add" method="post">
    <div class="mb-3 mt-3">
      <label for="name" class="form-label">Name</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="mb-3">
      <label for="address" class="form-label">Address</label>
      <input type="text" class="form-control" id="address" placeholder="Enter quantity" name="address">
    </div>
    <div class="mb-3">
      <label for="phoneNumber" class="form-label">PhoneNumber</label>
      <input type="text" class="form-control" id="phoneNumber" placeholder="Enter quantity" name="phoneNumber">
    </div>
    <div class="mb-3">
      <label for="salary" class="form-label">Salary</label>
      <input type="text" class="form-control" id="salary" placeholder="Enter quantity" name="salary">
    </div>
    <div class="mb-3">
      <label for="department" class="form-label">Department</label>
      <select id="department" name="departments" class="form-control custom-select bg-white border-left-0 border-md">
        <option value="">Open this select menu</option>
        <c:forEach items="${departments}" var="d">
          <option value="${d.getId()}"><c:out value="${d.getName()}"/></option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>
</body>
</html>
