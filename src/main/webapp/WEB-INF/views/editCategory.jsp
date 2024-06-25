<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Category</title>
</head>
<body>

<%@include file="header.jsp"%>

	<form action="updateSupportCategory" method="post">
		<label>Support Category Name</label> <input type="text" name="categoryName" value = "${category.categoryName}" required><br>
		<label>Support Category Description</label> <input type="text" name="categoryDescription" value = "${category.categoryDescription}" required><br>
		<input type = "submit" value = "Submit">
	</form>

</body>
</html>