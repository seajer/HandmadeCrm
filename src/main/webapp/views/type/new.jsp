<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<div>
	<form action="new_projecttype" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		PT <input type="text" name="type">
		<input type="submit" value="Submit" />
	</form>
</div>
