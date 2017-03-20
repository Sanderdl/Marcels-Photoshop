<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Product Screen</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
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
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login/page/">Login</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-5 col-md-offset-3">
            <form:form class="form-horizontal" action="/registerproduct/submit/" commandName="productregistration"
                       enctype="multipart/form-data">
                <fieldset>
                    <legend>Upload a photo</legend>
                    <div class="form-group">
                        <label for="inputPicturetitle" class="col-lg-2 control-label">Title</label>
                        <div class="col-lg-10">
                            <form:input path="title" name="inputPicturetitle" type="text" class="form-control"
                                        id="inputPicturetitle" placeholder="Picture Title"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputPrijs" class="col-lg-2 control-label">Price</label>
                        <div class="col-lg-10">
                            <div class="input-group">
                                <span class="input-group-addon">€</span>
                                <form:input path="price" value="0.00" min="0"
                                            data-number-stepfactor="100" class="form-control currency" id="inputPrijs"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <h4>Choose a picture</h4>
                            <div class="input-group">
                        <span class="input-group-btn">
                            <span class="btn btn-primary btn-file">
                                Browse&hellip; <form:input path="picture" name="file" type="file"/>
                            </span>
                        </span>
                                <input type="text" class="form-control" readonly>
                            </div>

                            <h4>Choose an album</h4>
                            <form:select class="form-control" path="album">
                                <form:option value="-1" label="--- Select ---"/>
                                <form:options items="${albums}"/>
                            </form:select>

                            <h4>Should the picture be private?</h4>
                            <form:checkbox path="isPublic"/> Private

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <h4>On what product do you want your picture to be placed?</h4>
                            <c:if test="${not empty availableProducts}">

                                <c:forEach var="product" items="${availableProducts}">
                                    <div class="checkbox">
                                        <label>
                                            <form:checkbox path="products" value="${product.id}"/>
                                            <c:out value="${product.name}"/>
                                        </label>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-4">
                            <button type="reset" class="btn btn-default">Cancel</button>
                            <button type="submit" class="btn btn-primary">Upload</button>
                        </div>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger">
                            <c:out value="${message}"/>
                        </div>
                    </c:if>
                </fieldset>
            </form:form>

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
