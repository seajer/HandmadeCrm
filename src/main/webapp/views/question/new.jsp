<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>

<div>
	<form action="new_question" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="questionnaireId" value="${questionaireId}"/>
		Text
		<input name="question" /> <br>
		Recomendations
		<input name="recommendations" /> <br>
		Type
		<c:forEach items="${questionTypes}" var="type">
			<input type="radio" name="answType" value="${type.id}"> ${type.text} <br>
		</c:forEach>
		<button type="Submit">Submit</button>
	</form>
</div>
