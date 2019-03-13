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

            <p><fmt:message key="order_created_jsp.order_created" bundle="${lang}"/></p>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>