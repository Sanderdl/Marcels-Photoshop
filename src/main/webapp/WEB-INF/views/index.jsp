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
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
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
                        <li><a href="/login/page/">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
    <section id="photos" class="justified-gallery shadow-m">
        <c:if test="${not empty lists}">
            <c:forEach var="listValue" items="${lists}">
                <a href="/gallery/image?id=${listValue.key}">
                    <img src="/gallery/image?id=${listValue.key}">
                </a>
                <%--<a href="/gallery/random/">--%>
                    <%--<img src="data:img/png;base64, ${listValue}" alt="No image">--%>
                <%--</a>--%>
            </c:forEach>

        </c:if>
    </section>
    <div id=pageNavigation>
        <c:if test="${pageNumber > 1}">
            <a href="/gallery/random/?pageNumber=${pageNumber - 1}">Previous</a>
        </c:if>
    <a href="/gallery/random/?pageNumber=${pageNumber + 1}">Next</a>
    </div>
</body>
<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.justifiedGallery.min.js"/>"></script>
<script>
    $(function () {
        $("#photos").justifiedGallery({
            rowHeight: 130,
            maxRowHeight: -1,
            randomize: true,
            margins: 6,
            captions: true
        });
    });
</script>
</html>
