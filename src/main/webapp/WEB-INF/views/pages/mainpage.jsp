<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1> MAIN PAGE </h1>

<c:choose>
    <c:when test="${sessionScope.get('loggedUser').getLogin() != null}">
        <h2> heloł ${sessionScope.get('loggedUser').getLogin()}</h2>

        <c:choose>
            <c:when test="${user.role.equals('ROLE_JUDGE')}">
                <h3> jesteś sędziom </h3>
            </c:when>
            <c:otherwise>
                <h3> jesteś graczem </h3>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <h2> heloł gościu </h2>
    </c:otherwise>
</c:choose>

