<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<div>
	<form action="editPoll" method="post" >
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" value="${resultId}" name="resultId">
		<c:forEach items="${questions}" varStatus="status">
			<input type="button" style="color: green" value="${status.index+1}" />
		</c:forEach>
		<c:forEach items="${questions}" var="question" varStatus="status">
			<div class="question ${status.first ? 'shown' : 'hidden'}" ${status.first ? '': 'style = "display:none"'}>
				<input type="hidden" value="${question.id}" name="questionId">
				<p>${question.text}</p><p> ${question.id} </p>
				<p style="color: red">${question.recomendations}</p><br/>
				<c:forEach items="${question.answers}" var="answer">
					<input type="hidden" value="${question.type.text}" class="questionType">
					<c:choose>
						<c:when test="${question.type.text=='Кілька відповідей' 
						|| question.type.text=='Кілька відповідей з можливістю обрати свій варіант'}">
							<input class="answer" <c:if test="${fn:contains(result.answers, answer)}">checked="checked"</c:if> name="answers" type="checkbox" value="${answer.id}"><b class="text"> ${answer.text} </b><br/>
						</c:when>
						<c:when test="${question.type.text=='Одна відповідь'
						|| question.type.text=='Одна вдповідь з можливістю обрати свій варіант'}">
							<input class="answer" <c:if test="${fn:contains(result.answers, answer)}">checked</c:if> name="answers" type="radio" value="${answer.id}"><b class="text"> ${answer.text} </b><br/>
						</c:when>
						<c:when test="${question.type.text=='Процентре співвідношення представлених варіантів'
						|| question.type.text=='Процентне співвідношення зі своїм варіантом'}">
							<input class="persentage" name="answers" value="0"><b class="text"> ${answer.text} </b><br/>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${question.type.text=='Одна вдповідь з можливістю обрати свій варіант'
						|| question.type.text=='Кілька відповідей з можливістю обрати свій варіант'
						|| question.type.text=='Процентне співвідношення зі своїм варіантом'}">
						<input type="button" class="customAnswer" value="Add own">
				</c:if>			
				<c:if test="${!status.last}"><input type="button" class="next" value="Next"/></c:if><br/>
				<c:if test="${status.last}"><button class="next">Save pool</button></c:if>
			</div>
		</c:forEach>
	</form>
</div>
