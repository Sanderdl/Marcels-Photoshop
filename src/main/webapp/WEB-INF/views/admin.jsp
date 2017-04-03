<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="screen.admin" /></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-5 col-md-offset-3">
            <form:form class="form-horizontal" action="/registerproduct/submit/" commandName="Extras"
                       enctype="multipart/form-data">
                <fieldset>
                    <legend>Admin console</legend>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <h4><spring:message code="admin.allproducts"/></h4>
                            <c:if test="${not empty availableProducts}">

                                <c:forEach var="product" items="${availableProducts}">
                                    <div class="checkbox">
                                        <label>
                                            <form:checkbox path="extras" value="${product.id}"/>
                                            <spring:message code="${product.name}"/>
                                        </label>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button type="reset" class="btn btn-default"><spring:message code="command.cancel"/></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="command.upload"/></button>
                        </div>
                    </div>
                </fieldset>
            </form:form>

        </div>

</body>
</html>
