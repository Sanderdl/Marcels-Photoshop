<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="screen.login" /></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/gallery/random/">Home</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/createalbum/page/">Create album <span class="sr-only">(current)</span></a></li>
                <li><a href="/registerproduct/page/">Upload picture</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.User}">
                        <li><a href="#"><c:out value="${sessionScope.User.userName}"/></a></li>
                        <li><a href="/login/page/">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href= "/registration/page/">Register</a> </li>
                        <li class="active"><a href="/login/page/">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-5 col-md-offset-3">
            <form:form class="form-horizontal" action="/login/submit/" commandName="newLogin">
                <fieldset>
                    <legend>Login</legend>
                    <div class="form-group">
                        <label for="inputUsername" class="col-lg-2 control-label"><spring:message code="account.username" /></label>
                        <div class="col-lg-10">
                            <form:input path="username" name="inputUsername" type="text" class="form-control"
                                        id="username" placeholder="Username"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-lg-2 control-label"><spring:message code="account.password" /></label>
                        <div class="col-lg-10">
                            <form:input path="password" name="inputPassword" type="password" class="form-control"
                                        id="inputPassword" placeholder="Password"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button type="reset" class="btn btn-default"><spring:message code="command.cancel" /></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="command.login" /></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <spring:message code="account.needaccount"/> <a href="/registration/page/"><spring:message code="command.registerhere"/></a>
                        </div>
                    </div>
                </fieldset>
            </form:form>
            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                    <c:out value="${message}"/>
                </div>
            </c:if>
        </div>
        ${pageContext.response.locale}
    </div>
</div>
</body>
</html>
