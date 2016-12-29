<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<form action="new_project" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		Project <input name="name"/>
		Company <input name="company"/>
		Language 
		<select name="lang">
			<c:forEach items="${languages}" var="language">
				<option value="${language.id}"> ${language.name} </option>
			</c:forEach>	
		</select>
		Project type
		<select name="type">
			<c:forEach items="${types}" var="type">
				<option value="${type.id}"> ${type.name} </option>
			</c:forEach>
		</select>		
		<input type="submit" value="Submit" />
	</form>
</div>
