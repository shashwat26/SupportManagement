<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Link</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
	<%@include file = "header.jsp" %>
</head>
<body>
<p>Add Quick Links</p> 
<form action="postquicklink" method="post">
<label>Link Name</label>
<input type = "text" name = "linkDescription" required><br>
<label>Link Url</label>
<input type = "text" name = "linkUrl" required><br>
<input type = "submit" value = "Enter">
</form>
</body>
</html>