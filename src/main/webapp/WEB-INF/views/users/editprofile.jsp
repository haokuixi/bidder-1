<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form modelAttribute="user" method="POST" enctype="utf8">
    <br>
    <table>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.login"/>
                </label>
            </td>
            <td><form:label path="login">${user.login}</form:label></td>
        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.firstName"/>
                </label>
            </td>
            <td><form:input path="firstName" value="${user.firstName}"/> <form:errors path="firstName" element="div"/></td>

        </tr>
        <tr>
            <td>
                <label>
                    <spring:message code="label.user.lastName"/>
                </label>
            </td>
            <td><form:input path="lastName" value="${user.lastName}"/> <form:errors path="lastName" element="div"/></td>
        </tr>


        <c:choose>
            <c:when test="${user.wzbs.shortName.equals('NZ')}">
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
            </c:when>
            <c:otherwise>
                <tr>
                    <td>
                        <label>
                            <spring:message code="label.user.pzbs.wzbs"/>
                        </label>
                    </td>
                    <td><form:label path="wzbs">${user.wzbs.shortName}</form:label> </td>
                </tr>
                <tr>
                    <td>
                        <label>
                            <spring:message code="label.user.pzbs.pzbs_id"/>
                        </label>
                    </td>
                    <td><form:label path="pzbsId">${user.pzbsId}</form:label> </td>
                </tr>
            </c:otherwise>
        </c:choose>

    </table>
    <button type="submit">
        <spring:message code="label.form.submit"/>
    </button>
</form:form>