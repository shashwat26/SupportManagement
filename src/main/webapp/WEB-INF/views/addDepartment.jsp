<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Department</title>
</head>
<body>
<%@include file="header.jsp"%>
<h2 align="center">Add Department</h2>
<form action="postDepartment" method = "post">
	<label>Department Name</label><input type = "text" name = "name" required><br>
	<label>Department Description</label><input type = "text" name = "description" required><br>
	<input type = "submit" value = "Enter">
</form>

</body>
</html>