<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<form action="save_customer_DB" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="fileName" value="${fileName}"/>
		<input type="hidden" name="fileType" value="${fileType}"/>
		<div class="customertDB-ourDB-chain">
			<div>
				<select name="paramName" id="firstSelect">
					<c:forEach items="${CDOFields}" var="field">
						<option>${field}</option>
					</c:forEach>
				</select>
				<input type="number" name="paramNumber" value="0">
			</div>
		</div>
		<input type="button" value="Add Field" id="addCDOField">
		<button type="submit">Submit</button>
	</form>
</div>