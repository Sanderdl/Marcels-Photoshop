<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marcel's Photo Shop</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/justifiedGallery.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="header.jsp" %>
<section id="photos" class="justified-gallery shadow-m">
    <c:if test="${not empty lists}">
        <c:forEach var="listValue" items="${lists}">
            <a href="/viewphoto/page/?id=${listValue.key}">
                <img src="/gallery/image?id=${listValue.key}">
            </a>
            <%--<a href="/gallery/random/">--%>
            <%--<img src="data:img/png;base64, ${listValue}" alt="No image">--%>
            <%--</a>--%>
        </c:forEach>
    </c:if>
</section>
<div id=pageNavigation class="col-md-2 col-md-offset-5" align="center">
    <c:choose>
        <c:when test="${pageNumber > 1}">
            <ul class="pagination pagination-lg">
                <li><a href="/gallery/random/?pageNumber=${pageNumber - 1}">&laquo;</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="pagination pagination-lg">
                <li class="disabled"><a href="#">&laquo;</a></li>
            </ul>
        </c:otherwise>
    </c:choose>

    <c:forEach var="X" items="${loopCount}">
        <c:choose>
            <c:when test="${pageNumber - X < 1}"></c:when>
            <c:otherwise>
                <a href="/gallery/random/?pageNumber=${pageNumber-X}">${pageNumber-X}</a>
            </c:otherwise>
            
        </c:choose>
    </c:forEach>
    ${pageNumber}

    <c:forEach var="X" items="${loopCount}">
        <c:choose>
            <c:when test="${pageNumber + X > pageCount}"></c:when>
            <c:otherwise>
                <a href="/gallery/random/?pageNumber=${pageNumber+X}">${pageNumber+X}</a>
            </c:otherwise>

        </c:choose>
    </c:forEach>


    <c:choose>
        <c:when test="${pageNumber < pageCount}">
            <ul class="pagination pagination-lg">
                <li><a href="/gallery/random/?pageNumber=${pageNumber + 1}">&raquo;</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="pagination pagination-lg">
                <li class="disabled"><a href="#">&raquo;</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
</body>
<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
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
