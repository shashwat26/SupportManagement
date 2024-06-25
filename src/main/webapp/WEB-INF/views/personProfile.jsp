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
<title>Personnel Profile</title>
</head>
<body>
<%@include file="header.jsp"%>
	<div align="center">
		<h3>${msg}</h3>
		<a
			href="${pageContext.request.contextPath}/resources/imgs/${imageName}"
			target="_blank"> <img alt="${imageName}"
			src="${pageContext.request.contextPath}/resources/imgs/${imageName}"
			width="200px" height="200px">
		</a> <br> Data Uploaded of: ${fname} ${lname} <br> Add New
		Support Person
		<button onclick="addSupportPerson()">Add</button>
	</div>



	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Mobile Number</th>
				<th>Email</th>
				<th>Support Category</th>
				<th>Department</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personList}" var="person">
				<tr class="success">
					<td><img alt="${imageName}"
						src="${pageContext.request.contextPath}/resources/imgs/${person.photoName}"
						width="20px" height="20px"></td>
					<td>${person.fname}</td>
					<td>${person.lname}</td>
					<td>${person.mobileNumber}</td>
					<td>${person.email}</td>
					<td>${person.supportcategory.categoryName}</td>
					<td>${person.department.name}</td>
					<td>
						<input type="submit" class="btn btn-info" onclick="showPerson(${person.personnelId})" value="Show">
						<input type="submit" class="btn btn-success" onclick="editPerson(${person.personnelId})" value="Edit">
						<input type="submit" class="btn btn-danger" onclick="confirmDelete(${person.personnelId})" value="Delete">
						<input type="submit" class="btn btn-primary" onclick="changePhoto(${person.personnelId})" value ="Change Photo">
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

	<script>
		function addSupportPerson() {
			window.location = "${pageContext.request.contextPath}/addSupportPerson";
		}
		function editPerson(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/editSupportPerson";
		}
		function showPerson(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/showSupportPerson";
		}
		function deletePerson(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/deletePerson";	
		}
		function changePhoto(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/changePhoto";
		}
		function confirmDelete(id){
			if(confirm("Do you want to delete this person?")){
				deletePerson(id);
			}
		}
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
</body>
</html>