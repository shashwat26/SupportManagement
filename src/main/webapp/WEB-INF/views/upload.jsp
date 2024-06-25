<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Photo here</title>
<%@include file="header.jsp"%>
</head>
<body>
<h1>You added ${person.fname}  ${person.lname}</h1>

<form action="${pageContext.request.contextPath}/${person.id}/uploadReg" method = "post" enctype="multipart/form-data">
	<p>Please Select a file:<p>
	<input type = "file" name = "file"><br>
	<input type = "submit" value = "Upload"> 	
	<input type = "submit" value = "Skip">
	
	</form>

</body>
</html>