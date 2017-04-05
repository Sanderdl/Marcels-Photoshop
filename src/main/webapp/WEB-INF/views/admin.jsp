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
        <div class="col-lg-8 col-md-offset-3">

                <fieldset>

                    <legend>Admin console</legend>
                    <form:form class="form-horizontal" action="/admin/add/" commandName="Extras"
                               enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <div class="form-group">
                                <label for="addExtra" class="col-lg-2 control-label"><spring:message code="admin.add"/></label>
                                <div class="col-lg-10">
                                    <div class="input-group">
                                    <form:input path="extraName" name="addExtra" type="text" class="form-control"
                                                id="addExtra" placeholder=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPrijs" class="col-lg-2 control-label"><spring:message
                                        code="product.price"/></label>
                                <div class="col-lg-9 col-lg-offset-1">
                                    <div class="input-group">
                                        <span class="input-group-addon">€</span>
                                        <form:input path="price" value="0.00" min="0"
                                                    data-number-stepfactor="100" class="form-control currency" id="inputPrijs"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary"><spring:message code="admin.add"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    </form:form>
                </fieldset>

            <fieldset>
                <h4><spring:message code="admin.allproducts"/></h4>
                <form:form class="form-horizontal" action="/admin/delete/" commandName="Extras"
                           enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <c:if test="${not empty availableProducts}">

                                <c:forEach var="product" items="${availableProducts}">

                                    <div class="table-responsive">
                                        <table class="table">
                                            <td>
                                                <div class="checkbox">
                                                    <label>
                                                        <form:checkbox path="extras" value="${product.id}"/>
                                                        <spring:message code="${product.name}"/>
                                                    </label>
                                                </div>
                                            </td>
                                            <td>€<input id="prod${product.id}" value="${product.price}" min="0"></td>
                                            <td><input id="avail${product.id}" value="${product.available}" min="0"></td>
                                            <td><a href="" onclick="updateLink(this, ${product.id})" class="btn btn-primary" role="button">Edit</a></td>
                                        </table>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary"><spring:message code="admin.delete"/></button>
                        </div>
                    </div>
                </form:form>
            </fieldset>


        </div>
    </div>
    <script>
        function updateLink(link, id) {
            var val = document.getElementById("prod" + id).value;
            var avail = document.getElementById("avail" + id).value;
            link.href = "/admin/edit/?id=" + id + "&price=" + val + "&avail="+avail;
        }
    </script>
</div>
</body>
</html>
