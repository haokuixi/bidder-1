<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/users/userlist">Uzytkownicy</a></li>
                <li><a href="${pageContext.request.contextPath}/tournaments/tourlist">Turnieje</a></li>
                <li><a href="${pageContext.request.contextPath}/users/registerPage">Zarejesruj</a></li>
            </ul>

            <c:choose>
                <c:when test="${sessionScope.get('loggedUser').getLogin() == null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/loginPage">Zaloguj</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/logout">Wyloguj</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>


        </div>
    </div>
</nav>