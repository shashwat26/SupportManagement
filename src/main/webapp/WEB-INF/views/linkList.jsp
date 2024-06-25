<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Quick Links</title>
<%@include file="header.jsp"%>
</head>
<body>
	<div align="center">
		<h1>Quick Link List</h1>
		<br> Add New Quick Link
		<button onclick="addquicklink()">Add</button>
		<h1><p style = "color: blue;">${msg}</p></h1>
	</div>
	
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>Link Name</th>
				<th>Link URL</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${linklist}" var="link">
				<tr class="success">
					<td>${link.id}</td>
					<td>${link.linkDescription}</td>
					<td>${link.linkUrl}</td>
					<td>
						<input type="submit" class="btn btn-danger" onclick="confirmDelete(${link.id})" value="Delete">
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>
	
	
	
	<script>
	function addquicklink(){
		window.location = "${pageContext.request.contextPath}/addquicklink";
	}
	
	function deletelink(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/deletelink";	
	}
	
	function confirmDelete(id){
		if(confirm("Do you want to delete this link?")){
			deletelink(id);
		}
	}
	
	$(document).ready(function() {
		$('#myTable').DataTable();
	});
	</script>
</body>
</html>