<%--
  Created by IntelliJ IDEA.
  User: huyng
  Date: 08/12/2022
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container p-5 my-5">
    <h1>Update form</h1>
    <form action="/EmployeeServlet?action=edit" method="post">
        <input type="text" value="${employee.getId()}" name="id" hidden>
        <div class="mb-3 mt-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" value="${employee.getName()}"
                   id="name" placeholder="Enter name" name="name">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="text" class="form-control" value="${employee.getEmail()}"
                   id="email" placeholder="Enter email" name="email">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" value="${employee.getAddress()}"
                   id="address" placeholder="Enter address" name="address">
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="text" class="form-control" value="${employee.getPhoneNumber()}"
                   id="phoneNumber" placeholder="Enter phone number" name="phoneNumber">
        </div>
        <div class="mb-3">
            <label for="salary" class="form-label">Salary</label>
            <input type="text" class="form-control" value="${employee.getSalary()}"
                   id="salary" placeholder="Enter salary" name="salary">
        </div>
        <div class="mb-3">
            <label for="department" class="form-label">Department:</label>
            <select id="department" name="departments" class="form-control custom-select bg-white border-left-0 border-md">
                <c:forEach items="${departments}" var="d">
                    <c:if test="${d.getId() != employee.getDepartment().getId()}">
                        <option value="${d.getId()}"><c:out value="${d.getName()}"/></option>
                    </c:if>
                    <c:if test="${d.getId() == employee.getDepartment().getId()}">
                        <option selected value="${d.getId()}"><c:out value="${d.getName()}"/></option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Edit</button>
        </div>
    </form>
</div>
</body>
</html>
