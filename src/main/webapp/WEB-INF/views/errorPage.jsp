<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<div align="center">
		<h1>${errorMsg}</h1><br>
			<img alt="Chhimek" src="${pageContext.request.contextPath}/resources/imgs/404.png" width="300px" height="300px"><br>
	
		<button onclick="gotoMain()">Go to App</button>
</div>


<script>
	function gotoMain(){
		window.location = "${pageContext.request.contextPath}/";
	}

</script>
	
</body>
</html>

