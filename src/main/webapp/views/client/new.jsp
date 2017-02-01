<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>
	<form:form action="saveExamined" method="post" modelAttribute="clientDataObject">
		Name
		<form:input path="name"/><br/>
		Surname
		<form:input path="surname"/><br/>
		Age
		<form:input path="age"/><br/>
		Position
		<form:input path="position"/><br/>
		Company name
		<form:input path="companyName"/><br/>
		Industry
		<form:input path="industry"/><br/>
		Workers
		<form:input path="workersCount"/><br/>
		Years earning
		<form:input path="yearEarning"/><br/>
		Description
		<form:input path="description"/><br/>
		Address
		<form:input path="adress"/><br/>
		Country
		<form:input path="country"/><br/>
		Phone
		<form:input path="phone"/><br/>
		e-mail
		<form:input path="email"/><br/>
		Site address
		<form:input path="site"/><br/>
		Comment
		<form:input path="comment" />
		Recall time
		<form:input path="recallTime"/>
		Status
		<form:select path="status">
			<c:forEach items="${status}" var="stat">
				<option value="${stat.id}">${stat.name}</option>
			</c:forEach>
		</form:select>
		<br/>
		<button type="submit">Submit</button>
	</form:form>
</div>