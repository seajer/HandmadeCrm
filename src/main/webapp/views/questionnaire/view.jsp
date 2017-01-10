<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<p>${questionnaire.text}</p>
	<br/>
	<c:forEach items="${questionnaire.questions}" var="question">
		<p>${question.questionText}</p>	
		<a href="edit_question_${question.questionId}">Edit question</a>
		<br/>
	</c:forEach>
	<a href="new_question_${questionnaire.id}">Add new question</a>
	<a href="edit_questionnaire_${questionnaire.id}">Edit questionnaire</a>
</div>
