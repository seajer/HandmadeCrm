<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="resources/css/choosen.css">
<script src="resources/js/choosen.js" type="text/javascript"></script>
<!DOCTYPE>
<div>
	<form action="edit_project" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="id" value="${project.id}">
		Project <input name="name" value="${project.title}"/>
		Company <input name="company" value="${project.companyName}"/>
		Language 
		<select name="lang">
		 	<option selected value="${project.language.id}">${project.language.name}</option>
			<c:forEach items="${otherLangs}" var="language">
				<option value="${language.id}"> ${language.name} </option>
			</c:forEach>	
		</select> 
		Project type
		<select name="type">
		 	<option selected value="${project.type.id}">${project.type.name}</option>
			<c:forEach items="${types}" var="type">
				<option value="${type.id}"> ${type.name} </option>
			</c:forEach>
		</select>
		Users
		<select class="chosen-select" name="users">
			<c:forEach items="${usersIn}" var="user">
				<option selected value="${user.id}">${user.name}</option>
			</c:forEach>
			<c:forEach items="${usersOut}" var="user">
				<option value="${user.id}">${user.name}</option>
			</c:forEach>
		</select>	
		<input type="submit" value="Submit" />
	</form>
</div>