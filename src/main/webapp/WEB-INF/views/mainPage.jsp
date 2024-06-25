<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chhimek Lagubitta Support Page</title>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"> 
	<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/styledropdown.css"> --%>
</head>

<body>
	<%-- <%@include file="headertwo.jsp"%> --%>
	<%@include file="headerChhimek.jsp"%>
	<div style="height:20px; width:100%; clear:both;"></div>
	<div align="center">
		<img alt="Chhimek"
			src="${pageContext.request.contextPath}/resources/imgs/logo.png"
			width="150px" height="150px">
		<h2>
			Chhimek Lagubitta Bittiya Sanstha<br> Support Page
		</h2>
		<a style="color: black; font-size: large;"
			href="https://chhimekbank.org/" target="_blank">Website:
			https://chhimekbank.org/</a>
		<div style="color: white">
			<br> Email: info@chhimek.org<br> Phone: +977-01-4490513 ,
			01-4464852
		</div>
	</div>
	<hr>

	<div class = "grid-containers">
	<div>
	<img alt="Chhimek"
			src="${pageContext.request.contextPath}/resources/imgs/corporate.jpg"
			width="800px" height="400px">
	</div>
	<div>
	<h3>Quick Links</h3>
	<c:forEach var="link" items="${linklist}">
	<a style="color:green;" href = "${link.linkUrl}" target="_blank">${link.linkDescription}</a><br>
	</c:forEach>
	</div>
	</div>

	

</body>
</html>