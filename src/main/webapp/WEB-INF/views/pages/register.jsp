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
</head>
<body>
<h1>
    <spring:message code="label.form.register"/>
</h1>
<form:form modelAttribute="user" method="POST" enctype="utf8">
    <br>
    <table>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.login"/>
                </label>
            </td>
            <td><form:input path="login" value=""/> <form:errors path="login" element="div"/></td>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.password"/>
                </label>
            </td>
            <td><form:password path="password" value=""/> <form:errors path="password" element="div"/></td>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.firstName"/>
                </label>
            </td>
            <td><form:input path="firstName" value=""/> <form:errors path="firstName" element="div"/></td>

        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.lastName"/>
                </label>
            </td>
            <td><form:input path="lastName" value=""/> <form:errors path="lastName" element="div"/></td>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.pzbs.wzbs"/>
                </label>
            </td>
            <td><form:select path="wzbs.shortName" items="${wzbsList}"/> <form:errors path="wzbs" element="div"/></td>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.pzbs.pzbs_id"/>
                </label>
            </td>
            <td><form:input path="pzbsId" value=""/> <form:errors path="pzbsId" element="div"/></td>
        </tr>
    </table>
    <button type="submit">
        <spring:message code="label.form.submit"/>
    </button>
</form:form>

</body>
</html>