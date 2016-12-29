<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<form action="new_questionnaire" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input name="desctiption"/>
		<select name="project">
			<c:forEach items="${projects}" var="project">
				<option value="${project.id}" >${project.title}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit">
	</form>
</div>
