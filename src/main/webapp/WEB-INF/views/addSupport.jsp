<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Support Personnel</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h2 align="center">Add Support Person</h2>

	<form action="postSupportPerson" method="post">
		<label>First Name</label> <input type="text"  name="fname" required><br>
		<label>Last Name</label> <input type="text"  name="lname" required><br>
		<label>Mobile Number</label> <input type="text"  name="mobileNumber" required><br>
		<label>Email</label><input type = "email" name = "email"><br>
		<label>Category</label>
		<select name = "supportcategory.id">
		<c:forEach var = "category" items = "${categoryList}">
			<option value = "${category.id}">${category.categoryName}</option>
		</c:forEach>
		</select><br>
		<label>Department</label>
		<select name = "department.id" required>
			<c:forEach var = "department" items = "${departmentList}">
				<option value = "${department.id}">${department.name}</option>
			</c:forEach>
		</select><br>
	    <label>Day Start</label><input type = "number" name = "dayStartTime" required><br>
		<label>Day End</label><input type = "number" name = "dayEndTime" required><br>
		<label>Username</label><input type = "text" name = "username" required><br>
		<label>Password</label><input type = "password" name = "password" required><br> 
		
		<input type = "submit" value = "Submit">
	</form>
</body>
</html>