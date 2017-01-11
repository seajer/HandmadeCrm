<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div>
	<form action="createUser" method="post">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		Name<input type="text" name="name"/><br/>
		email<input type="text" name="email"/><br/>
		pass<input type="password" name="password"/><br/>
		phone<input type="text" name="phone" id="phone"/><br/>
		role
		<select name="role">
			<c:forEach items="${roles}" var="role">
				<option value="${role.id}">${role.name}</option>
			</c:forEach>
		</select><br/>
		<select name="language">
			<c:forEach items="${languages}" var="language">
				<option value="${language.id}">${language.name}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit" />
	</form>
</div>