<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/checkMark.css"/>"/>
    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<spring:message code="account.username" var="lb_username" />
<spring:message code="account.password" var="lb_password" />
<spring:message code="account.name" var="lb_name" />
<spring:message code="account.email" var="lb_email" />
<spring:message code="account.type" var="lb_accounttype" />
<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="center-block" style="width:500px">
            <form:form class="form-horizontal" action="/registration/register/" commandName="newAccount">
                <fieldset>
                    <legend><spring:message code="screen.register"/></legend>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="account.username"/></label>
                        <div class="col-lg-9">
                            <form:input path="userName" type="text" class="form-control" id="username"
                                        placeholder="${lb_username}"/>
                        </div>
                    </div>
                    <div id="passwordFormGroup" class="form-group">
                        <label for="inputPassword" class="col-lg-3 control-label"><spring:message code="account.password"/></label>
                        <div class="col-lg-9">
                            <form:input path="password" name="inputPassword" type="password" class="form-control"
                                        id="inputPassword" placeholder="${lb_password}" onkeyup="passwordCheck()"/>

                            <p id="passwordInfo" class="text-danger hide"><small><spring:message code="message.password"/></small></p>
                        </div>
                        <svg id="checkmarkPassword" class="checkmark hide" viewBox="0 0 52 52">
                            <circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/>
                            <path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
                        </svg>
                    </div>
                    <div class="form-group">
                        <label for="inputName" class="col-lg-3 control-label"><spring:message code="account.name"/></label>
                        <div class="col-lg-9">
                            <form:input path="name" name="inputName" type="text" class="form-control" id="inputName"
                                        placeholder="${lb_name}"/>
                        </div>
                    </div>
                    <div id="emailFormGroup" class="form-group">
                        <label for="inputEmail" class="col-lg-3 control-label"><spring:message code="account.email"/></label>
                        <div class="col-lg-9">
                            <form:input path="email" name="inputEmail" type="text" class="form-control" id="inputEmail"
                                        placeholder="${lb_email}" onkeyup="emailCheck()"/>
                        </div>
                        <svg id="checkmarkEmail" class="checkmark hide" viewBox="0 0 52 52">
                            <circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/>
                            <path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
                        </svg>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="account.type"/></label>
                        <div class="col-lg-offset-4">
                            <label class="radio">
                                <form:radiobutton path="role" value="Customer" checked="checked"/>
                                <spring:message code="user.customer"/>
                            </label>
                            <label class="radio">
                                <form:radiobutton path="role" value="Photographer"/>
                                <spring:message code="user.photographer"/>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-3">
                            <button type="reset" class="btn btn-default"><spring:message code="command.cancel"/></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="command.register"/></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-3">
                            <spring:message code="account.alreadyaccount"/> <a href="/login/page/"><spring:message code="command.loginhere"/></a>
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
<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<script>
    function emailCheck() {
        var emailCheckMark = document.getElementById("checkmarkEmail");
        var emailInputElement = document.getElementById("emailFormGroup");
        var emailInput = document.getElementById("inputEmail").value;

        if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(emailInput)) {
            $(emailCheckMark).removeClass("hide");
            $(emailInputElement).removeClass("has-error");

        } else {
            $(emailCheckMark).addClass("hide");
            $(emailInputElement).addClass("has-error");
        }
    }
    function passwordCheck() {
        var passwordCheckmark = document.getElementById("checkmarkPassword");
        var passwordInputElement = document.getElementById("passwordFormGroup");
        var passwordInput = document.getElementById("inputPassword").value;
        var passwordInfo = document.getElementById("passwordInfo");

        if (/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$/.test(passwordInput)) {
            $(passwordCheckmark).removeClass("hide");
            $(passwordInfo).addClass("hide");
            $(passwordInputElement).removeClass("has-error");

        } else {
            $(passwordCheckmark).addClass("hide");
            $(passwordInfo).removeClass("hide");
            $(passwordInputElement).addClass("has-error");
        }
    }
</script>