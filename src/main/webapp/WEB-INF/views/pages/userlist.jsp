<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h2>Uski</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Login</th>
            <th>Imie</th>
            <th>Nazwisko</th>
            <th>PZBS ID</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.pzbsId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>