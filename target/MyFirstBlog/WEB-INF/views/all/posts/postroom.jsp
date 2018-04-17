<%--<!DOCTYPE html>--%>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 21.12.2017
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Post Room</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="../../include/headnav.jsp" %>
    <div class="container-fluid">
        <div class="row content">
            <div class="col-sm-3">
            </div>

            <div class="col-sm-9">
                <c:if test="${not empty message}">
                    <div class="alert alert-success alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> ${message}.
                    </div>
                </c:if>

                <hr>
                <h2>${selectedPost.title} <small>(id: ${selectedPost.id})</small></h2>
                <h5><span class="glyphicon glyphicon-time"></span> Post by <strong>${selectedPost.user.login}</strong>, ${selectedPost.date}.</h5>
                <p>${selectedPost.body}</p>
                <c:if test="${pageContext.request.userPrincipal.name == selectedPost.user.login}">
                    <button onclick="location.href='/all/posts/${selectedPost.id}/update'" class="btn btn-primary">Update</button>
                    <button onclick="location.href='/all/posts/${selectedPost.id}/delete?login=${selectedPost.user.login}'" class="btn btn-danger">Delete</button>
                </c:if>

            </div>
        </div>
    </div>
</body>
</html>
