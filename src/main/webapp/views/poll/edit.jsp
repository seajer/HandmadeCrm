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
		<c:forEach items="${questions}" varStatus="status" var="question">
			<input type="button" <c:choose><c:when test="${fn:contains(answeredQuestions, question.id)}">style="color:red"</c:when>
							<c:otherwise>style="color:green"</c:otherwise></c:choose> value="${status.index+1}" class="headerQuestion" />
		</c:forEach>
		<c:forEach items="${questions}" var="question" varStatus="status">
			<div class="question ${status.first ? 'shown' : 'hidden'}" ${status.first ? '': 'style = "display:none"'}>
				<input type="hidden" value="${question.id}" name="questionId">
				<p>${question.text}</p><p> ${question.id} </p>
				<p style="color: red">${question.recomendations}</p><br/>
				<c:forEach items="${question.answers}" var="answer">
					<input type="hidden" value="${question.type.id}" class="questionType">
					<c:choose>
						<c:when test="${question.type.id == 8 || question.type.id == 10}">
							<input class="answer" <c:if test="${fn:contains(result.answers, answer)}">checked="checked"</c:if> name="answers" type="checkbox" value="${answer.id}"><b class="text"> ${answer.text} </b><br/>
						</c:when>
						<c:when test="${question.type.id == 7 || question.type.id == 9}">
							<input class="answer" <c:if test="${fn:contains(result.answers, answer)}">checked="checked"</c:if> name="answers${answer.id}" type="radio" value="${answer.id}"><b class="text"> ${answer.text} </b><br/>
						</c:when>
						<c:when test="${question.type.id == 11 || question.type.id == 12}">
							<input class="persentage" name="answers" value="0"><b class="text"> ${answer.text} </b><br/>
						</c:when>
						<c:when test="${question.type.id == 13}">
							<textarea class="percentage" name="answer">${question.value}</textarea>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${question.type.id == 14 || question.type.id == 15 || question.type.id == 16}">
						<input type="button" class="customAnswer" value="Add own">
				</c:if>			
				<c:if test="${!status.last}"><input type="button" class="next" value="Next"/></c:if><br/>
				<c:if test="${!status.first}"><input type="button" class="prev" value="Previous"/></c:if><br/>
				<c:if test="${status.last}"><button class="next">Save pool</button></c:if>
			</div>
		</c:forEach>
	</form>
</div>
