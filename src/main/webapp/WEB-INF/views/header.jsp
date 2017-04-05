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
            <a class="navbar-brand" href="/gallery/random/"><spring:message code="screen.home" /></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/createalbum/page/"><spring:message code="command.createalbum" /> <span class="sr-only">(current)</span></a></li>
                <li><a href="/registerproduct/page/"><spring:message code="command.upload" /></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.User}">
                        <li><a href="#"><c:out value="${sessionScope.User.userName}"/></a></li>
                        <li><a href="<c:url value="/login/logout/" />" ><spring:message code="command.logout"  /></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="active"><a href= "/registration/page/"><spring:message code="command.register" /></a> </li>
                        <li><a href="/login/page/"><spring:message code="command.login" /></a></li>
                    </c:otherwise>
                </c:choose>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="material-icons" style="font-size:17px;color:white;vertical-align:bottom;">language</i><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="?locale=en">English</a></li>
                        <li><a href="?locale=nl">Nederlands</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
