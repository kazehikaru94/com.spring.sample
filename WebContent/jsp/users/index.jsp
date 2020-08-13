<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
    <h2><spring:message code="user.index.all"/></h2>
 
    <table>
        <tr>
            <th>#</th>
            <th><spring:message code="user.index.th.name" text="Name" /></th>
            <th><spring:message code="user.index.th.email" text="Email" /></th>
            <th></th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td><a href="<spring:url value="users/${user.id}"/>">
                    <spring:message code="user.show" text="Show" /></a>
                    <a href="<spring:url value="users/${user.id}/edit"/>">
                    <spring:message code="user.edit" text="Edit" /></a></td>
            </tr>
        </c:forEach>
    </table>