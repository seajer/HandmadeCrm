<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form method="post" action="uploadDb" enctype="multipart/form-data" >
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		File to upload:<input type="file" name="clientFile"/><br/>
		<button type="submit">Submit</button>
	</form>
</div>