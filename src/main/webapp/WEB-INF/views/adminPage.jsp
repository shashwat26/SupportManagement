<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
	<%@include file = "header.jsp" %>

	
	<div align="center">
		<img alt="Chhimek" src="${pageContext.request.contextPath}/resources/imgs/logo.png" width="150px" height="150px">
		<h2 style="color: blue">Chhimek Lagubitta Bittiya Sanstha</h2>
	</div>
	<h2 align="center">Administrator Dashboard</h2>	
	<div align = "center" class = "grid-container">
	
	<div class = "grid-item">
	Today's date is: ${date}<br>
	The time now is: ${time}<br>
	Go to Support Page<br>
	<input type = "submit" value = GotoSupport onclick="gotoSupport()"><br>
	<hr>
	Change Admin Password<br>
	<input type = "submit" value = "Change Password" onclick = "changeAdminPassword()">
	</div>
	<div  class = "grid-item">
	<a href = "${pageContext.request.contextPath}/addSupportPerson">Add Support Personnel</a><br>
	<a href = "${pageContext.request.contextPath}/listSupportPerson">List Support Personnel</a><br>
	<a href = "${pageContext.request.contextPath}/addSupportCategory">Add Support Category</a><br>
	<a href = "${pageContext.request.contextPath}/listSupportCategory">List Support Category</a><br>
	<a href = "${pageContext.request.contextPath}/addRegionPerson">Add Regional Personnel</a><br>
	<a href = "${pageContext.request.contextPath}/listRegionPerson">List Regional Personnel</a><br>
	<a href = "${pageContext.request.contextPath}/setHoliday">Set Holidays</a><br>
	<a href = "${pageContext.request.contextPath}/listholidays">List Holidays</a><br>
	<a href = "${pageContext.request.contextPath}/setLeave">Set Leave</a><br>
	<a href = "${pageContext.request.contextPath}/addBranch">Add Branch</a><br>
	<a href = "${pageContext.request.contextPath}/listBranch">List Branch</a><br>
	<a href = "${pageContext.request.contextPath}/addquicklink">Add QuickLinks</a><br>
	<a href = "${pageContext.request.contextPath}/listquicklink">List QuickLinks</a><br>
	<a href = "${pageContext.request.contextPath}/adddepartment">Add Department</a><br>
	<a href = "${pageContext.request.contextPath}/listDepartment">List Department</a><br>
<%-- 	<a href = "${pageContext.request.contextPath}/${userId}/changeAdminPassword">Change Admin Password</a><br> --%>
	
	</div>
	</div>
	
	<script type="text/javascript">
		function gotoSupport(){
			window.location = "${pageContext.request.contextPath}/";
		}
		
		function changeAdminPassword(){
			window.location = "${pageContext.request.contextPath}/" + ${userId} + "/changeAdminPassword";
		  	 /*  <li><a href="${pageContext.request.contextPath}/${userId}/changeAdminPassword">Change Password<span class="sr-only">(current)</span></a></li> */
		}
	</script>
</body>
</html>