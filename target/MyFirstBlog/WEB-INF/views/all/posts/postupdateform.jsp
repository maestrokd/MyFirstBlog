<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 22.12.2017
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Post Add Form</title>
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
                <sf:form class="form-horizontal" action="/all/posts/${updatedPostForm.id}/update" modelAttribute = "updatedPostForm" >
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="title"><s:message code="property.enterYourTitle"/></label>
                        <div class="col-sm-9">
                            <sf:input class="form-control" path ="title" placeholder="Your Title" value="${updatedPostForm.title}" />
                            <sf:errors path="title" class="text-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="body"><s:message code="property.enterYourBody"/></label>
                        <div class="col-sm-9">
                            <sf:textarea class="form-control" path ="body" placeholder="Your text" value="${updatedPostForm.body}" rows="10" />
                            <sf:errors path="body" class="text-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="text-center">
                            <strong>Author: </strong> ${updatedPostForm.user.login}
                            <strong>Date: </strong> ${updatedPostForm.date}
                        </p>
                    </div>
                    <div class="col-sm-offset-2">
                        <button type="submit" class="btn btn-default" ><s:message code="property.button.updatePost"/></button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>
</body>
</html>
