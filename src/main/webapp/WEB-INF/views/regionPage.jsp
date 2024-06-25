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
</head>

<body>
<%@include file="headerChhimek.jsp"%>
	<div style="height:20px; width:100%; clear:both;"></div>
	<div align="center">
		<img alt="Chhimek" src="${pageContext.request.contextPath}/resources/imgs/logo.png" width="150px" height="150px">
		<h2>Chhimek Lagubitta Bittiya Sanstha<br> Support Page</h2>
	</div>
	<div style="height:20px; width:100%; clear:both;"></div>
	<div align="center" id="dashboard" >
		<h2><p align = "center" style="color: orange;">${catRegName}</p></h2>
		<div class="grid-container">
		<c:forEach var="person" items="${personList}">
			<div class="grid-item">
				${person.fname} ${person.lname}<br> <img
					alt="${person.photoName}"
					src="${pageContext.request.contextPath}/resources/imgs/${person.photoName}"
					width="200px" height="200px"><br>
					Mobile Number:${person.mobileNumber}<br>
					Email:${person.email}<br>
			</div>
		</c:forEach>
		</div>
	</div>

</body>
</html>