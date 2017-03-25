<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <script src="http://momentjs.com/downloads/moment-with-locales.js"></script>
    <script src="http://momentjs.com/downloads/moment-timezone-with-data.js"></script>

    <script>
        function addNow() {
            nowDate = moment().tz("Europe/Warsaw").format('YYYY-MM-DD');
            nowTime = moment().tz("Europe/Warsaw").format('HH:mm');
            document.getElementById('registration-date').value = nowDate;
            document.getElementById('registration-time').value = nowTime;
            set = setTimeout(function () {
                addNow();
            }, 1000);
        }

        function stopNow() {
            clearTimeout(set);
        }
    </script>
    <title><spring:message code="label.tournament.edit"/></title>

    <style>
        div.register {
            width: 400px;
            margin: auto;
        }
    </style>
</head>
<body>

<div class="container register" align="center">
    <form:form modelAttribute="tour" method="POST" enctype="utf8">
        <div class="row main">
            <div class="panel-heading">
                <div class="panel-title text-center">
                    <h1 class="title"><spring:message code="label.tournament.edit"/></h1>
                    <hr/>
                </div>
            </div>
            <div class="main-login main-center">
                <div class="form-group">
                    <label for="title" class="cols-sm-2 control-label">
                        <spring:message code="label.tournament.title"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user fa" aria-hidden="true"></i>
                            </span>
                            <form:input path="title" value="${tour.title}" class="form-control"
                                        disabled="${tour.status.name()!='CREATED'}"/>
                            <form:errors path="title" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description" class="cols-sm-2 control-label">
                        <spring:message code="label.tournament.description"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user fa" aria-hidden="true"></i>
                            </span>
                            <form:textarea path="description" value="${tour.description}" rows="3"
                                           class="form-control" disabled="${tour.status.name()!='CREATED'}"/>
                            <form:errors path="description" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="tournamentMode" class="cols-sm-2 control-label">
                        <spring:message code="label.tournament.mode"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-users fa-lg" aria-hidden="true"></i>
                                </span>
                            <form:select path="tournamentMode" items="${modes}" class="form-control"
                                         disabled="${tour.status.name()!='CREATED'}"/>
                            <form:errors path="tournamentMode" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <form:form action="${pageContext.request.contextPath}/tournaments/tour?tourId=${tour.id}"
                               methodParam="tourId" method="post">
                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.name.equals(tour.judge.name)}">
                                <c:choose>
                                    <c:when test="${tour.status.name()=='CREATED'}">
                                        <button type="submit"
                                                class="btn btn-primary btn-lg btn-block login-button"
                                                name="startDate" value="startDate">
                                            <spring:message code="label.tournament.starttournament"/>
                                        </button>
                                    </c:when>
                                    <c:when test="${tour.status.name()=='INPROGRESS'}">
                                        <button type="submit"
                                                class="btn btn-primary btn-lg btn-block login-button"
                                                name="endDate" value="endDate">
                                            <spring:message code="label.tournament.completetournament"/>
                                        </button>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </form:form>
                </div>

                <button type="submit" class="btn btn-primary btn-lg btn-block login-button">
                    <spring:message code="label.form.submit"/>
                </button>
            </div>
        </div>
    </form:form>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
</div>
</body>
</html>