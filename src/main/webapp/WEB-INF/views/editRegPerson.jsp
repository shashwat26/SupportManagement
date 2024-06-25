<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing Regional Person</title>
</head>
<body>
<%@include file="header.jsp"%>
<form action="updateRegionPerson" method="post">
	<input type = "hidden" name = "id" value = "${user.id}">
		<label>First Name</label> <input type="text" name="fname" value = "${person.fname}" required><br>
		<label>Last Name</label> <input type="text" name="lname" value = "${person.lname}" required><br>
		<label>Mobile Number</label> <input type="text" name="mobileNumber" value = "${person.mobileNumber}" required><br>
		<label>Email</label> <input type = "email" name = "email" value = "${person.email}" required><br>
		<select name="region.id" required>
			<option value = "${regions.id}">${regions.region}</option> 
			<c:forEach var="region" items="${regionList}">
				<option value="${region.id}">${region.region}</option>
			</c:forEach>
		</select> <br>
		<input type="submit"  value="Submit"><br>
		</form>
</body>
</html>