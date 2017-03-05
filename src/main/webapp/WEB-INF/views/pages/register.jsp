<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title><spring:message code="label.form.register"/></title>

    <style>
        div.register {
            width:400px;
            margin: auto;
        }

    </style>
</head>
<body>

<div class="container register" align="center">
    <form:form modelAttribute="user" method="POST" enctype="utf8">
        <div class="row main">
            <div class="panel-heading">
                <div class="panel-title text-center">
                    <h1 class="title"><spring:message code="label.form.register"/></h1>
                    <hr/>
                </div>
            </div>
            <div class="main-login main-center">
                <div class="form-group">
                    <label for="login" class="cols-sm-2 control-label">
                        <spring:message code="label.user.login"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user fa" aria-hidden="true"></i>
                            </span>
                            <form:input path="login" value="" class="form-control"/>
                            <form:errors path="login" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="firstName" class="cols-sm-2 control-label">
                        <spring:message code="label.user.firstName"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa-pencil-square-o fa" aria-hidden="true"></i>
                            </span>
                            <form:input path="firstName" value="" class="form-control"/>
                            <form:errors path="firstName" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastName" class="cols-sm-2 control-label">
                        <spring:message code="label.user.lastName"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class=" fa-pencil-square-o fa" aria-hidden="true"></i>
                            </span>
                            <form:input path="lastName" value="" class="form-control"/>
                            <form:errors path="lastName" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">
                        <spring:message code="label.user.password"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-lock fa-lg" aria-hidden="true"></i>
                                </span>
                            <form:password path="password" value="" class="form-control"/>
                            <form:errors path="password" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="repeatedPassword" class="cols-sm-2 control-label">
                        <spring:message code="label.user.repeatpassword"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-lock fa-lg" aria-hidden="true"></i>
                                </span>
                            <form:password path="repeatedPassword" value="" class="form-control"/>
                            <form:errors path="repeatedPassword" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="wzbs.shortName" class="cols-sm-2 control-label">
                        <spring:message code="label.user.pzbs.wzbs"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-users fa-lg" aria-hidden="true"></i>
                                </span>
                            <form:select path="wzbs.shortName" items="${wzbsList}" class="form-control"/>
                            <form:errors path="wzbs" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="pzbsId" class="cols-sm-2 control-label">
                        <spring:message code="label.user.pzbs.pzbs_id"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-users fa-lg" aria-hidden="true"></i>
                                </span>
                            <form:input path="pzbsId" value="" class="form-control"/>
                            <form:errors path="pzbsId" element="div" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="judge" class="cols-sm-2 control-label">
                        <spring:message code="label.user.judge"/>
                    </label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-flag-o fa-lg" aria-hidden="true"></i>
                                </span>
                            <form:checkbox path="judge" value="" class="form-control"/>
                            <form:errors path="judge" element="div" class="form-control"/>
                        </div>
                    </div>
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