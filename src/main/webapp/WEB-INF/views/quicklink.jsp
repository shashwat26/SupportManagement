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
<%@include file="headertwo.jsp"%>
</head>
<body>
	<div align="center">
		<img alt="Chhimek"
			src="${pageContext.request.contextPath}/resources/imgs/logo.png"
			width="150px" height="150px">
		<h2>
			Chhimek Lagubitta Bittiya Sanstha<br> Support Page
		</h2>
	</div>
	
	<div align="center" id="dashboard" >
		<div class="grid-container">
		<c:forEach var="link" items="${linklist}">
			<div class="grid-item">
				<p><a href = "https://${link.linkUrl}" target="_blank">${link.linkDescription}</a></p>
				${link.linkUrl}
			</div>
		</c:forEach>
		</div>
	</div>

</body>
</html>