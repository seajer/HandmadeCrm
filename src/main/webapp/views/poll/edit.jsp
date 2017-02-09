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
		<input type="hidden" value="${principal}" class="principal">
		<c:forEach items="${questions}" varStatus="status" var="question">
			<input type="button" <c:choose><c:when test="${fn:contains(answeredQuestions, question.id)}">style="color:red"</c:when>
							<c:otherwise>style="color:green"</c:otherwise></c:choose> value="${status.index+1}" class="headerQuestion" />
		</c:forEach>
	
		<c:forEach items="${questions}" var="question" varStatus="status">
			<div class="question ${status.first ? 'shown' : 'hidden'}" ${status.first ? '': 'style = "display:none"'}>
				<input type="hidden" value="${question.id}" name="questionId">
				<p>${question.text}</p><p> ${question.id} </p>
				<p style="color: red">${question.recomendations}</p><br/>
				<c:choose>
					<c:when test="${question.type.id == 14 || question.type.id == 15 || question.type.id == 16}">
						<input type="hidden" value="${question.type.id}" class="questionType">
						<table border=5>
					 		<tr>
					 			<th></th>
					 			<c:forEach items="${question.answers}" var="answer">
					 				<th>${answer.text}</th>
					 			</c:forEach>
					 		</tr>
					 		<c:forEach items="${question.tableQuestions}" var="tableQuestion">
					 			<tr><td>${tableQuestion.text}<input type="hidden" value="${tableQuestion.id}" class="tableQuestionId"></td>
					 			<c:forEach items="${question.answers}" var="answer">
					 				<c:choose>
					 					<c:when test="${question.type.id == 14}">
					 						<td><input class="answer${tableQuestion.id}" name="answers${tableQuestion.id}" type="radio" value="${answer.id}"></td>
					 					</c:when>
					 					<c:when test="${question.type.id == 15}">
					 						<td><input class="answer${tableQuestion.id}" name="answers" type="checkbox" value="${answer.id}"></td>
					 					</c:when>
					 					<c:otherwise>
					 						<td><input class="answer${tableQuestion.id}" name="answers" value="0"></td>
					 					</c:otherwise>
					 				</c:choose>
					 			</c:forEach>
					 			</tr>
					 		</c:forEach>
					 	</table>
					 	<c:if test="${!status.last}"><input type="button" class="nextTable" value="Next"/></c:if><br/>
						<c:if test="${!status.first}"><input type="button" class="prevTable" value="Previous"/></c:if><br/>
						<c:if test="${status.last}"><button class="nextTable">Save pool</button></c:if>
					</c:when>
					<c:otherwise>
						<input type="hidden" value="${question.type.id}" class="questionType">
						<c:choose>
							<c:when test="${not empty question.answers}">
								<c:forEach items="${question.answers}" var="answer">
								<div>
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
									</c:choose>
								</div>
							</c:forEach>
							</c:when>
							<c:otherwise>
								<c:if test="${question.type.id == 13}">
									<textarea class="openAnswer" name="answer"></textarea>
								</c:if>
							</c:otherwise>
						</c:choose>
						
						<c:if test="${question.type.id == 9 || question.type.id == 10 || question.type.id == 12}">
							<input type="button" class="customAnswer" value="Add own">
						</c:if>			
						<c:if test="${!status.last}"><input type="button" class="next" value="Next"/></c:if><br/>
						<c:if test="${!status.first}"><input type="button" class="prev" value="Previous"/></c:if><br/>
						<c:if test="${status.last}"><button class="next">Save pool</button></c:if>
					</c:otherwise>
				</c:choose>
			
			</div>
		</c:forEach>
	</form>
</div>
