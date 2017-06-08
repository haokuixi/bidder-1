<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${visible}">
        <div class="container" align="center">
            <div class="container col-md-4 col-lg-4">
                <table class="table">
                    <tr>
                        <td/>
                        <td>
                            <table>
                                <tr>
                                    <th/>
                                    <th style="font-size:large">
                                        <spring:message code="label.deal.cards.north"/>
                                    </th>
                                    <th/>
                                </tr>
                                <tr>
                                    <td>♠</td>
                                    <td>
                                        <c:forEach var="spade" items="${deal.dealModel.north.spades}">
                                            ${spade.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♥</td>
                                    <td>
                                        <c:forEach var="heart" items="${deal.dealModel.north.hearts}">
                                            ${heart.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♦</td>
                                    <td>
                                        <c:forEach var="diamond" items="${deal.dealModel.north.diamonds}">
                                            ${diamond.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♣</td>
                                    <td>
                                        <c:forEach var="club" items="${deal.dealModel.north.clubs}">
                                            ${club.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td/>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <th/>
                                    <th style="font-size:large">
                                        <spring:message code="label.deal.cards.west"/>
                                    </th>
                                    <th/>
                                </tr>
                                <tr>
                                    <td>♠</td>
                                    <td>
                                        <c:forEach var="spade" items="${deal.dealModel.west.spades}">
                                            ${spade.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♥</td>
                                    <td>
                                        <c:forEach var="heart" items="${deal.dealModel.west.hearts}">
                                            ${heart.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♦</td>
                                    <td>
                                        <c:forEach var="diamond" items="${deal.dealModel.west.diamonds}">
                                            ${diamond.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♣</td>
                                    <td>
                                        <c:forEach var="club" items="${deal.dealModel.west.clubs}">
                                            ${club.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td/>
                        <td>
                            <table>
                                <tr>
                                    <th/>
                                    <th style="font-size:large">
                                        <spring:message code="label.deal.cards.east"/>
                                    </th>
                                    <th/>
                                </tr>
                                <tr>
                                    <td>♠</td>
                                    <td>
                                        <c:forEach var="spade" items="${deal.dealModel.east.spades}">
                                            ${spade.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♥</td>
                                    <td>
                                        <c:forEach var="heart" items="${deal.dealModel.east.hearts}">
                                            ${heart.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♦</td>
                                    <td>
                                        <c:forEach var="diamond" items="${deal.dealModel.east.diamonds}">
                                            ${diamond.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♣</td>
                                    <td>
                                        <c:forEach var="club" items="${deal.dealModel.east.clubs}">
                                            ${club.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td/>
                        <td>
                            <table>
                                <tr>
                                    <th/>
                                    <th style="font-size:large">
                                        <spring:message code="label.deal.cards.south"/>
                                    </th>
                                    <th/>
                                </tr>
                                <tr>
                                    <td>♠</td>
                                    <td>
                                        <c:forEach var="spade" items="${deal.dealModel.south.spades}">
                                            ${spade.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♥</td>
                                    <td>
                                        <c:forEach var="heart" items="${deal.dealModel.south.hearts}">
                                            ${heart.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♦</td>
                                    <td>
                                        <c:forEach var="diamond" items="${deal.dealModel.south.diamonds}">
                                            ${diamond.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td>♣</td>
                                    <td>
                                        <c:forEach var="club" items="${deal.dealModel.south.clubs}">
                                            ${club.name}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td/>
                    </tr>
                </table>
            </div>
        </div>
        </div>


        <div class="container" align="center">
            <div class="container">
                <div class="well">
                    <c:choose>
                        <c:when test="${buttonVisible}">
                            <a class="btn btn-primary btn-block login-button button"
                               href="${pageContext.request.contextPath}/deals/enterresult?dealId=${deal.hashedId}">
                                <spring:message code="label.deal.enterresult"/>
                            </a>
                        </c:when>
                    </c:choose>
                    <table class="table">
                        <thead class="header">
                        <tr>
                            <th><spring:message code="label.deal.nsPair"/></th>
                            <th><spring:message code="label.deal.ewPair"/></th>
                            <th><spring:message code="label.deal.contract"/></th>
                            <th><spring:message code="label.deal.declarer"/></th>
                            <th><spring:message code="label.deal.result"/></th>
                            <th><spring:message code="label.deal.lead"/></th>
                            <th><spring:message code="label.deal.points"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="res" items="${results}">
                            <tr>
                                <td>${res.pairNS.playerOne.surname}, ${res.pairNS.playerTwo.surname}</td>
                                <td>${res.pairEW.playerOne.surname}, ${res.pairEW.playerTwo.surname}</td>
                                <td>
                                        ${fn:substring(res.contract, 0, 1)}
                                        ${res.contractColor}
                                    <c:choose>
                                        <c:when test="${res.contractColor!='NT'}">
                                            ${fn:substring(res.contract, 2, -1)}
                                        </c:when>
                                        <c:otherwise>
                                            ${fn:substring(res.contract, 3, -1)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${res.declarerPosition}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${res.result==0}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${res.result}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${res.lead}</td>
                                <td>${res.points}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container" align="center">
            <div class="container">
                <spring:message code="label.deal.nonvisible"/>
            </div>
        </div>
    </c:otherwise>
</c:choose>