<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing ${department.name}</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<form action="updateDepartment" method="post">
		<label>Department Name</label> <input type="text" name="name" value = "${department.name}" required><br>
		<label>Department Description</label> <input type="text" name="description" value = "${department.description}" required><br>
		<input type = "submit" value = "Submit">
	</form>

</body>
</html>