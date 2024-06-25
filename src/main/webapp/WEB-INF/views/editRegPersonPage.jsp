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
	<h1 align="center">Editing ${user.supportRegPerson.fname} ${user.supportRegPerson.lname}</h1>
	<hr>
	<form action="updateRegionPersonByUser" method="post">
	<input type = "hidden" name = "id" value = "${user.id}">
		<label>First Name</label> <input type="text" name="supportRegPerson.fname" value = "${user.supportRegPerson.fname}" required><br>
		<label>Last Name</label> <input type="text" name="supportRegPerson.lname" value = "${user.supportRegPerson.lname}" required><br>
		<label>Mobile Number</label> <input type="text" name="supportRegPerson.mobileNumber" value = "${user.supportRegPerson.mobileNumber}" required><br>
		<select name="supportRegPerson.region.id" required>
			<option value = "${regions.id}">${regions.region}</option> 
			<c:forEach var="region" items="${regionList}">
				<option value="${region.id}">${region.region}</option>
			</c:forEach>
		</select> <br>
		<label>Day Start</label><input type = "number" name = "supportRegPerson.dayStartTime" value = "${user.supportRegPerson.dayStartTime}" required><br>
		<label>Day End</label><input type = "number" name = "supportRegPerson.dayEndTime" value = "${user.supportRegPerson.dayEndTime}" required><br>
		<label>Username</label><input type = "text" name = "username" value = "${user.username}" required><br>
		<input type="submit"  value="Submit"><br>
		</form>
</body>
</html>