<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<div>
	<a href="new_project">Add Project</a>
	<c:forEach items="${projects}" var="project">
	<div>
		<a href="project_clients_${project.id}">${project.title}</a> ||
		<i>${project.companyName}</i> ||
		<i>${project.language.name}</i> ||
		<i>${project.type.name}</i> ||
		<a href="edit_project_${project.id}">Edit</a>
		<a href="delete_project_${project.id}">Delete</a>
	</div>
	</c:forEach>
</div>
