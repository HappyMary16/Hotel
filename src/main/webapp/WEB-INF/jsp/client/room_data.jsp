<%@ page import="ua.nure.borodin.hotel.model.entity.Category" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<script type="text/javascript">
    function checkDates() {

        const from = new Date(document.getElementById('from').value).valueOf();
        const to = new Date(document.getElementById('to').value).valueOf();
        const now = new Date();
        const today = new Date(now.getFullYear(), now.getMonth(), now.getDate()).valueOf();

        if (today > from) {
            alert("Date " + document.getElementById('from').value + " is old ");
            return false;
        }
        if (today > to) {
            alert("Date " + document.getElementById('to').value + " is old ");
            return false;
        }
        if (from > to) {
            alert("Date " + document.getElementById('from').value + " is > then "
                + document.getElementById('to').value);
            return false;
        }

        return true;
    }
</script>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form action="controller">

                <input type="hidden" name="command" value="makeApplication"/>

                <p><fmt:message key="room_jsp.table.header.number" bundle="${lang}"/></p>
                <input type="number" id="numberPlaces" name="numberPlaces" min="1" max="10">

                <p><fmt:message key="room_jsp.table.header.category" bundle="${lang}"/></p>
                <select name="category" id="category">
                    <%
                        for (Category c :
                                Category.values()) {
                    %>
                    <option value="<%=c.getId()%>"><%=c%></option>
                    <%
                        }
                    %>
                </select>

                <input type="submit" value="<fmt:message key="room_jsp.button.make_an_order" bundle="${lang}"/>">
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>