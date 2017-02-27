<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
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

                            <a href="#" class="btn btn-primary">My Sales Performance</a>
                            <a href="#" class="btn btn-primary">Team Sales Performance</a>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button"
                       class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                    <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="container">

    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">


                <c:choose>
                    <c:when test="${user.judge==true}">
                        <h3 class="panel-title"><spring:message code="label.user.judgetours"/></h3>
                    </c:when>
                    <c:otherwise>
                        <h3 class="panel-title"><spring:message code="label.user.playedtours"/></h3>
                    </c:otherwise>
                </c:choose>

            </div>
            <table class="table">
                <thead>
                <tr class="header-panel">
                    <th width="5%"> </th>
                    <th><spring:message code="label.tournament.title"/></th>
                    <c:choose>
                        <c:when test="${user.judge==false}">
                            <th><spring:message code="label.tournament.partner"/></th>
                            <th><spring:message code="label.tournament.result"/></th>
                        </c:when>
                    </c:choose>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tours}" var="tour">
                        <tr>
                            <td align="center"><span class="ionicons ion-trophy"></span></td>
                            <td width="60%"><a href="${pageContext.request.contextPath}/tournaments/tour?tourId=${tour.key.id}">${tour.key.title}</a></td>
                            <c:choose>
                                <c:when test="${user.id==tour.value.playerOne.id}">
                                    <td><a href="${pageContext.request.contextPath}/users/user?userId=${tour.value.playerTwo.id}">${tour.value.playerTwo.surname}</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${pageContext.request.contextPath}/users/user?userId=${tour.value.playerOne.id}">${tour.value.playerOne.surname}</a></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${tour.value.maxResult!=null}">
                                    <td>${tour.value.maxResult} %</td>
                                </c:when>
                                <c:when test="${tour.value.impResult!=null}">
                                    <td>${tour.value.impResult} IMP</td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>