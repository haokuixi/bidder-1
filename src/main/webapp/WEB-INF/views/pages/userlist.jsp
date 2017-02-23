<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h2>Uski</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.user.login"/></th>
            <th><spring:message code="label.user.firstName"/></th>
            <th><spring:message code="label.user.lastName"/></th>
            <th><spring:message code="label.user.pzbs.pzbs_id"/></th>
            <th><spring:message code="label.user.pzbs.wzbs"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.pzbsId}</td>
                <td>${user.wzbs.shortName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>