<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<a href="new_projecttype">New Project Type</a>
	<c:forEach items="${types}" var="type">
	<div>
		<form action="edit_projectType" method="post">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<input type="hidden" name="id" value="${type.id}"/>
			<input name="name" value="${type.name}"/>
			<input type="submit" value="Submit" />
			<a href="delete_projectType_${type.id}">Delete</a>
		</form>
	</div>
	</c:forEach>
</div>
