<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>

<body>
<header>
    <li><a href="<%=request.getContextPath()%>/api/list">Users</a></li>
    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
</header>

<c:if test="${requestScope.user != null}">
<form action="/api/updateUser" method="post">
    </c:if>
    <c:if test="${requestScope.user == null}">
    <form action="/api/insertUser" method="post">
        </c:if>

        <caption>
            <h2>
                <c:if test="${requestScope.user != null}"> Edit User
                </c:if>
                <c:if test="${requestScope.user == null}"> Add New User
                </c:if>
            </h2>
        </caption>

        <c:if test="${requestScope.user != null}">
            <input type="hidden" name="id" value="<c:out value='${requestScope.user.userId}' />"/>
        </c:if>

        <fieldset class="form-group">
            <label>User Name</label> <input type="text"
                                            value="<c:out value='${requestScope.user.name}' />"
                                            name="name" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>User Email</label> <input type="text"
                                             value="<c:out value='${requestScope.user.address}' />"
                                             name="address">
        </fieldset>

        <fieldset class="form-group">
            <label>User Country</label> <input type="text"
                                               value="<c:out value='${requestScope.user.phoneNumber}' />"
                                               name="phoneNumber">
        </fieldset>

        <button type="submit">Save</button>

    </form>


</body>

</html>
