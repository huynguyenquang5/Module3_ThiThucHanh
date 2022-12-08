<%--
  Created by IntelliJ IDEA.
  User: huyng
  Date: 08/12/2022
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List Employee</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <h1>List Employee</h1>
    <h2><c:if test="${not empty message}">
        <c:out value="${message}"/>
        <c:set var="message" value="" scope="session"/>
    </c:if></h2>
    <a href="EmployeeServlet?action=add">
        <button style="margin-bottom: 10px" class="btn btn-primary">
            Add new product
        </button>
    </a>
    <form action="EmployeeServlet?action=search" method="post">
        <input type="text" name="search" placeholder="Enter name search">
        <button class="btn btn-primary">Search</button>
    </form>
    <table class="table table-striped">
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>PhoneNumber</th>
            <th>Salary</th>
            <th>Department</th>
            <th colspan="2" style="width: 25%;">Action</th>
        </tr>
        <c:forEach items="${employee}" var="e">
            <tr>
                <td><c:out value="${e.getId()}"/></td>
                <td><c:out value="${e.getName()}"/></td>
                <td><c:out value="${e.getEmail()}"/></td>
                <td><c:out value="${e.getAddress()}"/></td>
                <td><c:out value="${e.getPhoneNumber()}"/></td>
                <td><c:out value="${e.getSalary()}"/></td>
                <td><c:out value="${e.getDepartment().getName()}"/></td>
                <td>
                    <a href="/EmployeeServlet?action=edit&id=${e.getId()}">
                        <button class="btn btn-primary">
                            Edit
                        </button>
                    </a>
                </td>
                <td>
                    <a href="#">
                        <button onclick="referenceDelete(${e.getId()})" data-bs-toggle="modal"
                                data-bs-target="#delete" class="btn btn-danger">
                            Delete
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- Modal delete -->
<div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Confirm</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure want to delete?
            </div>
            <div class="modal-footer" id="delete-parameter">
                <a href="EmployeeServlet?action=delete" onclick="location.href=this.href+'&id='+a;return false;" id="a">
                    <button type="button" class="btn btn-primary">Yes</button></a>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="ModalDelete.js"></script>
