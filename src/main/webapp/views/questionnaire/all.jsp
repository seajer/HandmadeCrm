<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div>
	<a href="new_questionnaire">New Questionnaire</a>
	<c:forEach items="${questionaire}" var="quest">
		<br/>
		<a href="view_questionnaire_${quest.id}">${quest.description}</a>
	</c:forEach>
</div>
