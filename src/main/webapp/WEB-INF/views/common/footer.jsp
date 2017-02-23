<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <style>
        .footer {
            width: 100%;
            float: left;
            background: #ffedec;
            height: 50px;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<div class="footer navbar-fixed-bottom">
    <c:choose>
        <c:when test="${sessionScope.get('loggedUser').getLogin() != null}">
            <h2> heloł ${sessionScope.get("loggedUser").getLogin()}</h2>

            <c:choose>
                <c:when test="${user.judge==true}">
                    <h3> jesteś sędziom </h3>
                </c:when>
                <c:otherwise>
                    <h3> jesteś graczem </h3>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <h2> heloł gościu </h2>
        </c:otherwise>
    </c:choose>

</div>

