<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Product Screen</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<spring:message code="product.title" var="lb_picturetitle"/>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="center-block" style="width:500px">
            <form:form class="form-horizontal" action="/registerproduct/submit/" commandName="productregistration"
                       enctype="multipart/form-data">
                <fieldset>
                    <legend>Upload a photo</legend>
                    <div class="form-group">
                        <label for="inputPicturetitle" style="text-align: left"
                               class="col-lg-2 control-label">Title</label>
                        <div class="col-lg-9 col-lg-offset-1">
                            <form:input path="title" name="inputPicturetitle" type="text" class="form-control"
                                        id="inputPicturetitle" placeholder="${lb_picturetitle}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputPrijs" style="text-align: left" class="col-lg-2 control-label"><spring:message
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
                        <label for="inputPrijs" style="text-align: left" class="col-lg-2 control-label"> <spring:message
                                code="product.select"/></label>
                        <div class="col-lg-9 col-lg-offset-1">
                            <div class="input-group">
                        <span class="input-group-btn">
                            <span class="btn btn-primary btn-file">
                                <spring:message code="command.browse"/>&hellip; <form:input path="picture" name="file"
                                                                                            type="file"/>
                            </span>
                        </span>
                                <input type="text" class="form-control" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="album" style="text-align: left" class="col-lg-2 control-label"><spring:message
                                code="product.album"/></label>
                        <div class="col-lg-9 col-lg-offset-1">
                            <div class="input-group">
                                <form:select class="form-control" path="album" id="album">
                                    <form:option value="-1" label="--- Select album ---"/>
                                    <form:options items="${albums}"/>
                                </form:select>
                                <spring:message code="product.createalbum"/>
                                <a data-toggle="modal" href="#" data-target="#myModal">
                                    <spring:message code="product.clickalbum"/></a>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="public" style="text-align: left" class="col-lg-2"><spring:message
                                code="product.shouldpublic"/></label>
                        <div class="col-lg-9 col-lg-offset-1">
                            <div class="check-box">
                                <form:checkbox path="isPublic" name="public"/>
                                <spring:message code="product.public"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="product" style="text-align: left" class="col-lg-2"><spring:message
                                code="product.whichproduct"/></label>
                        <c:if test="${not empty availableProducts}">
                            <div class="col-lg-9 col-lg-offset-1">
                                <c:forEach var="product" items="${availableProducts}">
                                    <div class="check-box">
                                        <form:checkbox path="products" value="${product.id}" name="product"/>
                                        <spring:message code="${product.name}"/>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <div class="center-block" style="width:500px">
                            <div class="col-lg-9 col-lg-offset-3">
                                <button type="reset" class="btn btn-default"><spring:message
                                        code="command.cancel"/></button>
                                <button type="submit" class="btn btn-primary" class="col-lg-2 col-lg-offset-1">
                                    <spring:message
                                            code="command.upload"/></button>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger">
                            <c:out value="${message}"/>
                        </div>
                    </c:if>


                </fieldset>
            </form:form>

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title"><spring:message code="command.createalbum"/></h4>
                        </div>
                        <form:form class="form-horizontal" action="/registerproduct/modal/" commandName="album"
                                   enctype="multipart/form-data">
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label for="albumName" style="text-align: left;left:10px;"
                                               class="col-lg-2 control-label"><spring:message
                                                code="account.name"/></label>
                                        <div class="col-lg-10">
                                            <form:input path="name" name="inputName" type="text" class="form-control"
                                                        id="albumName" placeholder="Name"/>
                                            <span class="hide help-inline">This is required</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="album" style="text-align: left;left:10px;"
                                               class="col-lg-2 control-label"><spring:message
                                                code="album.category"/></label>
                                        <div class="col-lg-10" style="width:400px;">
                                            <div class="input-group">
                                                <form:select class="form-control" path="categories">
                                                    <form:options items="${categories}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="album" style="text-align: left;left:10px;"
                                               class="col-lg-2 control-label"><spring:message
                                                code="product.select"/></label>
                                        <div class="col-lg-10">
                                            <div class="input-group">
                                        <span class="input-group-btn">
                                             <span class="btn btn-primary btn-file">
                                                <spring:message code="command.browse"/>&hellip; <form:input
                                                     path="thumbnail" name="file"
                                                     type="file"/>
                                             </span>
                                        </span>
                                                <input type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" id="submitModal" class="btn btn-primary"><spring:message
                                        code="command.upload"/></button>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                                        code="command.cancel"/></button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</body>
</html>
<style>
    .btn-file {
        position: relative;
        overflow: hidden;
    }

    .btn-file input[type=file] {
        position: absolute;
        top: 0;
        right: 0;
        min-width: 100%;
        min-height: 100%;
        font-size: 100px;
        text-align: right;
        filter: alpha(opacity=0);
        opacity: 0;
        background: red;
        cursor: inherit;
        display: block;
    }

    .btn-file input[type=file] {
        position: absolute;
        top: 0;
        right: 0;
        min-width: 100%;
        min-height: 100%;
        font-size: 100px;
        text-align: right;
        filter: alpha(opacity=0);
        opacity: 0;
        background: red;
        cursor: inherit;
        display: block;
    }

    input[readonly] {
        background-color: white !important;
        cursor: text !important;
    }
</style>
<script>
    $(document).on('change', '.btn-file :file', function () {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    $(document).ready(function () {
        $('.btn-file :file').on('fileselect', function (event, numFiles, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = numFiles > 1 ? numFiles + ' files selected' : label;

            if (input.length) {
                input.val(log);
            } else {
                if (log) alert(log);
            }

        });
    });
</script>
