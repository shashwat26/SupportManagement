<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Added Success!!!</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align = "center">You added: <br> ${supportperson.fname} ${supporperson.lname} </h1>
	
	<form action="${supportperson.personnelId}/upload" method = "post" enctype="multipart/form-data">
	<p>Please Select a file:<p>
	<input type = "file" name = "file"><br>
	<input type = "submit" value = "Upload"> 	
	<input type = "submit" value = "Skip">
	
	</form>
	
</body>
</html>