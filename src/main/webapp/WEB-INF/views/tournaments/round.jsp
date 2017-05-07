<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="well">
       <div style="alignment: center"><spring:message code="label.tournament.movements.round"/> ${round.roundNumber} </div>
    </div>
</div>

<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name.equals(tour.judge.name)}">
        <div class="container">
            <div class="well">
                <table class="table">
                    <tr>
                        <td/>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">
                            <th><spring:message code="label.tournament.movements.table"/> ${table.number} </th>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td/>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">
                            <c:choose>
                                <c:when test="${table.movement.ns.equals(' stationary ')}">
                                    <td>NS: <spring:message code="label.tournament.movements.stationary"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td>NS: ${table.movement.ns}</td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td/>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">
                            <c:choose>
                                <c:when test="${table.movement.ew=='stationary'}">
                                    <td>EW: <spring:message code="label.tournament.movements.stationary"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td>EW: ${table.movement.ew}</td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>

                    <tr>
                        <td/>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">

                            <td class="round">
                                    ${table.rounds.round.get(round.roundNumber).boards.from}-${table.rounds.round.get(round.roundNumber).boards.to}
                            </td>

                        </c:forEach>
                    </tr>
                </table>
            </div>
        </div>
    </c:when>
</c:choose>