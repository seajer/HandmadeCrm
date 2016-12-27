<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
	<form action="createUser" method="post">
		Name<input type="text" name="name"/>
		email<input type="text" name="email"/>
		pass<input type="password" name="password"/>
		phone<input type="text" name="phone" id="phone"/>
		role
		<select name="role">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
		</select>
		<input type="submit" value="Submit" />
	</form>
</div>