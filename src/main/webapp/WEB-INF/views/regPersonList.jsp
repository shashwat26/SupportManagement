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
<title>Regional Person List</title>
</head>
<body>
<%@include file="header.jsp"%>
<div align = "center">
<h2>Regional Person List</h2><br>
<h2 align="center"><p style="color: red">${msg}</p></h2>
Add New Regional Support Person
<button onclick="addRegSupportPerson()">Add</button>

</div>

<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Mobile Number</th>
				<th>Support Region</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personList}" var="person">
				<tr class="success">
					 <td><img alt="${person.photoName}"
						src="${pageContext.request.contextPath}/resources/imgs/${person.photoName}"
						width="20px" height="20px"></td> 
					<td>${person.fname}</td>
					<td>${person.lname}</td>
					<td>${person.mobileNumber}</td>
					<td>${person.region.region}</td>
					<td>
					    <input type="submit" class="btn btn-info" onclick="showRegPerson(${person.id})" value="Show">
						<input type="submit" class="btn btn-success" onclick="editRegPerson(${person.id})" value="Edit">
						<input type="submit" class="btn btn-danger" onclick="confirmDelete(${person.id})" value="Delete"> 
						<input type="submit" class="btn btn-primary" onclick="changePhoto(${person.id})" value ="Change Photo">
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

<script type="text/javascript">
	function addRegSupportPerson(){
		window.location = "${pageContext.request.contextPath}/addRegionPerson";
	}
	
	function showRegPerson(id){
		window.location = "${pageContext.request.contextPath}/" + id + "/showRegionPerson";
	}
	
	function editRegPerson(id){
		window.location = "${pageContext.request.contextPath}/" + id + "/editRegionPerson";
	}
	
	function deleteRegPerson(id){
		window.location = "${pageContext.request.contextPath}/" + id + "/deleteRegionPerson";
	}
	
	function changePhoto(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/changeRegPhoto";
	}
	function changePassword(id){
		window.location = "${pageContext.request.contextPath}/"+id+"/changeRegPasswordByAdmin";
	}
	function confirmDelete(id){
		if(confirm("Do you want to delete this person?")){
			deleteRegPerson(id);
		}
	}
	

	
	$(document).ready(function() {
		$('#myTable').DataTable();
	});

</script>


</body>
</html>