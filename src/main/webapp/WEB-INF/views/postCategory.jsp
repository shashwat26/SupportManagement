<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Added</title>
</head>
<body>

<%@include file="header.jsp"%>
		<h1 align = "center">You added a category</h1>
		<h2>${categoryName}</h2>
		<h2>${categoryDescription}</h2><br>
		<button onclick="goBack()">Go Back</button>
</body>

<script>
function goBack() {
	window.location = "${pageContext.request.contextPath}/listSupportCategory"
}
</script>
</html>