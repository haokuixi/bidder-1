<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:choose>
    <c:when test="${sessionScope.get('loggedUser').getLogin() != null}">
        <h2><spring:message code="label.mainpage.welcome"/> ${sessionScope.get('loggedUser').getLogin()}</h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="label.mainpage.welcome"/> <spring:message code="label.mainpage.guest"/></h2>
    </c:otherwise>
</c:choose>

