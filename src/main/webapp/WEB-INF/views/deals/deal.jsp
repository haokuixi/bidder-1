<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

visible ${visible}
<h2> heloł ${sessionScope.get("loggedUser").getLogin()}</h2>


<div class="container" align="center">
    <div class="container">
        <div class="well col-md-9 col-lg-9">
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