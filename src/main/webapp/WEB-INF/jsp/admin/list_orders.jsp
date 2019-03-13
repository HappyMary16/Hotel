<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <c:choose>
                <c:when test="${fn:length(userOrderBeanList) == 0}">No such orders</c:when>

                <c:otherwise>

                    <table id="list_order_table">
                        <thead>
                        <tr>
                            <td>№</td>
                            <td><fmt:message key="list_orders_jsp.table.header.client" bundle="${lang}"/></td>
                            <td><fmt:message key="list_orders_jsp.table.header.bill" bundle="${lang}"/></td>
                            <td><fmt:message key="list_orders_jsp.table.header.status" bundle="${lang}"/></td>
                            <td>Confirm</td>
                        </tr>
                        </thead>

                        <c:forEach var="bean" items="${userOrderBeanList}">

                            <tr>
                                <td>${bean.id}</td>
                                <td>${bean.firstName} ${bean.lastName}</td>
                                <td>${bean.orderBill}</td>
                                <td>${bean.statusName}</td>
                                <td>
                                    <c:if test="${bean.statusName == 'Opened'}">
                                        <form action="controller">
                                            <input type="hidden" name="command" value="confirmOrder"/>
                                            <input type="hidden" name="orderId" value="${bean.id}">
                                            <input type="submit" value="confirm">
                                        </form>
                                    </c:if>
                                </td>
                            </tr>

                        </c:forEach>
                    </table>

                </c:otherwise>
            </c:choose>

            <br>
            <br>

            <c:choose>
                <c:when test="${fn:length(applications) == 0}">No such applications</c:when>

                <c:otherwise>
                    <table id="list_application_table">
                        <thead>
                        <tr>
                            <td>№</td>
                            <td><fmt:message key="room_jsp.table.header.number" bundle="${lang}"/></td>
                            <td><fmt:message key="room_jsp.table.header.category" bundle="${lang}"/></td>
                            <td>Create order</td>
                        </tr>
                        </thead>

                        <c:forEach var="bean" items="${applications}">

                            <tr>
                                <td>${bean.id}</td>
                                <td>${bean.numberPlaces}</td>
                                <td>${bean.category.toString()}</td>
                                <td>
                                    <form action="controller">
                                        <input type="hidden" name="command" value="createOrder"/>
                                        <input type="hidden" name="appId" value="${bean.id}">
                                        <input type="submit" value="create order">
                                    </form>
                                </td>
                            </tr>

                        </c:forEach>
                    </table>

                </c:otherwise>
            </c:choose>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>