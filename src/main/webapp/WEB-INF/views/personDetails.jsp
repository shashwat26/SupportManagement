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
<title>Personnel Details</title>
</head>
	
<body>
	<%@include file="header.jsp" %>
	<h1 align = "center">Person Details for ${person.fname} ${person.lname}</h1>
	<div align = "center">
	<img alt="${imageName}"
						src="${pageContext.request.contextPath}/resources/imgs/${person.photoName}"
						width="200px" height="200px">
	</div>
	<div align = "center">
	<p>${success}</p>
	<label>Name:   </label><b>${person.fname} ${person.lname}</b><br>
	<label>Mobile:   </label><b>${person.mobileNumber}</b><br>
	<label>Email: </label><b>${person.email}</b><br>
	<label>Support Category:   </label><b>${person.supportcategory.categoryName}</b><br>
	<label>Department: </label><b>${person.department.name}</b><br>
	<label>Day Start at:</label><b>${person.dayStartTime}</b><br>
	<label>Day End at:</label><b>${person.dayEndTime}</b><br>
	<c:choose>
		<c:when test="${person.workingFromHome == true}">
			<p style="color:red;">${person.fname} ${person.lname} is Working from home</p>
		</c:when>		
	</c:choose>
	<button onclick="goBack()">Go Back</button>
	<button onclick="changePassword(${user.id})">Change Password</button>
	</div>
	
	 <div class="w3-bar w3-black">
  <button class="w3-bar-item w3-button" onclick="openHoliday()">Holiday</button>
  <button class="w3-bar-item w3-button" onclick="openLeaves()">Leave</button>
  <button class="w3-bar-item w3-button" onclick="openAddForm()">Add Leave</button>
  <button class="w3-bar-item w3-button" onclick="openWorkFromHome()">Work from Home</button>
  <button class="w3-bar-item w3-button" onclick="addExchangeLeaveForm()">Add Exchange Leave</button>	
  <button class="w3-bar-item w3-button" onclick="listExchangeLeave()">List Exchange Leave</button>	
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
					<td><input type="submit" class="btn btn-danger" onclick="confirmDelete(${person.personnelId},${leave.id})" value="Delete"></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	</div>
	
	<div id = "addleaveform" class="w3-container city" style="display:none">
	<form action="${pageContext.request.contextPath}/insertLeaveByAdmin" method="post">
			<input type = "hidden" name = "person.personnelId" value = "${person.personnelId}">		
			<label>Add Holiday</label> <input type="Date" name="leaveDay" required><br> <label>Reason</label> <input
			type="text" name="leavediscription" required><br>
			<input type = "submit" value = "Enter">
			</form>
	</div>
	
	<div align = "center" id = "addWorkfromHome" class = "w3-container city" style="display: none">
		<label>Add work from home status for ${person.fname} ${person.lname} ?</label>
		<button class = "btn-primary" onclick="addWFHAdmin(${person.personnelId})">Yes</button><br>
		<label>Remove work from home status for ${person.fname} ${person.lname} ?</label>
		<button class = "btn-danger" onclick="removeWFHAdmin(${person.personnelId})">Yes</button><br>
	</div>
	
	<div align="center" id="addExchangeleaveform" class="w3-container city" style="display: none">
		<form action="${pageContext.request.contextPath}/insertExchangeLeaveByAdmin" method="post">
			<input type="hidden" name="personnel.personnelId"
				value="${person.personnelId}" required> <label>Add Exchange Leave</label> <input
				type="Date" name="exchangeLeaveDate" required><br> <label>Reason</label>
			<input type="text" name="description" required><br> <input
				type="submit" value="Enter">
		</form>
	</div>
	
	 <div id="exchangeleavetable" class="w3-container city" style="display: none">
		<table id="myTable" class="table table-striped">
			<thead>
				<tr class="info">
					<th>Leave Days</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${exchangeLeaveList}" var="leave">
					<tr class="success">
						<td>${leave.exchangeLeaveDate}</td>
						<td>${leave.description}</td>
						<td><input type="submit" class="btn btn-danger" onclick="confirmDeleteExchange(${person.personnelId},${leave.id})" value="Delete"></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div> 
	
	

<script>
function goBack() {
	window.location = "${pageContext.request.contextPath}/listSupportPerson";
}

function changePassword(id){
	window.location = "${pageContext.request.contextPath}/"+id+ "/changePasswordByAdmin";
}

function openHoliday(){
	document.getElementById("regholidaytable").style.display = "block";
	document.getElementById("regleavetable").style.display = "none";
	document.getElementById("addleaveform").style.display = "none";
	document.getElementById("addWorkfromHome").style.display = "none";
	document.getElementById("addExchangeleaveform").style.display = "none";
	document.getElementById("exchangeleavetable").style.display = "none";
}

function openLeaves(){
	document.getElementById("regleavetable").style.display = "block";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("addleaveform").style.display = "none";
	document.getElementById("addWorkfromHome").style.display = "none";
	document.getElementById("addExchangeleaveform").style.display = "none";
	document.getElementById("exchangeleavetable").style.display = "none";
}

function openAddForm(){
	document.getElementById("addleaveform").style.display = "block";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("regleavetable").style.display = "none";
	document.getElementById("addWorkfromHome").style.display = "none";
	document.getElementById("addExchangeleaveform").style.display = "none";
	document.getElementById("exchangeleavetable").style.display = "none";
}	

function openWorkFromHome(){
	document.getElementById("addleaveform").style.display = "none";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("regleavetable").style.display = "none";
	document.getElementById("addWorkfromHome").style.display = "block";
	document.getElementById("addExchangeleaveform").style.display = "none";
	document.getElementById("exchangeleavetable").style.display = "none";
}

function addExchangeLeaveForm(){
	document.getElementById("addleaveform").style.display = "none";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("regleavetable").style.display = "none";
	document.getElementById("addWorkfromHome").style.display = "none";
	document.getElementById("addExchangeleaveform").style.display = "block";
	document.getElementById("exchangeleavetable").style.display = "none";
}

function listExchangeLeave(){
	document.getElementById("addleaveform").style.display = "none";
	document.getElementById("regholidaytable").style.display = "none";
	document.getElementById("regleavetable").style.display = "none";
	document.getElementById("addWorkfromHome").style.display = "none";
	document.getElementById("addExchangeleaveform").style.display = "none";
	document.getElementById("exchangeleavetable").style.display = "block";
}

function deleteLeave(id, lid){
	window.location = "${pageContext.request.contextPath}/deleteLeave/"+id+"/"+lid;	
}

function confirmDelete(id,lid){
	if(confirm("Do you want to remove this leave entry?")){
		deleteLeave(id, lid);
	}
}

function deleteExchangeLeave(id, lid){
	window.location = "${pageContext.request.contextPath}/deleteExchangeLeaveByAdmin/"+id+"/"+lid;	
}

function confirmDeleteExchange(id,elid){
	if(confirm("Do you want to remove this Exchange leave entry?")){
		deleteExchangeLeave(id, elid);
	}
}

function addWFHAdmin(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/insertWorkFromHomeByAdmin";
}

function removeWFHAdmin(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/removeWorkFromHomeByAdmin";
}


</script>
</body>
</html>