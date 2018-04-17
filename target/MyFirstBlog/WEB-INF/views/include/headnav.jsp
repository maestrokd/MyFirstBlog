<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 11.02.2018
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">My First Blog</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/all/userhome">User's room</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/protected/users/userlist">User management panel</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><s:message code="property.button.language"/>
                        <span class="caret"></span> ${pageContext.response.locale}</a>
                    <ul class="dropdown-menu">
                        <li><a href="?lang=en">English</a></li>
                        <li><a href="?lang=ru">Russian</a></li>
                    </ul>
                </li>
                <%--<p class="navbar-text">${pageContext.response.locale}</p>--%>

                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a href="/userregistration"><span class="glyphicon glyphicon-user"></span> <s:message code="property.button.sign"/></a></li>
                    <li><a href="/newlogin"><span class="glyphicon glyphicon-log-in"></span> <s:message code="property.button.login"/></a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="#">Welcome : ${pageContext.request.userPrincipal.name}</a></li>
                    <c:url var="logoutUrl" value="/logout" />
                    <form action="${logoutUrl}" id="logout" class="navbar-form navbar-left" method="post">
                        <div class="input-group">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                            <div class="input-group-btn">
                                <%--<button class="btn btn-default" type="submit">Logout</button>--%>
                                <button class="btn btn-link" type="submit"><span class="glyphicon glyphicon-log-out"></span> <s:message code="property.button.logout"/></button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </ul>
        </div>
    </div>
</nav>