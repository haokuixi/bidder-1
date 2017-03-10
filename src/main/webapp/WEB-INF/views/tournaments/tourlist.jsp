<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
</style>

<div class="well">
    <h2 align="center" class="list"><spring:message code="label.tournament.tournaments"/></h2>
    <table class="table">
        <thead class="header">
        <tr>
            <th><spring:message code="label.tournament.title"/></th>
            <th><spring:message code="label.tournament.judge"/></th>
            <th><spring:message code="label.tournament.startdate"/></th>
            <th><spring:message code="label.tournament.enddate"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tour" items="${tourlist}">
            <tr>
                <td class="link"><a class="link" href="${pageContext.request.contextPath}/tournaments/tour?tourId=${tour.id}">${tour.title}</a>
                </td>
                <td class="text">${tour.judge.surname}</td>
                <td class="text">${tour.startDate}</td>
                <td class="text">${tour.endDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="panel-footer">
        <div class="row">
            <div class="col col-xs-4">Strona ${page} z ${pages}
            </div>
            <div class="col col-xs-8">
                <ul class="pagination hidden-xs pull-right">
                    <c:forEach var="p" begin="1" end="${pages}">
                        <li><a href="${pageContext.request.contextPath}/tournaments/tourlist?page=${p}">${p}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>