<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${image.name}</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<div style="width:90%" class="center-block">
    <div class="jumbotron">
        <div class="container">
            <div class="col-md-8">
                <h1 style="margin-bottom:0px;">${image.name}</h1>
                <p style="float:left;margin-top:0px;margin-bottom:0px"><spring:message
                        code="photo.madeby"/> <a>${image.id}</a></p>
                <ul class="pager" style="margin:0px 0px 10px 0px;float:right">
                    <c:choose>
                        <c:when test="${image.id > 1}">
                            <li><a href="/viewphoto/page/?id=${image.id - 1}">&larr;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="disabled"><a href="#">&larr;</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li><a href="/viewphoto/page/?id=${image.id + 1}"><spring:message
                            code="photo.next"/> &rarr;</a></li>
                </ul>
                <img src="data:image/png;base64,${imageBytes}" style="width:100%">
            </div>
            <div id="about" class="col-md-4" style="float:right;margin-top:11%">
                <h3><spring:message code="photo.about"/></h3>
                <h4>
                    <table>
                        <tr>
                            <td style="padding:5px;"><spring:message code="photo.title"/></td>
                            <td style="padding:5px;">${image.name}</td>
                        </tr>
                        <tr>
                            <td style="padding:5px;"><spring:message code="photo.photographer"/></td>
                            <td style="padding:5px;">${image.id}</td>
                        </tr>
                        <tr>
                            <td style="padding:5px;"><spring:message code="photo.price"/></td>
                            <td style="padding:5px;">${price}</td>
                        </tr>
                    </table>
                </h4>
            </div>
        </div>
    </div>
</div>
</body>
</html>
