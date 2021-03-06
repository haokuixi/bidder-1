<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
</head>
<body>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/#">
                <spring:message code="header.mainpage"/>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/users/userlist?page=1">
                    <spring:message code="header.users"/>
                </a></li>
                <li><a href="${pageContext.request.contextPath}/tournaments/tourlist?page=1">
                    <spring:message code="header.tournaments"/>
                </a></li>
                <c:choose>
                    <c:when test="${sessionScope.get('loggedUser').isJudge()}">
                        <li><a href="${pageContext.request.contextPath}/judge">
                            <spring:message code="header.judge"/>
                        </a></li>
                    </c:when>
                </c:choose>
            </ul>

            <c:choose>
                <c:when test="${sessionScope.get('loggedUser').getLogin() == null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/users/registerPage">
                            <spring:message code="header.register"/>
                        </a></li>
                        <li><a href="${pageContext.request.contextPath}/loginPage">
                            <spring:message code="header.login"/>
                        </a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/users/editprofile">
                            <spring:message code="header.profileedit"/>
                        </a></li>
                        <li><a href="${pageContext.request.contextPath}/logout">
                            <spring:message code="header.logout"/>
                        </a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
</body>