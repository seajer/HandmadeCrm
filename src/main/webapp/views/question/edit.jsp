<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<c:choose>
		<c:when test="${question.type.id == 7 || question.type.id == 8 
		|| question.type.id == 9 || question.type.id == 10 || question.type.id == 11 || question.type.id == 12}">
				<form action="edit_question" method="post">
					<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					<input type="hidden"  name="id"   value="${question.id}"/>
					Recomendations
					<input name="recommendations" value="${question.recomendations}" /> <br>
					Type
					<select name="answType" class="questionType">
						<option selected value="${question.type.id}"> ${question.type.text} </option>
						<c:forEach items="${types}" var="type">
							<option value="${type.id}"> ${type.text} </option>
						</c:forEach>
					</select>
					<c:choose>
						<c:when test="${question.type.id == 7 || question.type.id == 8
						|| question.type.id == 9 || question.type.id == 10 || question.type.id == 11
						|| question.type.id == 12}" >
							<div class="question">
								Text
								<input name="question" value="${question.text}"/> <br>
								<div id="answers">
									<c:forEach items="${question.answers}" var="answer">
										<div>
											<input name="answerId" type="hidden" value="${answer.id}"/>
											<input name="answerText" value="${answer.text}"/>
											<input type="button" class="remove" value="Remove"/>
										</div>
									</c:forEach>
								</div>
								<input type="button" value="Add answer" class="addAnswer"/>
							</div>
						</c:when>
						<c:when test="${question.type.id== 13}">
							<input type="text" name="question" value="${question.text}"> 
						</c:when>
					</c:choose>
					<button type="Submit">Submit</button>
				</form>
		</c:when>
		<c:when test="${question.type.id == 13}">
			<form action="edit_open" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="id"   value="${question.id}"/>
				Recomendations
				<input name="recommendations" value="${question.recomendations}" /><br>
				Type
				<select name="answType" class="questionType">
					<option selected value="${question.type.id}"> ${question.type.text} </option>
					<c:forEach items="${types}" var="type">
						<option value="${type.id}"> ${type.text} </option>
					</c:forEach>
				</select><br/>
				<div class="question">
					<input type="text" name="question" value="${question.text}">
				</div>
				<button type="Submit">Submit</button>
			</form>
		</c:when>
		<c:otherwise>
			<form action="edit_table" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="id"   value="${question.id}"/>
				Recomendations
				<input name="recommendations" value="${question.recomendations}" /><br>
				Type
				<select name="answType" class="questionType">
					<option selected value="${question.type.id}"> ${question.type.text} </option>
					<c:forEach items="${types}" var="type">
						<option value="${type.id}"> ${type.text} </option>
					</c:forEach>
				</select><br/>
				<div class="question">
					<table border='5'>
						<tr><th>запитання &#8595; відповіді &#8594;</th>
						<c:forEach items="${question.answers}" var="answer">
					 		<th><input type="hidden" value="${answer.id}" name="answerId"/>
					 		<input type="text" value="${answer.text}" name="answer" /></th>
					 	</c:forEach>
					 	<c:forEach items="${question.tableQuestions}" var="tableQuestion">
					 		<tr>
					 			<td><input type="text" value="${tableQuestion.text}" name="question" />
					 			<input type="hidden" value="${tableQuestion.id}" name="questionsId" class="tableQuestionId"></td>
					 			<c:forEach items="${question.answers}" var="answer">
					 				<td></td>
					 			</c:forEach>
					 		</tr>
					 	</c:forEach>
					</table>
					<input type='button' value='Add question' class='addRow'/>
					<input type='button' value='Add answer' class='addColumn'/>
				</div>
				<button type="Submit">Submit</button>
			</form>
		</c:otherwise>
	</c:choose>
</div>
