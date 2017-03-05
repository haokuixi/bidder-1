<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
    .etc-login-form p {
        margin-bottom: 5px;
    }
    .login-form-1 {
        max-width: 300px;
        border-radius: 5px;
        display: inline-block;
        alignment: right;
    }
    .main-login-form {
        position: relative;
    }
    .login-form-1 .form-group {
        margin-bottom: 0;
        padding-right: 20px;
        position: relative;
    }
    .login-group {
        background: #ffffff;
        color: #999999;
        border-radius: 8px;
        padding: 10px 20px;
    }
    .login-form-1 .login-button {
        position: absolute;
        right: -55px;
        top: 40%;
        background: #ffffff;
        color: #999999;
        padding: 11px 0;
        width: 80px;
        height: 80px;
        margin-top: -25px;
        border: 5px solid #efefef;
        border-radius: 50%;
        transition: all ease-in-out 500ms;
    }
    .login-form-1 .login-button:hover {
        color: #555555;
        transform: rotate(450deg);
    }
    .logo {
        padding: 15px 0;
        font-size: 25px;
        color: #aaaaaa;
        font-weight: bold;
    }

</style>

<div class="text-center" style="padding:50px 0">
    <div class="logo"><spring:message code="label.form.toLog"/></div>
    <div class="login-form-1">
        <form:form id="loginForm" action="/loginPage" method="post" commandName="user" class="text-left">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                    <div class="form-group">
                        <label style="display: table-cell">
                            <spring:message code="label.form.login"/>
                        </label>
                        <form:input id="login" name="login" path="login" class="form-control"/>
                        <form:errors path="login" element="div" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label style="display: table-cell">
                            <spring:message code="label.user.password"/>
                        </label>
                        <form:password id="password" name="password" path="password" class="form-control"/>
                        <form:errors path="password" element="div" class="form-control"/>
                    </div>

                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="<spring:message code="label.form.toLog"/>" class="login-button"/>
            </div>

        </form:form>
    </div>
</div>

