<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<a href="new_user">New User</a>
	<table>
		<tr>
			<th>Name</th>
			<th>email</th>
			<th>phone</th>
			<th></th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td> ${user.fullName} </td>
				<td> ${user.email} </td>
				<td> ${user.phone} </td>
				<td> <a href="delete_user_${user.id}">delete</a> </td>
			</tr>
		</c:forEach>
	</table>
</div>
