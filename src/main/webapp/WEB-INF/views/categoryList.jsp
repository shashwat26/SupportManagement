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
<title>Category List</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1 align="center">Support Category List</h1>
	<h2 align="center"><p style="color: red">${msg}</p></h2>
	<div align = "center">
	Add Support Category
		<button onclick="addCategory()">Add</button>
	</div>
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>Category Name</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categoryList}" var="category">
				<tr class="success">
					<td>${category.id}</td>
					<td>${category.categoryName}</td>
					<td>${category.categoryDescription}</td>
					<td><input type="submit" class="btn btn-success"
						onclick="editCategory(${category.id})" value="Edit">
						<input type="submit" class="btn btn-danger"
						onclick="confirmDelete(${category.id})" value="Delete">
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

	<script>
	
	function addCategory() {
		window.location = "${pageContext.request.contextPath}/addSupportCategory";
	}
	
	function editCategory(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/editSupportCategory";
	}
	
	function confirmDelete(id){
		if(confirm("Do you want to delete this category?")){
			deleteCategory(id);
		}
	}
	function deleteCategory(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/deleteCategory";	
	}
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
</body>
</html>