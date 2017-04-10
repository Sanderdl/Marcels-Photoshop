<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marcel's Photo Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/justifiedGallery.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<section id="photos" class="justified-gallery shadow-m">
    <h1>${album.name}</h1>
    <c:if test="${not empty map}">
        <c:forEach var="listValue" items="${map}">
            <a href="/viewphoto/page/?id=${listValue.key}">
                <img src="/album/image?id=${listValue.key}">
            </a>
        </c:forEach>
    </c:if>
</section>

</body>
<script src="<c:url value="/resources/js/jquery.justifiedGallery.min.js"/>"></script>
<script>
    $(function () {
        $("#photos").justifiedGallery({
            rowHeight: 130,
            maxRowHeight: -1,
            margins: 6,
            captions: true
        });
    });
</script>
</html>

