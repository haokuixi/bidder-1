<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="container">
    <div class="well">
        <div class="panel-heading" align="center">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${user.name} ${user.surname}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"><img alt="User Pic"
                                                                            src="https://msc.com.pl/cezar1/fots/${user.pzbsId}a.jpg"
                                                                            onerror="this.src='http://blog.dar.org/sites/default/files/default_images/icon-user-default.png'"
                                                                            class="img-circle img-responsive"
                                                                            width="130" height="160"></div>
                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td><spring:message code="label.user.login"/></td>
                                    <td>${user.login}</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="label.user.pzbs.wzbs"/></td>
                                    <td>${user.wzbs.fullName}</td>
                                </tr>

                                <tr>
                                    <td><spring:message code="label.user.pzbs.pzbs_id"/></td>
                                    <td>${user.pzbsId}</td>
                                </tr>
                                </tbody>
                            </table>
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
            <c:choose>
                <c:when test="${user.judge==true}">
                    <h2 class="list"><spring:message code="label.user.judgetours"/></h2>
                </c:when>
                <c:otherwise>
                    <h2 class="list"><spring:message code="label.user.playedtours"/></h2>
                </c:otherwise>
            </c:choose>

        </div>
        <table class="table">
            <thead class="header">
            <tr class="header-panel">
                <th width="5%"></th>
                <th><spring:message code="label.tournament.title"/></th>
                <c:choose>
                    <c:when test="${user.judge==false}">
                        <th><spring:message code="label.tournament.partner"/></th>
                        <th><spring:message code="label.tournament.result"/></th>
                        <th><spring:message code="label.tournament.startdate"/></th>
                        <th><spring:message code="label.tournament.enddate"/></th>
                    </c:when>
                    <c:otherwise>
                        <th/>
                        <th/>
                        <th><spring:message code="label.tournament.startdate"/></th>
                        <th><spring:message code="label.tournament.enddate"/></th>
                    </c:otherwise>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tours}" var="tour">
                <tr>
                    <td align="center"><span class="ionicons ion-trophy"></span></td>
                    <td>
                        <a class="link"
                           href="${pageContext.request.contextPath}/tournaments/tour?tourId=${tour.key.hashedId}">${tour.key.title}</a>
                    </td>
                    <c:choose>
                        <c:when test="${user.id==tour.value.playerOne.id}">
                            <td>
                                <a class="link"
                                   href="${pageContext.request.contextPath}/users/user?userId=${tour.value.playerTwo.login}">
                                   ${tour.value.playerTwo.surname}
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a class="link"
                                   href="${pageContext.request.contextPath}/users/user?userId=${tour.value.playerOne.login}">
                                   ${tour.value.playerOne.surname}
                                </a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${tour.value.maxResult==null}">
                            <c:choose>
                                <c:when test="${tour.value.impResult==null}">
                                    <td/>
                                </c:when>
                                <c:otherwise>
                                    <td class="text">${tour.value.impResult} IMP</td>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <td class="text">${tour.value.maxResult} %</td>
                        </c:otherwise>
                    </c:choose>
                    <td class="text">${tour.key.startDate}</td>
                    <td class="text">${tour.key.endDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>