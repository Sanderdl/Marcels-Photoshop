<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Screen</title>
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
<form action="/gallery/random//">
    <div id="loginScreen">
        <form class="form-horizontal">
            <fieldset>
                <legend>Legend</legend>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Checkbox
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
</form>
</body>
</html>
