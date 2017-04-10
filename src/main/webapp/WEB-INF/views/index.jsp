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
    <c:if test="${not empty lists}">
        <c:forEach var="listValue" items="${lists}">
            <c:choose>
                <c:when test="${listValue.value.album}">
                    <a href="/album/page/?id=${listValue.key}">
                        <img src="/gallery/image?id=${listValue.key}" style='z-index: 2; left: 30px; border: 2px black;
                         -webkit-box-shadow: 8px 0px 6px -6px black;
	   -moz-box-shadow: 8px 0px 6px -6px black;
	        box-shadow: 8px 0px 6px -6px black;' alt="No image">
                        <img style='z-index: 1; width:70%; height:70%;' alt="No image">
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="viewphoto/page/?id=${listValue.key}">
                        <img src="/gallery/image?id=${listValue.key}">
                    </a>
                    <%--<a href="/gallery/random/">--%>
                    <%--<img src="data:img/png;base64, ${listValue}" alt="No image">--%>
                    <%--</a>--%>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</section>
<div id=pageNavigation align="center">
    <ul class="pagination">
        <c:choose>
            <c:when test="${pageNumber > 1}">
                <li><a href="/gallery/random/?pageNumber=${pageNumber - 1}">&laquo;</a></li>
            </c:when>
            <c:otherwise>
                <li class="disabled"><a href="#">&laquo;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

    <ul class="pagination">

        <c:forEach var="X" items="${loopCount}">
            <c:choose>
                <c:when test="${pageNumber - X < 1}"></c:when>
                <c:otherwise>
                    <li><a href="/gallery/random/?pageNumber=${pageNumber-X}">${pageNumber-X}</a></li>
                </c:otherwise>

            </c:choose>
        </c:forEach>
        <li class="disabled"><a href="#">${pageNumber}</a></li>

        <c:forEach var="X" items="${loopCount}">
            <c:choose>
                <c:when test="${pageNumber + X > pageCount}"></c:when>
                <c:otherwise>
                    <li><a href="/gallery/random/?pageNumber=${pageNumber+X}">${pageNumber+X}</a></li>
                </c:otherwise>

            </c:choose>
        </c:forEach>
    </ul>


    <ul class="pagination">
        <c:choose>
            <c:when test="${pageNumber < pageCount}">
                <li><a href="/gallery/random/?pageNumber=${pageNumber + 1}">&raquo;</a></li>
            </c:when>
            <c:otherwise>
                <li class="disabled"><a href="#">&raquo;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
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
