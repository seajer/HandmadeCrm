<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<p>${questionnaire.description}</p>
	<br/>
	<c:forEach items="${questionnaire.questions}" var="question">
		<p>${question.text}</p>
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
</div>