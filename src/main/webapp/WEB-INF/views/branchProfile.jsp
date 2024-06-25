<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Profile</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<h1 align = "center">Add Branch<br>
	<a href = "${pageContext.request.contextPath}/listBranch">Go To List</a><br>
	You Added: ${branch.name}
	</h1>
</body>
</html>