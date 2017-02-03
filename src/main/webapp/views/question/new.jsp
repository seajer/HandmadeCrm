<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<div>
	<form action="new_question" method="post" id="creatingQuestion">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="questionnaireId" value="${questionaireId}"/>
		Type
		<select name="answType" class="questionType">
			<c:forEach items="${questionTypes}" var="type">
				<option value="${type.id}"> ${type.text} </option>
			</c:forEach>
		</select>
		Recomendations
		<input name="recommendations" /> <br>
		<div class="question">
			Text
			<input name="question" /> <br>
			Answers
			<div id="answers">
				<input name="answer"><br/>
			</div>
			<input type="button" value="Add answer" class="addAnswer"/>
		</div>
		<button type="Submit">Submit</button>
	</form>
</div>
