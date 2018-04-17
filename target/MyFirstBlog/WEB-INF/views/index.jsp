<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="include/headnav.jsp" %>
    <div class="container-fluid">
        <div class="row content">
            <div class="col-sm-3">
                <a href="user">Go to the all users page</a><br>
                <a href="userregistration">Registration</a><br>
                <a href="newlogin">Login</a><br>
                <a href="protected">Go to the protected page</a><br>
                <a href="confidential">Go to the confidential page</a><br>
                <a href="/protected/users/userlist">User List</a><br>
            </div>

            <div class="col-sm-9">
                <h2 class="text-center">Welcome page</h2>
                <c:if test="${!empty postList}">
                    <c:forEach var="listItem" items="${postList}">
                        <hr>
                        <h2>${listItem.title} <small>(id: ${listItem.id})</small></h2>
                        <h5><span class="glyphicon glyphicon-time"></span> Post by <strong>${listItem.user.login}</strong>, ${listItem.date}.</h5>
                        <p>${listItem.body}</p>
                        <button onclick="location.href='/all/posts/${listItem.id}'" class="btn btn-info">Info</button>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
                    <%--<table class="table table-striped">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>Id</th>--%>
                            <%--<th>Title</th>--%>
                            <%--<th>Date</th>--%>
                            <%--<th>Action</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<c:forEach var="listItem" items="${postList}">--%>
                            <%--<tr>--%>
                                <%--<td value="${listItem}">${listItem.id}</td>--%>
                                <%--<td value="${listItem}">${listItem.title}</td>--%>
                                <%--<td value="${listItem}">${listItem.date}</td>--%>
                                <%--<td>--%>
                                    <%--<button onclick="location.href='/all/posts/${listItem.id}'" class="btn btn-info">Info</button>--%>
                                    <%--&lt;%&ndash;<button onclick="location.href='/all/posts/${listItem.id}/update'" class="btn btn-primary">Update</button>&ndash;%&gt;--%>
                                    <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
                                        <%--<c:if test="${selectedUser != null}">--%>
                                            <%--&lt;%&ndash;<button onclick="location.href='/all/posts/${listItem.id}/delete?login=${selectedUser.login}'" class="btn btn-danger">Delete</button>&ndash;%&gt;--%>
                                        <%--</c:if>--%>
                                    <%--</sec:authorize>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
