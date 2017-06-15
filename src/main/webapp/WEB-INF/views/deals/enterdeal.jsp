<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    label.error {
        color: #ff0000;
    }
</style>

<c:choose>
    <c:when test="${error!=null}">
        <div class="container" align="center">
            <div class="well">
                <label class="error"><spring:message code="${error}"/></label>
            </div>
        </div>
    </c:when>
</c:choose>

<div class="container" align="center">
    <div class="well">
        <spring:message code="label.deal.tooltip"/>
    </div>
</div>

<div class="container" align="center">
    <div class="well">
        <form:form modelAttribute="deal" method="POST" enctype="utf8">
            <table class="table">
                <tr>
                    <td/>
                    <td>
                        <table>
                            <tr>
                                <th/>
                                <th style="font-size:large">
                                    <spring:message code="label.deal.cards.north"/>
                                </th>
                                <th/>
                            </tr>
                            <tr>
                                <td>♠</td>
                                <td>
                                    <form:input path="northSpades" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♥</td>
                                <td>
                                    <form:input path="northHearts" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♦</td>
                                <td>
                                    <form:input path="northDiamonds" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♣</td>
                                <td>
                                    <form:input path="northClubs" value="" class="form-control"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td/>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <th/>
                                <th style="font-size:large">
                                    <spring:message code="label.deal.cards.west"/>
                                </th>
                                <th/>
                            </tr>
                            <tr>
                                <td>♠</td>
                                <td>
                                    <form:input path="westSpades" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♥</td>
                                <td>
                                    <form:input path="westHearts" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♦</td>
                                <td>
                                    <form:input path="westDiamonds" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♣</td>
                                <td>
                                    <form:input path="westClubs" value="" class="form-control"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td/>
                    <td>
                        <table>
                            <tr>
                                <th/>
                                <th style="font-size:large">
                                    <spring:message code="label.deal.cards.east"/>
                                </th>
                                <th/>
                            </tr>
                            <tr>
                                <td>♠</td>
                                <td>
                                    <form:input path="eastSpades" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♥</td>
                                <td>
                                    <form:input path="eastHearts" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♦</td>
                                <td>
                                    <form:input path="eastDiamonds" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♣</td>
                                <td>
                                    <form:input path="eastClubs" value="" class="form-control"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td/>
                    <td>
                        <table>
                            <tr>
                                <th/>
                                <th style="font-size:large">
                                    <spring:message code="label.deal.cards.south"/>
                                </th>
                                <th/>
                            </tr>
                            <tr>
                                <td>♠</td>
                                <td>
                                    <form:input path="southSpades" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♥</td>
                                <td>
                                    <form:input path="southHearts" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♦</td>
                                <td>
                                    <form:input path="southDiamonds" value="" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>♣</td>
                                <td>
                                    <form:input path="southClubs" value="" class="form-control"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td/>
                </tr>
            </table>

            <input type="hidden" name="dealId" value="${dealId}"/>
            <button type="submit" class="btn btn-primary btn-lg btn-block login-button">
                <spring:message code="label.form.submit"/>
            </button>
        </form:form>
    </div>
</div>