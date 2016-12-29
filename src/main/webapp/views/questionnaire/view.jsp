<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<p>${questionnaire.description}</p>
	<br/>
	<c:forEach items="${questionnaire.questions}" var="question">
		<p>${question.text}</p>
		<br/>
	</c:forEach>
</div>
