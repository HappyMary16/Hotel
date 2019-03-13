<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <c:choose>
                <c:when test="${fn:length(orders) == 0}">No such orders</c:when>

                <c:otherwise>
                    <table id="list_order_table">
                        <thead>
                        <tr>
                            <td><fmt:message key="list_client_order_jsp.room" bundle="${lang}"/></td>
                            <td><fmt:message key="list_orders_jsp.table.header.bill" bundle="${lang}"/></td>
                            <td><fmt:message key="list_client_order_jsp.pay" bundle="${lang}"/></td>
                        </tr>
                        </thead>

                        <c:forEach var="bean" items="${orders}">

                            <tr>
                                <td>${bean.roomsId}</td>
                                <td>${bean.bill}</td>

                                <td>
                                    <c:if test="${bean.status.id == 1}">
                                    <form action="controller">
                                        <input type="hidden" name="command" value="payForOrder"/>
                                        <input type="hidden" name="roomId" value="${bean.id}">
                                        <input type="submit" value="pay">
                                    </form>
                                    </c:if>
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