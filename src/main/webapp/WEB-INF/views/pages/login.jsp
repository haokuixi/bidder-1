<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="login-box" style="text-align: center;">

    <h2>Zaloguj</h2>

    <form:form id="loginForm" action="/loginPage" method="post" commandName="user">
        <form:label path="login"><spring:message code="label.form.login"/></form:label>
        <form:input id="login" name="login" path="login"/> <br>

        <form:label path="password"><spring:message code="label.user.password"/></form:label>
        <form:password id="password" name="password" path="password"/> <br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Submit"/>
    </form:form>
</div>
