<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing Branch</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align="center">Editing ${branch.name}</h1>
	<hr>
	<input type = "hidden" name = "id" value = "${branch.id}">
	<form action="updateBranch" method = "post">
	<label>Branch Name</label>
	<input type = "text" name = "name" value = "${branch.name}" required><br>
	<label>Branch Address</label>
	<input type = "text" name = "address" value = "${branch.address}" required><br>
	<label>Branch Manager</label>
	<input type = "text" name = "branchManager" value = "${branch.branchManager}" required><br>
	<label>Phone Number</label>
	<input type = "text" name = "phone" value = "${branch.phone}" required><br>
	<select name = "branchRegion.id" required>
	<option value = "${branch.branchRegion.id}">${branch.branchRegion.region}</option>
	<c:forEach var = "region" items = "${regionList}">
		<option value = "${region.id}">${region.region}</option>
	</c:forEach>
	</select>
	<input type = "submit" value = "Enter">
	</form>

</body>
</html>