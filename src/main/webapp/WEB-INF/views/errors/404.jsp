<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<body marginwidth="0" marginheight="0">
<div align="center">
    <div>
        <h2><spring:message code="errorpage.404"/></h2>
        <p>
            <a class="link" href="${pageContext.request.contextPath}/#"><spring:message code="errorpage.frontpage"/></a>
        </p>
    </div>
</div>
</body>