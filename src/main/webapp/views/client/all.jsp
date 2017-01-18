<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<p>${project.title}</p>
	<c:forEach items="${clients}" var="client">
		<b>${client.firstName}</b>
		<b>${client.lastName}</b>
		<b>${client.phone}</b>
		<b>${client.statusName}</b>
		<a href="edit_result_${client.resultId}">Edit result</a>
		<br/>
	</c:forEach>
	<c:if test="${project.type.name == 'Poll using yellowpages'}"><a href="new_poll_${project.id}">New Pool</a></c:if>
</div>