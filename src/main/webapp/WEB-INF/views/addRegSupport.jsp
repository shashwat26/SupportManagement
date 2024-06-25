<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Regional Support Person</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h2 align="center">Add Regional Person Here</h2>

	<form action="postRegionPerson" method="post">
		<label>First Name</label> <input type="text" name="fname" required><br>
		<label>Last Name</label> <input type="text" name="lname" required><br>
		<label>Mobile Number</label> <input type="text" name="mobileNumber" required><br>
		<label>Email</label> <input type = "email" name = "email" required><br>
		<select name="region.id" required>
			<c:forEach var="region" items="${regionList}">
				<option value="${region.id}">${region.region}</option>
			</c:forEach>
		</select><br>
		<!-- <label>Day Start</label><input type = "number" name = "supportRegPerson.dayStartTime" required><br>
		<label>Day End</label><input type = "number" name = "supportRegPerson.dayEndTime" required><br> -->
		<!-- <label>Username</label><input type = "text" name = "username" required><br>
		<label>Password</label><input type = "password" name = "password" required><br>  -->
		<input type = "submit" value = "Enter">
	</form>

</body>
</html>