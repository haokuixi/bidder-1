<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<c:choose>
    <c:when test="${success==true}">
        <div class="container">
            <div class="row text-center">
                <div class="col-sm-6 col-sm-offset-3">
                    <br><br>
                    <h2 style="color:#0fad00"><spring:message code="label.register.success"/></h2>
                    <img src="http://osmhotels.com//assets/check-true.jpg">
                    <br><br>
                </div>

            </div>
        </div>
    </c:when>
</c:choose>

<div class="well">
    <h2 align="center" class="list"><spring:message code="label.userlist"/></h2>
    <table class="table">
        <thead class="header">
        <tr>
            <th width="16%" class="tableheader"><spring:message code="label.user.login"/></th>
            <th width="21%"><spring:message code="label.user.firstName"/></th>
            <th width="21%"><spring:message code="label.user.lastName"/></th>
            <th width="12%"><spring:message code="label.user.pzbs.pzbs_id"/></th>
            <th width="15%"><spring:message code="label.user.pzbs.wzbs"/></th>
            <th width="15%"><spring:message code="label.user.judge"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><a class="link"
                       href="${pageContext.request.contextPath}/users/user?login=${user.login}">${user.login}</a></td>
                <td class="text">${user.name}</td>
                <td class="text">${user.surname}</td>
                <td class="text">${user.pzbsId}</td>
                <td class="text">${user.wzbs.shortName}</td>
                <td class="text">
                    <c:choose>
                        <c:when test="${user.judge==true}">
                            <spring:message code="word.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="word.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
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
                        <li><a href="${pageContext.request.contextPath}/users/userlist?page=${p}">${p}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>