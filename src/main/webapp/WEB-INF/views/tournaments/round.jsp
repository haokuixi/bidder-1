<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="well">
        <div style="alignment: center"><spring:message
                code="label.tournament.movements.round"/> ${round.roundNumber} </div>
    </div>
</div>

<c:choose>
    <c:when test="${round.status.equals('COMPLETED')}">
        <div class="container">
            <div class="well">
                <table class="table">
                    <thead class="header">
                    <tr class="header-panel">
                        <th><spring:message code="label.round.pairposition"/></th>
                        <th><spring:message code="label.round.pair"/></th>
                        <th><spring:message code="label.round.result"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="result" items="roundResults">
                        <tr>
                            <td>${result.position}</td>
                            <td>${result.pair.playerOne.surname, result.pair.playerTwo.surname}</td>
                            <c:choose>
                                <c:when test="${mode.equals('IMPS')}">
                                    <td>${result.impResult}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${result.maxResult}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name.equals(tour.judge.name)
    && round.status.equals('INPROGRESS')}">
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
                        <td>
                            <spring:message code="label.tournament.round.playingpairs"/>
                        </td>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">
                            <td>NS: ${tour.getPairNames(table.number, 'NS')}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td/>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">
                            <td>EW: ${tour.getPairNames(table.number, 'EW')}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="label.tournament.movements.boardsontablesnumbers"/>
                        </td>
                        <c:forEach var="table" items="${tour.movement.movementTables.table}">
                            <td class="round">
                                    ${table.rounds.round.get(round.roundNumber).boards.from}-${table.rounds.round.get(round.roundNumber).boards.to}
                            </td>
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
                </table>
            </div>
        </div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${tour.containsPlayer(pageContext.request.userPrincipal.name)
        && round.status.equals('INPROGRESS')}">
        <div class="container">
            <div class="well">
                <c:forEach var="i"
                           begin="${tour.movement.movementTables.table.get(tour.currentPair.currentTable-1).rounds.round.get(tour.currentRound.roundNumber).boards.from}"
                           end="${tour.movement.movementTables.table.get(tour.currentPair.currentTable-1).rounds.round.get(tour.currentRound.roundNumber).boards.to}">
                    <div class="container">
                        <a href=${pageContext.request.contextPath}/deals/deal?dealId=${tour.getDealByNumber(i)}>
                            <spring:message code="label.deal.no"/> ${i}
                        </a>
                        <br/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:when>
</c:choose>