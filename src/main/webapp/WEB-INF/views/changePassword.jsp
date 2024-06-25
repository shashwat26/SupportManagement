<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<%@include file="header.jsp" %>
</head>
<body>
	<div>
	<p style="color: red;">${msg}</p>
		<form action="${pageContext.request.contextPath}/passwordChangeByAdmin" method="post">
			<input type = "hidden" name = "id" value = "${user.id}">
			<label>Username</label>
			<input type = "text" name = "username" value = "${user.username}" required><br>
			<label>Current Password</label>
			<input type = "password" name = "password" required><br>
			<label>New Password</label>
			<input type = "password" name = "newpass" value = "${newpass}" required><br>
			<label>Confirm Password</label>
			<input type = "password" name = "conpass" value = "${conpass}" required><br>
			<input type = "submit" value ="Change Password">
			
		</form>
	</div>
</body>
</html>