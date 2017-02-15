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
			<input type="button" <c:choose><c:when test="${fn:contains( tableAnswers.keySet(), question.id) || fn:contains(customAnswers.keySet(), question.id)}">style="color:green"</c:when><c:otherwise>style="color:red"</c:otherwise></c:choose> value="${status.index+1}" class="headerQuestion" />
		</c:forEach>
	
		<c:forEach items="${questions}" var="question" varStatus="status">
			<div class="question ${status.first ? 'shown' : 'hidden'}" ${status.first ? '': 'style = "display:none"'}>
				<input type="hidden" value="${question.id}" name="questionId">
				<p>${question.text}</p><p> ${question.id} </p>
				<p style="color: red">${question.recomendations}</p><br/>
				<input type="hidden" value="${question.type.id}" class="questionType">
				<c:choose>
					<c:when test="${question.type.id == 8 || question.type.id == 9 || question.type.id == 10}">
						<table>
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
					 					<c:when test="${question.type.id == 8}">
					 						<td><input class="answer${tableQuestion.id}" <c:if test="${fn:contains( tableAnswers[tableQuestion.id], answer.id)}">checked="checked"</c:if> name="answers${tableQuestion.id}"  type="radio" value="${answer.id}"/></td>
					 					</c:when>
					 					<c:when test="${question.type.id == 9}">
					 						<td><input class="answer${tableQuestion.id}" <c:if test="${fn:contains( tableAnswers[tableQuestion.id], answer.id)}">checked="checked"</c:if> name="answers" type="checkbox" value="${answer.id}"></td>
					 					</c:when>
					 					<c:otherwise>
					 						<td><input class="answer${tableQuestion.id}" name="answers" value="0" /></td>
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
						<c:choose>
							<c:when test="${not empty question.answers}">
								<c:forEach items="${question.answers}" var="answer">
								<div>
									<c:choose>
										<c:when test="${question.type.id == 2 || question.type.id == 4}">
											<input class="answer" <c:if test="${fn:contains(tableAnswers[question.id], answer.id)}">checked="checked"</c:if> name="answers" type="checkbox" value="${answer.id}"><b class="text"> ${answer.text} </b><br/>
										</c:when>
										<c:when test="${question.type.id == 1 || question.type.id == 3}">
											<input class="answer" <c:if test="${fn:contains(tableAnswers[question.id], answer.id)}">checked="checked"</c:if> name="answers${question.id}" type="radio" value="${answer.id}"><b class="text"> ${answer.text} </b><br/>
										</c:when>
										<c:when test="${question.type.id == 5 || question.type.id == 6}">
											<input class="persentage" name="answers" value="0"><b class="text"> ${answer.text} </b><br/>
										</c:when>
									</c:choose>
								</div>
							</c:forEach>
							</c:when>
							<c:otherwise>
								<c:if test="${question.type.id == 7}">
									<textarea class="openAnswer" name="answer"><c:if test="${fn:contains(customAnswers.keySet(), question.id)}">${customAnswers[question.id]}</c:if></textarea>
								</c:if>
							</c:otherwise>
						</c:choose>
						
						<c:if test="${question.type.id == 3 || question.type.id == 4 || question.type.id == 6}">
							<c:choose>
								<c:when test="${fn:contains( customAnswers.keySet(), question.id)}">
									<input class="custom_answer" value="${customAnswers[question.id]}" />
								</c:when>
								<c:otherwise>
									<input type="button" class="customAnswer" value="Add own">
								</c:otherwise>
							</c:choose>
						</c:if>			
						<c:if test="${!status.last}"><input type="button" class="next" value="Next"/><br/></c:if>
						<c:if test="${!status.first}"><input type="button" class="prev" value="Previous"/><br/></c:if>
						<br/><c:if test="${status.last}"><button class="next">Save pool</button></c:if>
					</c:otherwise>
				</c:choose>
			
			</div>
		</c:forEach>
	</form>
</div>
