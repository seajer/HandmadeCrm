<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<form action="edit_question" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden"  name="id"   value="${question.id}"/>
		Recomendations
		<input name="recommendations" value="${question.recomendations}" /> <br>
		Type
		<select name="answType" class="questionType">
			<option selected value="${question.type.id}"> ${question.type.text} </option>
			<c:forEach items="${types}" var="type">
				<option value="${type.id}"> ${type.text} </option>
			</c:forEach>
		</select>
		<c:choose>
			<c:when test="${question.type.text=='Одна відповідь' 
			|| question.type.text=='Кілька відповідей' || question.type.text == 'Одна вдповідь з можливістю обрати свій варіант' 
			|| question.type.text=='Кілька відповідей з можливістю обрати свій варіант'
			|| question.type.text=='Процентре співвідношення представлених варіантів'
			|| question.type.text=='Процентне співвідношення зі своїм варіантом'}" >
				<div class="question">
					Text
					<input name="question" value="${question.text}"/> <br>
					<div id="answers">
						<c:forEach items="${question.answers}" var="answer">
							<div>
								<input name="answerId" type="hidden" value="${answer.id}"/>
								<input name="answerText" value="${answer.text}"/>
								<input type="button" class="remove" value="Remove"/>
							</div>
						</c:forEach>
					</div>
					<input type="button" value="Add answer" class="addAnswer"/>
				</div>
			</c:when>
			<c:when test="${question.type.text=='Розгорнутий (або не розгорнутий) коментар'}">
				<input type="text" name="question" value="${question.text}"> 
			</c:when>
		</c:choose>
		
		<button type="Submit">Submit</button>
	</form>
</div>
