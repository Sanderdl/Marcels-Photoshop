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
            <a class="navbar-brand" href="/gallery/random/"><spring:message code="screen.home"/></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/registerproduct/page/"><spring:message code="command.upload"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.User}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false">
                                <c:out value="${sessionScope.User.userName}"/>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/shared/page/?id=${sessionScope.User.id}"><spring:message
                                        code="nav.shared"/></a></li>
                                <li><a href="<c:url value="/login/logout/" />"><spring:message
                                        code="command.logout"/></a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/registration/page/"><spring:message code="command.register"/></a></li>
                        <li><a href="/login/page/"><spring:message code="command.login"/></a></li>
                    </c:otherwise>
                </c:choose>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i
                            class="material-icons"
                            style="font-size:17px;color:white;vertical-align:bottom;">language</i><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/login/language/?lang=en"/>">English</a></li>
                        <li><a href="<c:url value="/login/language/?lang=nl"/>">Nederlands</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="navbar navbar-default navbar-fixed-bottom">
    <span class="navbar-text" style="margin-left:100px">
      Copyright &#169; 2017 Fred Inc. All rights reserved.
    </span>
    <div style="float:right;margin-right:100px;padding-top:13px;text-decoration:underline">
        <span>
            <a style="color:white;" href="<c:url value="/login/language/?lang=nl"/>">Nederlands</a>
        </span>
        <span>
            <a style="color:white;" href="<c:url value="/login/language/?lang=en"/>">English</a>
        </span>
    </div>
</div>


