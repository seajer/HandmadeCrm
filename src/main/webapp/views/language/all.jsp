<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div>
	<a href="new_language">Add language</a>
	<c:forEach items="${languages}" var="language">
	<div>
		<form action="edit_language" method="post">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<input type="hidden" name="id" value="${language.id}"/>
			<input name="name" value="${language.name}"/>
			<input type="submit" value="Submit" />
			<a href="delete_lang_${language.id}">Delete</a>
		</form>
	</div>
	</c:forEach>
</div>
