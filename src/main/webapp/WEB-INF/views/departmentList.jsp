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
<title>Department List</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align="center">Department List</h1>
	<h2 align="center"><p style="color: red">${msg}</p></h2>
	<div align = "center">
	Add Department
		<button onclick="addDepartment()">Add</button>
	</div>
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>Department</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${departmentList}" var="department">
				<tr class="success">
					<td>${department.id}</td>
					<td>${department.name}</td>
					<td>${department.description}</td>
					<td><input type="submit" class="btn btn-success"
						onclick="editDepartment(${department.id})" value="Edit">
						<input type="submit" class="btn btn-danger"
						onclick="confirmDelete(${department.id})" value="Delete">
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

<script>
function addDepartment() {
	window.location = "${pageContext.request.contextPath}/adddepartment";
}

function editDepartment(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/editDepartment";
}

function confirmDelete(id){
	if(confirm("Do you want to delete this Department?")){
		deleteDepartment(id);
	}
}
function deleteDepartment(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/deleteDepartment";	
}

$(document).ready(function() {
	$('#myTable').DataTable();
});
</script>
</body>
</html>