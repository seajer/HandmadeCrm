<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="resources/css/choosen.css">
<script src="resources/js/choosen.js" type="text/javascript"></script>
<!DOCTYPE html>
<div>
	<form action="edit_user" method="post">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<input type="hidden" name="id" value="${user.id}"/>
		Name<input type="text" name="name" value="${user.fullName}"/>
		email<input type="text" name="email" value="${user.email}"/>
		phone<input type="text" name="phone" id="phone" value="${user.phone}"/>
		Language 
		<div >
		<select name="lang" class="chosen-select" multiple="multiple">
		 	<c:forEach items="${langsIn}" var="lang">
				<option selected value="${lang.id}">${lang.name}</option>
			</c:forEach>
			<c:forEach items="${langsOut}" var="lang">
				<option value="${lang.id}">${lang.name}</option>
			</c:forEach>	
		</select>
		</div>
		<div>
			<%
				if(request.getParameter("wrongPass")!=null){
					out.print("<p>WrongPassword</p>");
				}
			%>
			<h1> ${wrongPass} </h1>
			Old password<input name="oldPassword"/>
			New password<input name="newPassword"/>
		</div>
		<input type="submit" value="Submit" />
	</form>
</div>
