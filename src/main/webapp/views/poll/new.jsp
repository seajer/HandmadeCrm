<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<!DOCTYPE>
<div>
	<form action="startPoll" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" value="${projectId}" name="projectId">
		<input type="hidden" value="${resultId}" name="resultId">
		<c:forEach items="${questions}" varStatus="status">
			<b style="color: green">${status.index+1}      !</b>
		</c:forEach>
		<c:forEach items="${questions}" var="question" varStatus="status">
			<div class="question ${status.first ? 'shown' : 'hidden'}" ${status.first ? '': 'style = "display:none"'}>
				<input type="hidden" value="${question.id}" name="questionId">
				<p>${question.text}</p><p> ${question.id} </p>
				<p style="color: red">${question.recomendations}</p><br/>
				<c:forEach items="${question.answers}" var="answer">
					<input class="answer" name="answers" type="checkbox" value="${answer.id}"> ${answer.text} <br/>
				</c:forEach>				
				<c:if test="${!status.last}"><a href="#" class="next">Next</a></c:if><br/>
				<c:if test="${status.last}"><button class="next">Save poll</button></c:if>
			</div>
		</c:forEach>
	</form>
</div>
