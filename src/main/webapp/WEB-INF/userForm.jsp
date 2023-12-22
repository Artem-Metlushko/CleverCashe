<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>

<body>
<header>
    <li><a href="<%=request.getContextPath()%>/list">Users</a></li>
    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
</header>

<c:if test="${requestScope.user != null}">
<form action="/updateUser" method="post">
    </c:if>
    <c:if test="${requestScope.user == null}">
    <form action="/insertUser" method="post">
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
            <label>User firstName</label> <input type="text"
                                            value="<c:out value='${requestScope.user.firstName}' />"
                                            name="firstName" >
        </fieldset>

        <fieldset class="form-group">
            <label>User lastName</label> <input type="text"
                                             value="<c:out value='${requestScope.user.lastName}' />"
                                             name="lastName">
        </fieldset>

        <fieldset class="form-group">
            <label>User surName</label> <input type="text"
                                             value="<c:out value='${requestScope.user.surName}' />"
                                             name="surName">
        </fieldset>

        <fieldset class="form-group">
            <label>User Email</label> <input type="text"
                                             value="<c:out value='${requestScope.user.email}' />"
                                             name="email">
        </fieldset>


        <fieldset class="form-group">
            <label>User phoneNumber</label> <input type="text"
                                               value="<c:out value='${requestScope.user.phoneNumber}' />"
                                               name="phoneNumber">
        </fieldset>

        <button type="submit">Save</button>

    </form>


</body>

</html>
