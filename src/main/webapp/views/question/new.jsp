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
		<select name="answType">
		<c:forEach items="${questionTypes}" var="type">
			<option value="${type.id}"> ${type.text} </option>
		</c:forEach>
		</select>
		<div id="answers">
			<input name="answer"><br/>
		</div>
		<button type="Submit">Submit</button>
	</form>
	<button class="addAnswer">Add answer</button>
</div>
