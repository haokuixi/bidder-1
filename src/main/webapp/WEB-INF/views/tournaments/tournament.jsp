<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    a.link {
        color: rgba(34, 85, 99, 0.86);
        font-weight: bold;
    }

    td.link {
        color: rgba(34, 85, 99, 0.86);
        font-weight: bold;
    }

    td.text {
        color: rgba(99, 98, 92, 0.86);
    }

    h3.title {
        color: rgba(41, 60, 99, 0.86);
        font-weight: bold;
        font-size: large;
    }
</style>

<div class="container">
    <div class="well">
        <div class="panel-heading" align="center">
            <div class="panel panel-info">
                <a href="${pageContext.request.contextPath}/tournaments/tour/edit?tourId=${tour.id}">
                    <button class="btn btn-primary btn-lg btn-block login-button">
                        <spring:message code="label.tournament.edit"/>
                    </button>
                </a>
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="label.tournament.details"/></h3>
                    <h3 class="title">${tour.title}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div>
                            <table class="table table-user-information">
                                <tbody>
                                <tr align="center">
                                    <td class="text"><spring:message code="label.tournament.judge"/></td>
                                    <td>
                                        <a class="link"
                                           href="${pageContext.request.contextPath}/users/user?userId=${tour.judge.id}">
                                            ${tour.judge.name} ${tour.judge.surname}
                                        </a>
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td class="text">
                                        <spring:message code="label.tournament.mode"/>
                                    </td>
                                    <td class="text">
                                        <c:choose>
                                            <c:when test="${tour.tournamentMode.name=='imps'}">
                                                <spring:message code="label.tournament.mode.imps"/>
                                            </c:when>
                                            <c:otherwise>
                                                <spring:message code="label.tournament.mode.percent"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td class="text">
                                        <spring:message code="label.tournament.description"/>
                                    </td>
                                    <td class="text">
                                        ${tour.description}
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td class="text">
                                        <spring:message code="label.tournament.status"/>
                                    </td>
                                    <td class="text">
                                        <c:choose>
                                            <c:when test="${tour.status.name=='CREATED'}">
                                                <spring:message code="label.tournament.created"/>
                                            </c:when>
                                            <c:when test="${tour.status.name=='INPROGRESS'}">
                                                <spring:message code="label.tournament.inprogress"/>
                                            </c:when>
                                            <c:when test="${tour.status.name=='COMPLETED'}">
                                                <spring:message code="label.tournament.completed"/>
                                            </c:when>
                                            <c:when test="${tour.status.name=='SUSPENDED'}">
                                                <spring:message code="label.tournament.suspended"/>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td class="text">
                                        <spring:message code="label.tournament.startdate"/>
                                    </td>
                                    <td class="text">
                                        <c:choose>
                                            <c:when test="${tour.startDate!=null}">
                                                ${tour.startDate}
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td class="text">
                                        <spring:message code="label.tournament.enddate"/>
                                    </td>
                                    <td class="text">
                                        <c:choose>
                                            <c:when test="${tour.endDate!=null}">
                                                ${tour.endDate}
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                            <form:form action="${pageContext.request.contextPath}/tournaments/tour?tourId=${tour.id}"
                                       methodParam="tourId" method="post">
                                <c:choose>
                                    <c:when test="${pageContext.request.userPrincipal.name.equals(tour.judge.name)}">
                                        <c:choose>
                                            <c:when test="${tour.status.name=='CREATED'}">
                                                <button type="submit"
                                                        class="btn btn-primary btn-lg btn-block login-button"
                                                        name="startDate" value="startDate">
                                                    <spring:message code="label.tournament.starttournament"/>
                                                </button>
                                            </c:when>
                                            <c:when test="${tour.status.name=='INPROGRESS'}">
                                                <button type="submit"
                                                        class="btn btn-primary btn-lg btn-block login-button"
                                                        name="endDate" value="endDate">
                                                    <spring:message code="label.tournament.completetournament"/>
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                </c:choose>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">

    <div class="well">
        <div class="panel-heading" align="center">
            <h2 class="list"><spring:message code="label.tournament.players"/></h2>
        </div>
        <table class="table">
            <thead class="header">
            <tr class="header-panel">
                <th/>
                <th/>
                <th/>
                <th><spring:message code="label.tournament.result"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pair" items="${tour.pairs}">
                <tr>
                    <td align="center"><span class="fa fa-play"></span></td>
                    <td>
                        <a class="link"
                           href="${pageContext.request.contextPath}/users/user?userId=${pair.playerOne.id}">
                                ${pair.playerOne.name} ${pair.playerOne.surname}
                        </a>
                    </td>
                    <td>
                        <a class="link"
                           href="${pageContext.request.contextPath}/users/user?userId=${pair.playerOne.id}">
                                ${pair.playerTwo.name} ${pair.playerTwo.surname}
                        </a>
                    </td>

                    <c:choose>
                        <c:when test="${pair.maxResult==null}">
                            <c:choose>
                                <c:when test="${pair.impResult==null}">
                                    <td/>
                                </c:when>
                                <c:otherwise>
                                    <td class="text">${pair.impResult} IMP</td>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <td class="text">${pair.maxResult} %</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>