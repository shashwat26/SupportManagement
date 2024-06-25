<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.js"></script>	
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
		<h2>Chhimek Lagubitta Bittiya Sanstha<br> Support Page</h2><br>
		<h2><p style="color: yellow;">${msg}</p></h2>		
	</div>
	
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>Branch Name</th>
				<th>Address</th>
				<th>Manager</th>
				<th>Phone</th>
				<th>Region</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${branchList}" var="branch">
				<tr class="success">
					<td></td>
					<td>${branch.name}</td>
					<td>${branch.address}</td>
					<td>${branch.branchManager}</td>
					<td>${branch.phone}</td>
					<td>${branch.branchRegion.region}</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
		
		
		var addSerialNumber = function () {
			var i = 0;
		    $('table tr').each(function(index) {
		        $(this).find('td:nth-child(1)').html(index-1+1);
		    });
		};

		addSerialNumber();
		
		$('#myTable').dataTable({
			  'iDisplayLength': 100
			});
	</script>
</body>
</html>