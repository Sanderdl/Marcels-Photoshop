<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
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
                <li><a href="#">Link</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
                    <form class="form-horizontal">
                        <fieldset>
                            <legend>Registration</legend>
                            <div class="form-group">
                                <label for="inputName" class="col-lg-2 control-label">Username</label>
                                <div class="col-lg-10">
                                    <input name="inputUsername" type="text" class="form-control" id="username" placeholder="Username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                                <div class="col-lg-10">
                                    <input name="inputPassword" type="password" class="form-control" id="inputPassword" placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="col-lg-2 control-label">Name</label>
                                <div class="col-lg-10">
                                    <input name ="inputName" type="text" class="form-control" id="inputName" placeholder="Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                                <div class="col-lg-10">
                                    <input name="inputEmail" type="text" class="form-control" id="inputEmail" placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">Account type</label>
                                <div class="col-lg-10">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
                                            Photographer
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                            Client
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="reset" class="btn btn-default">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
        </div>
    </div>
</div>
</body>
</html>
