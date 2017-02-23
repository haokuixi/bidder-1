<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h2><spring:message code="label.tournament.tournaments"/></h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.tournament.title"/></th>
            <th><spring:message code="label.tournament.judge"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="tour" items="${tourlist}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/tournaments/tour?tourId=${tour.id}">${tour.title}</a></td>
                <td>${tour.judge.surname}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>