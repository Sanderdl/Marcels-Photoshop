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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<spring:message code="account.username" var="lb_username" />
<spring:message code="account.password" var="lb_password" />
<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="center-block" style="width:500px">
            <form:form class="form-horizontal" action="/login/submit/" commandName="newLogin">
                <fieldset>
                    <legend>Login</legend>
                    <div class="form-group">
                        <label for="inputUsername" class="col-lg-3 control-label" style="text-align:left"><spring:message code="account.username"/></label>
                        <div class="col-lg-8">
                            <form:input path="username" name="inputUsername" type="text" class="form-control"
                                        id="username" placeholder="${lb_username}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-lg-3 control-label" style="text-align:left"><spring:message code="account.password" /></label>
                        <div class="col-lg-8">
                            <form:input path="password" name="inputPassword" type="password" class="form-control"
                                        id="inputPassword" placeholder="${lb_password}"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-3">
                            <button type="reset" class="btn btn-default"><spring:message code="command.cancel" /></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="command.login" /></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-3">
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
    </div>
</div>
</body>
</html>
