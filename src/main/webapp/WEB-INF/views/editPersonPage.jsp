<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing User</title>
</head>
<body>
	<%@include file="headerUser.jsp"%>
	<h1 align="center">Editing ${user.supportPerson.fname} ${user.supportPerson.lname}</h1>
	<hr>
	<form action="updatePersonByUser" method="post">
	<label>First Name</label> <input type="text" name="supportPerson.fname" value = "${user.supportPerson.fname}" required><br>
	<label>Last Name</label> <input type="text" name="supportPerson.lname" value = "${user.supportPerson.lname}" required><br>
	<label>Mobile Number</label> <input type="text" name="supportPerson.mobileNumber" value = "${user.supportPerson.mobileNumber}" required><br>
	<label>Email</label> <input type="text" name="supportPerson.email" value = "${user.supportPerson.email}" required><br>
	<label>Support Category</label>
	<select name="supportPerson.supportcategory.id" required>
		<option value = "${categoryz.id}">${categoryz.categoryName}</option>	
			<c:forEach var="category" items="${categoryList}">
				<option value="${category.id}">${category.categoryName}</option>
			</c:forEach>
		</select> <br>
		<label>Department</label>
		<select name="supportPerson.department.id" required>
		<option value = "${department.id}">${department.name}</option>	
			<c:forEach var="departments" items="${departmentList}">
				<option value="${departments.id}">${departments.name}</option>
			</c:forEach>
		</select> <br>
		<label>Day Start</label><input type = "number" name = "supportPerson.dayStartTime" value = "${user.supportPerson.dayStartTime}" required><br>
		<label>Day End</label><input type = "number" name = "supportPerson.dayEndTime" value = "${user.supportPerson.dayEndTime}" required><br>
		<label>Username</label><input type = "text" name = "username" value = "${user.username}" required><br>
		
		<input type="submit"  value="Submit">
	
	</form>
</body>
</html>