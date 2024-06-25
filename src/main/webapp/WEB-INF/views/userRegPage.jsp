<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
</head>
<body>
<%@include file="headerUser.jsp" %>
<h1 align = "center">Person Details for ${supportRegPerson.fname} ${supportRegPerson.lname}</h1>
	<div align = "center">
	<img alt="${supportRegPerson.photoName}"
						src="${pageContext.request.contextPath}/resources/imgs/${supportRegPerson.photoName}"
						width="200px" height="200px"></td>
	</div>
	<div align = "center">
	<p>${msg}</p>
	<label>Name:   </label><b>${supportRegPerson.fname} ${supportRegPerson.lname}</b><br>
	<label>Mobile:   </label><b>${supportRegPerson.mobileNumber}</b><br>
	<label>Support Category:   </label><b>${supportRegPerson.region.region}</b><br>
	<label>Day Start:      </label><b>${supportRegPerson.dayStartTime}:00</b><br>
	<label>Day End:      </label><b>${supportRegPerson.dayEndTime}:00</b><br>
	<button class = "btn-danger" onclick="editSupportRegPerson(${supportRegPerson.id})">Edit</button>
	<button class = "btn-primary" onclick="changeRegPhoto(${supportRegPerson.id})">Change Photo</button>
	<button class = "btn-success" onclick="changePassword(${supportRegPerson.id})">Change Password</button>
	
	</div>
	
	<div class="w3-bar w3-black">
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
					<td><input type="submit" class="btn btn-danger" onclick="confirmDelete(${supportRegPerson.id},${leave.id})" value="Delete"></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	</div>
	
	<div align = "center" id = "addleaveform" class="w3-container city" style="display:none">
	<form action="${pageContext.request.contextPath}/insertRegLeaveByUser" method="post">
			<input type = "hidden" name = "regperson.id" value = "${supportRegPerson.id}">		
			<label>Add Holiday</label> <input type="Date" name="leaveDay" required><br> <label>Reason</label> <input
			type="text" name="leavediscription" required><br>
			<input type = "submit" value = "Enter">
			</form>
	</div>
	
<script>

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

function editSupportRegPerson(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/editSupportRegPersonByUser";
}

function changeRegPhoto(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/changePhotoByRegUser";
}

function deleteRegLeave(id, lid){
	window.location = "${pageContext.request.contextPath}/deleteLeaveByRegUser/"+id+"/"+lid;
}

function changePassword(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/changeRegPasswordByUser";
}

function confirmDelete(id,lid){
	if(confirm("Do you want to remove this leave entry?")){
		deleteRegLeave(id, lid);
	}
}


</script>
</body>
</html>
</body>
</html>