<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Support Category</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align = "center">Add Support Category</h1>
	
	<form action = "postSupportCategory" method = "post">
	<label>Category Name</label>
	<input type = "text" name = "categoryName" required><br>
	<label>Category Description</label>
	<input type = "text" name = "categoryDescription" required><br>
	
	<input type = "submit" value = "Submit">
	
	</form>
	
	

</body>
</html>