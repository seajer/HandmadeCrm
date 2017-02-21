<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<p>${project.title}</p>
	<c:forEach items="${clients}" var="client">
		<b>${client.firstName}</b> |_| 
		<b>${client.lastName}</b> |_| 
		<b>${client.company}</b> |_| 
		<b>${client.phone}</b> |_| 
		<b>${client.statusName}</b> |_| 
		<a href="edit_result_${client.resultId}">Edit result</a>
		<br/>
	</c:forEach>
	<c:if test="${project.type.id == 2}"><a href="new_poll_${project.id}">New Pool</a></c:if>
</div>