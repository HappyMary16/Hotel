<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<script type="text/javascript">
    function checkDates() {

        const from = new Date(document.getElementById('from').value).valueOf();
        const to = new Date(document.getElementById('to').value).valueOf();
        const now  = new Date();
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

            <form id="chooseDates" action="controller" onsubmit="return checkDates();">

                <input type="hidden" name="command" value="listRooms"/>

                <p><fmt:message key="choose_dates_jsp.from_date" bundle="${lang}"/></p>
                <input id="from" name="from" type="date" required>
                <p><fmt:message key="choose_dates_jsp.to_date" bundle="${lang}"/></p>
                <input id="to" name="to" type="date" required>

                <input type="submit" value='<fmt:message key="choose_dates_jsp.next" bundle="${lang}"/>'>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>