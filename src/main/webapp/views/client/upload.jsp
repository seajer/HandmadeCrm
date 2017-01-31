<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<form method="post" action="uploadDb" enctype="multipart/form-data">
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <input type="file" name="file"/>
            <input type="submit"/>
			<select name="type">
				<c:forEach items="${types}" var="type">
					<option>${type}</option>
				</c:forEach>
			</select>           
    </form>
</div>