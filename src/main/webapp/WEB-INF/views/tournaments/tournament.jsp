<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1><spring:message code="label.tournament.details"/></h1>
<br/>
<h2><spring:message code="label.tournament.title"/></h2>
<h3>${tour.title}</h3>
<h2><spring:message code="label.tournament.judge"/></h2>
<h3>${tour.judge.surname}</h3>
<h2><spring:message code="label.tournament.players"/></h2>

<c:forEach var="pair" items="${tour.pairs}">

    <tr>
        <td><a href="${pageContext.request.contextPath}/users/user?userId=${pair.playerOne.id}">${pair.playerOne.surname}</a>
        </td>
        <td><a href="${pageContext.request.contextPath}/users/user?userId=${pair.playerTwo.id}">${pair.playerTwo.surname}</a>
        </td>
    </tr>
</c:forEach>