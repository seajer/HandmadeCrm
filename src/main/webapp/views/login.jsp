<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
<!DOCTYPE>
<div>

	<c:if test="${not empty message}">
		<c:out value="${message}" />
	</c:if>
	
	<form role="form" action="loginprocessing" method="post">
		<fieldset>
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<input type="text" name="username" required autofocus>
			<input type="password" name="password" required>		
			<button class="btn btn-lg btn-success btn-block" type="submit">Enter</button>
		</fieldset>
	</form>
</div>
