<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<a href="<%=request.getContextPath()%>/new">Add New User</a>
<table>
    <thead>
    <tr>
        <th>userId</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>surName</th>
        <th>email</th>
        <th>phoneNumber</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.usersList}">

        <tr>
            <td><c:out value="${user.userId}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.surName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.phoneNumber}"/></td>
            <td><a href="${pageContext.request.contextPath}/show?id=<c:out value='${user.userId}'/>">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${user.userId}'/>">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>

</table>

</body>
</html>
