<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<div>
		<input type="hidden" value="${resultId}" id="resultId">
		<select class="status">
			<option value="${status.name}" selected>${status.name}</option>
			<c:forEach items="${statuses}" var="stat">
				<option value="${stat.name}">${stat.name}</option>
			</c:forEach>
		</select>
		<input type="button" value="Change Status" class="changeStatus"/>
	</div>
	<a href="edit_client_${clientId}">Change clients data</a>
</div>