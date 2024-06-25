<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department Added</title>
</head>
<%@include file="header.jsp"%>
		<h1 align = "center">You added a category</h1>
		<h2>${department.name}</h2>
		<h2>${department.description}</h2><br>
		<button onclick="goBack()">Go Back</button>
</body>

<script>
function goBack() {
	window.location = "${pageContext.request.contextPath}/listDepartment"
}
</script>
</html>