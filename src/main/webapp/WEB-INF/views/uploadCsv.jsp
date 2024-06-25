<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload CSV File</title>
<%@include file="header.jsp"%>
</head>
<body>
<p style="color: red;">${msg}</p>
<form action="${pageContext.request.contextPath}/updateBranchByCsv" method = "post" enctype="multipart/form-data">
	<p>Please Select a csv file:<p>
	<input type = "file" name = "file" required><br>
	<input type = "submit" value = "Upload"> 	
	
	
	</form>

</body>
</html>