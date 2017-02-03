<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<form action="edit_questionnaire" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="id" value="${questionnaire.id}"/>
		<input name="description" value="${questionnaire.description}"/>
		<br/>
		<button type="submit">Submit</button>
		<br/>
		<c:forEach items="${questionnaire.questions}" var="question" varStatus="i">
			<b>${i.index+1}. ${question.recomendations}</b>
			<c:choose>
				<c:when test="${question.isVisible()}">
					<a href="hide_question_${question.id}">Hide question</a>
				</c:when>
				<c:otherwise>
					<a href="show_question_${question.id}">Show question</a>
				</c:otherwise>
			</c:choose>
			<br/>
		</c:forEach>
		<a href="new_question_${questionnaire.id}">Add new question</a>
	</form>
</div>