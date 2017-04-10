<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="404.title"/></title>
    <link rel="stylesheet" type="text/css" href="https://s.imgur.com/min/404.css?1393899213">
    <style type="text/css">
        :root .sponsored,
        :root .promoted,
        :root .advertisement,
        :root #wrapper-pop_sky,
        :root #content > #right > .dose > .dosesingle,
        :root #content > #center > .dose > .dosesingle,
        :root .panel-ad {
            display: none !important;
        }</style>
    <style>.pkt_added {
        text-decoration: none !important;
    }
    </style>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
</head>
<style>
    *::selection {
        background: #B1D7FE;
        color: #000000;
    }
    *::-moz-selection {
        background: #B1D7FE;
        color: #000000;
    }
    *::-webkit-selection {
        background: #B1D7FE;
        color: #000000;
    }
</style>
<body style="">
<div id="hallway" class="center-block" style="width: 100%;height: 350px;background: url(//s.imgur.com/images/404/hallbg.png) center #871908;outline: #2C2F34 solid 1px;border-bottom: 1px solid #121211">
    <div class="container" style="width: 1040px;margin: 10px auto 0;">
        <div id="cat1" class="painting">
            <img src="//s.imgur.com/images/404/cat1weyes.png">
            <div class="eye-container">
                <div class="eye left" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 3.9277240901003445px; top: 2.5328037466523656px;"></div>
                </div>
                <div class="eye right" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 3.9212517090613694px; top: 2.555690444788074px;"></div>
                </div>
            </div>
        </div>
        <div id="cat2" class="painting">
            <img src="//s.imgur.com/images/404/cat2weyes.png">
            <div class="eye-container">
                <div class="eye" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 3.6194841780665765px; top: 3.1735718968141766px;"></div>
                </div>
            </div>
        </div>
        <div id="giraffe" class="painting">
            <img src="//s.imgur.com/images/404/giraffeweyes.png">
            <div class="eye-container">
                <div class="eye left" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 0.7959346779481402px; top: 3.5969429232856607px;"></div>
                </div>
                <div class="eye right" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 0.25873525883377435px; top: 2.9838684369219886px;"></div>
                </div>
            </div>
            <img class="monocle" src="//s.imgur.com/images/404/monocle.png">
        </div>
        <div id="cat3" class="painting">
            <img src="//s.imgur.com/images/404/cat3weyes.png">
            <div class="eye-container">
                <div class="eye left" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 0.08315610818407637px; top: 2.570709641068016px;"></div>
                </div>
                <div class="eye right" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 0.07241281039401293px; top: 2.5332988153623566px;"></div>
                </div>
            </div>
        </div>
        <div id="cat4" class="painting">
            <img src="//s.imgur.com/images/404/cat4weyes.png">
            <div class="eye-container">
                <div class="eye left" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 0.031129889648661146px; top: 2.351497494393782px;"></div>
                </div>
                <div class="eye right" style="position: relative;">
                    <div class="pupil"
                         style="position: absolute; left: 0.029461844319608854px; top: 2.3420224802666088px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="center-block" style="width:100%;">
    <div class="center-block" style="width:35%">
        <div class="well well-lg" style="width:35%;position:absolute;top:50%;">
            <h3><spring:message code="404.wrongturn"/></h3>
            <h4><spring:message code="404.message"/></h4>
            <h4><spring:message code="404.redirectmessage"/> <a href="/gallery/random/"><spring:message
                    code="404.visitgallery"/></a></h4>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://s.imgur.com/min/404.js?1393899213"></script>
<script type="text/javascript">
    var e404 = E404.getInstance();
    e404.generalInit();
</script>


</body>
</html>