<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sande
  Date: 02/03/2017
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marcel's Photo Shop</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/justifiedGallery.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<div id ="mainbar" class ="shadow-m wide dbtn">
    <div id ="nav">
        <div id ="logo">
            <img src="<c:url value="/resources/images/mps_logo.png"/>" alt="logo" style="width:150px;height:60px;">
        </div>
        <div id="site-search">
            <input type="text" name="search" placeholder="Zoek foto's, fotografen en meer..." class="wide btn text-box">
        </div>
        <div id ="buttons">

            link
            link
        </div>
    </div>
</div>
<section id="photos" class="justified-gallery shadow-m">
    <c:if test="${not empty lists}">
        <c:forEach var="listValue" items="${lists}">
            <a href="/gallery/image?id=${listValue}">
                <img src="/gallery/image?id=${listValue}">
            </a>
        </c:forEach>

    </c:if>
</section>
</body>
<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.justifiedGallery.min.js"/>"></script>
<script>
    $(function() {
        $("#photos").justifiedGallery({
            rowHeight: 130,
            maxRowHeight: -1,
            lastRow: 'hide',
            margins: 6,
            captions: true
        });
    });
</script>
</html>
