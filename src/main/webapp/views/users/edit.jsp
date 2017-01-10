<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div>
	<form action="edit_user" method="post">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<input type="hidden" name="id" value="${user.id}"/>
		Name<input type="text" name="name" value="${user.fullName}"/>
		email<input type="text" name="email" value="${user.email}"/>
		phone<input type="text" name="phone" id="phone" value="${user.phone}"/>
		<div>
			<%
				if(request.getParameter("wrongPass")==null){
					out.print("clear");
				} else {
					out.print("<p>WrongPassword</p>");
				}
			%>
			Old password<input name="oldPassword"/>
			New password<input name="newPassword"/>
		</div>
		<input type="submit" value="Submit" />
	</form>
</div>
