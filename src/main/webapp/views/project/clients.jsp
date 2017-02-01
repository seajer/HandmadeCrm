<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<c:forEach items="${results}" var="result">
		<div>
			<a href="edit_result_${result.id}">${result.id}</a>
		</div>
	</c:forEach>
</div>