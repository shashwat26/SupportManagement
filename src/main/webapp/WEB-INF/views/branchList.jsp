<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch List</title>
<%@include file="header.jsp"%>
</head>
<body>

<div align="center">
		<h1>Branch List</h1><br>
		<font style = "color: yellow;">Add New Branch</font>
		<button class = "btn btn-danger" onclick="addBranch()">Add</button><br>
		<font style = "color: orange;">Update With CSV</font>
		<button class = "btn btn-warning" onclick="updateWithCsv()">Update</button><br>
		<font style = "color: aqua;">Download Sample</font>
		<a class = "btn btn-info" href = "${pageContext.request.contextPath}/resources/files/sample.csv" download>Download</a>
		<h1><p style = "color: blue;">${msg}</p></h1>
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
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${branchList}" var="branch">
				<tr class="success">
					<td>${branch.id}</td>
					<td>${branch.name}</td>
					<td>${branch.address}</td>
					<td>${branch.branchManager}</td>
					<td>${branch.phone}</td>
					<td>${branch.branchRegion.region}</td>
					<td>
						<input type = "submit" class = "btn btn-success" onclick = "editBranch(${branch.id})" value = "Edit">
						<input type="submit" class="btn btn-danger" onclick="confirmDelete(${branch.id})" value="Delete">
						
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>
	<script>
	
	function addBranch(){
		window.location = "${pageContext.request.contextPath}/addBranch";
	}
	
	function updateWithCsv(){
		window.location = "${pageContext.request.contextPath}/updateWithCsv";
	}
	
	function editBranch(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/editBranch";
	}
	
	function deleteBranch(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/deleteBranch";
	}
	
	function confirmDelete(id){
		if(confirm("Do you want to delete this branch?")){
			deleteBranch(id);
		}
	}
	$(document).ready(function() {
		$('#myTable').DataTable();
	});
	
	</script>
		
</body>

</html>