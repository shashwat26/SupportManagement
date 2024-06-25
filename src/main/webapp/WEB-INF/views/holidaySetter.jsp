<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Please select holidays</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1>Please Select holiday Date</h1>
	<p>${success}</p>
	<form action="${pageContext.request.contextPath}/insertHoliday" method="post">
		<input type = "date" name = "holiday" required>
		<input type = "text" name = "description" required>
		<input type = "submit" value = "Enter Holiday">
	</form>
	
	<a href="${pageContext.request.contextPath}/listholidays">Go To List</a><br>
	<a href="${pageContext.request.contextPath}/dashboard">Go To Dashboard</a>
</body>

</html>