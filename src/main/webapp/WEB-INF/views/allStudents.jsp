<%--
  Created by IntelliJ IDEA.
  User: EVA
  Date: 03.04.2021
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Students</title>
</head>
<body>
<br>
<br>
<br>
<a href="${pageContext.request.contextPath}/addStudent">Add User</a>
<br>
<h3>List of all users</h3>
${message}
<br>
<br>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td><a
                    href="${pageContext.request.contextPath}/editStudent/${student.id}">Edit</a></td>
            <td><a
                    href="${pageContext.request.contextPath}/deleteStudent/${student.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>


</table>
</body>
</html>
