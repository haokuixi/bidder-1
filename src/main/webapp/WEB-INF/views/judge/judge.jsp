<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    textarea.xml {
        width: 100%;
        height: 50%;
    }

    label.error {
        font-size: large;
        color: #ff0000;
    }
</style>

<c:choose>
    <c:when test="${success==true}">
        <div class="container">
            <div class="row text-center">
                <div class="col-sm-6 col-sm-offset-3">
                    <br><br>
                    <h2 style="color:#0fad00"><spring:message code="label.upload.success"/></h2>
                    <img src="http://osmhotels.com//assets/check-true.jpg">
                    <br><br>
                </div>

            </div>
        </div>
    </c:when>
</c:choose>

<form:form action="${pageContext.request.contextPath}/judge/import" method="POST" modelAttribute="file">
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>
                    <spring:message code="label.xmlContentUpload"/>
                </strong>
            </div>
            <div class="panel-body">
                <h4>
                    <spring:message code="label.inputxml"/>
                </h4>
                <div>
                    <div>
                        <form:textarea path="xmlContent" value="" class="xml"/>
                        <br/>
                        <c:choose>
                            <c:when test="${validationError!=null}">
                                <label class="error"><spring:message code="${validationError}"/></label>
                            </c:when>
                        </c:choose>
                    </div>
                    <br/>
                    <br/>
                    <button type="submit" class="btn btn-lg btn-primary" id="uploadFile">
                        <spring:message code="label.upload"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</form:form>