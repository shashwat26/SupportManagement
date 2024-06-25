<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Branch</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align = "center">Add Branch</h1>
	<form action="${pageContext.request.contextPath}/postBranch" method="post">
	<label>Branch Name</label>
	<input type = "text" name = "name" required><br>
	<label>Branch Address</label>
	<input type = "text" name = "address" required><br>
	<label>Branch Manager</label>
	<input type = "text" name = "branchManager"><br>
	<label>Phone Number</label>
	<input type = "text" name = "phone"><br>
	<label>Region</label>
	<select name = "branchRegion.id" required>
	<c:forEach var = "region" items = "${regionList}">
		<option value = "${region.id}">${region.region}</option>
	</c:forEach>
	</select><br>
	<input type = "submit" value = "Enter">
	</form>
</body>
</html>