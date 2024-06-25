<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Support Person</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align="center">Editing ${person.fname} ${person.lname}</h1>
	<hr>
	<form action="updatePerson" method="post">
		<label>First Name</label> <input type="text" name="fname" value = "${person.fname}" required><br>
		<label>Last Name</label> <input type="text" name="lname" value = "${person.lname}" required><br>
		<label>Mobile Number</label> <input type="text" name="mobileNumber" value = "${person.mobileNumber}" required><br>
		<label>Email</label><input type = "text" name = "email" value = "${person.email}" required><br>
		<label>Category</label>
		<select name="supportcategory.id" required>
		<option value = "${categoryz.id}">${categoryz.categoryName}</option>	
			<c:forEach var="category" items="${categoryList}">
				<option value="${category.id}">${category.categoryName}</option>
			</c:forEach>
		</select> <br>
		<label>Department</label>
		<select name="department.id" required>
		<option value = "${department.id}">${department.name}</option>	
			<c:forEach var="departments" items="${departmentList}">
				<option value="${departments.id}">${departments.name}</option>
			</c:forEach>
		</select> <br>
		<label>Day Start At:</label><input type = "number" name = "dayStartTime" value = "${person.dayStartTime}" required><br>
		<label>Day End At:</label><input type = "number" name = "dayEndTime" value = "${person.dayEndTime}" required><br>
		<label>Username</label><input type = "text" name = "username" value = "${user.username}" required><br>
		<input type="submit"  value="Submit">

	</form>



</body>
</html>