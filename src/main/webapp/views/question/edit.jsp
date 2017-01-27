<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<form action="edit_question" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden"  name="id"   value="${question.id}"/>
		Text
		<input name="question" value="${question.text}"/> <br>
		Recomendations
		<input name="recommendations" value="${question.recomendations}" /> <br>
		Type
		<select name="answType">
			<option selected value="${question.type.id}"> ${question.type.text} </option>
			<c:forEach items="${types}" var="type">
				<option value="${type.id}"> ${type.text} </option>
			</c:forEach>
		</select>
		<div id="answers">
			<c:forEach items="${question.answers}" var="answer">
				<div>
					<input name="answerId" type="hidden" value="${answer.id}"/>
					<input name="answerText" value="${answer.text}"/>
					<input type="button" class="remove" value="Remove"/>
				</div>
			</c:forEach>
		</div>
		<button type="Submit">Submit</button>
	</form>
	<button class="editAnswer">Add answer</button>
</div>
