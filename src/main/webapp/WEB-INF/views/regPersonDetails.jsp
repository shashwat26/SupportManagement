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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.js"></script>
<meta charset="ISO-8859-1">
<title>Regional Personnel Details</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1 align = "center">Person Details for ${person.fname} ${person.lname}</h1>
	<div align = "center">
	<img alt="${person.photoName}"
						src="${pageContext.request.contextPath}/resources/imgs/${person.photoName}"
						width="200px" height="200px">
	</div>
	<div align = "center">
	<label>Name:   </label><b>${person.fname} ${person.lname}</b><br>
	<label>Mobile:   </label><b>${person.mobileNumber}</b><br>
	<label>Support Region:   </label><b>${person.region.region}</b><br>
	<label>Email: </label><b>${person.email}</b><br>
	<button onclick="goBack()">Go Back</button>
	</div>
	
	<%-- <div class="w3-bar w3-black">
  <button class="w3-bar-item w3-button" onclick="openHoliday()">Holiday</button>
  <button class="w3-bar-item w3-button" onclick="openLeaves()">Leave</button>
   <button class="w3-bar-item w3-button" onclick="openAddForm()">Add Leave</button>
	</div>
	
	<div id = "regholidaytable" class="w3-container city" style="display:block">
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>Holiday</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${holidayList}" var="holiday">
				<tr class="success">
					<td>${holiday.holiday}</td>
					<td>${holiday.description}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	</div>
	
	<div id = "regleavetable" class="w3-container city" style="display:none">
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>Leave Days</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leaveList}" var="leave">
				<tr class="success">
					<td>${leave.leaveDay}</td>
					<td>${leave.leavediscription}</td>
						<td><input type="submit" class="btn btn-danger" onclick="confirmDelete(${person.id},${leave.id})" value="Delete"></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	</div>
	
	<div id = "addleaveform" class="w3-container city" style="display:none">
	<form action="${pageContext.request.contextPath}/insertregLeaveByAdmin" method="post">
			<input type = "hidden" name = "regperson.id" value = "${person.id}">		
			<label>Add Holiday</label> <input type="Date" name="leaveDay" required><br> <label>Reason</label> <input
			type="text" name="leavediscription" required><br>
			<input type = "submit" value = "Enter">
			</form>
	</div> --%>
	
<script>
function goBack() {
	window.location = "${pageContext.request.contextPath}/listRegionPerson";
}

function openHoliday(){
	document.getElementById("regholidaytable").style.display = "block";
	document.getElementById("regleavetable").style.display = "none";
	document.getElementById("addleaveform").style.display = "none";
}

function openLeaves(){
	document.getElementById("regleavetable").style.display = "block";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("addleaveform").style.display = "none";
}

function openAddForm(){
	document.getElementById("addleaveform").style.display = "block";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("regleavetable").style.display = "none";
}

function deleteLeave(id, lid){
	window.location = "${pageContext.request.contextPath}/deleteRegLeave/"+id+"/"+lid;	
}


function confirmDelete(id,lid){
	if(confirm("Do you want to remove this leave entry?")){
		deleteLeave(id, lid);
	}
}


</script>
</body>
</html>