<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title><spring:message code="label.form.register"></spring:message></title>
</head>
<body>
<h1>
    <spring:message code="label.form.register"></spring:message>
</h1>
<form:form modelAttribute="user" method="POST" enctype="utf8">
    <br>
    <table>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.login"></spring:message>
                </label>
            </td>
            <td><form:input path="login" value=""/></td>
            <form:errors path="login" element="div"/>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.firstName"></spring:message>
                </label>
            </td>
            <td><form:input path="name" value=""/></td>
            <form:errors path="name" element="div"/>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.lastName"></spring:message>
                </label>
            </td>
            <td><form:input path="surname" value=""/></td>
            <form:errors path="surname" element="div"/>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.pzbs.wzbs"></spring:message>
                </label>
            </td>
            <td><form:select path="wzbs.shortName" items="${wzbsList}" /></td>
            <form:errors path="wzbs" element="div"/>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.pzbs.pzbs_id"></spring:message>
                </label>
            </td>
            <td><form:input path="pzbsId" value=""/></td>
            <form:errors path="pzbsId" element="div"/>
        </tr>
    </table>
    <button type="submit">
        <spring:message code="label.form.submit"></spring:message>
    </button>
</form:form>

</body>
</html>