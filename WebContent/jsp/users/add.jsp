<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
    <c:if test="${not empty flash}">
	    <div class="alert alert-${css} alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>${flash}</strong>
	    </div>
	</c:if>
		
	<h1><spring:message code="user.new.title" text="Register" /></h1>
	
	<spring:url value="/users" var="userActionUrl" />

	<form:form method="POST" action="${pageContext.request.contextPath}/users" modelAttribute="user" >
	<table border="1">
	<tr>
		<td><spring:message code="user.new.name" text="Name" /></td>
		<td><form:input path="name"/><form:errors path="name" cssClass="error" /></td>
	</tr>
	<tr>
		<td><spring:message code="user.new.email" text="Email" /></td>
		<td><form:input type="email" path="email"/><form:errors path="email" cssClass="error" /></td>
	</tr>
	<tr>
		<td><spring:message code="user.new.password" text="Password" /></td>
		<td><form:input type="password" path="password"/><form:errors path="password" cssClass="error" /></td>
	</tr>
	<tr>
		<td><spring:message code="user.new.confirmation" text="Confirmation" /></td>
		<td><form:input type="password" path="confirmation"/><form:errors path="confirmation" cssClass="error" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="<spring:message code="user.new.submit" text="Submit" />"></td>
	</tr>
	</table>
	</form:form>