<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h2>Turnieje</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Tytuł</th>
            <th>Sędzia</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="tour" items="${tourlist}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/tournaments/tour">${tour.title}</a></td>
                <td>${tour.judge.surname}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>