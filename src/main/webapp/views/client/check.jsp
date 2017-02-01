<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form action="check_client" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="projectId" value="${projectId}">
		Phone<input type="text" name="phone"/>
		Company name<input type="text" name="company"/>
		<button type="submit">Submit</button>
	</form>
</div>