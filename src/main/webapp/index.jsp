<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link type="text/css" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <h1>List Employee</h1>
    <h2><c:if test="${not empty messageTelephone}">
        <c:out value="${messageTelephone}"/>
        <c:set var="messageTelephone" value="" scope="session"/>
    </c:if></h2>
</div>
</body>
</html>
