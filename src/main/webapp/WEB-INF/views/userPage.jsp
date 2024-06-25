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
	<%@include file="headerUser.jsp"%>
	<h3 align="center">Details for ${supportPerson.fname}
		${supportPerson.lname}</h3>
	<div align="center">
		<img alt="${supportPerson.photoName}"
			src="${pageContext.request.contextPath}/resources/imgs/${supportPerson.photoName}"
			width="200px" height="200px">
	</div>
	<div align="center">
		<p style= "color: blue">${success}</p>
		<p style= "color: green">${msg}</p>
		<label>Name: </label><b>${supportPerson.fname} ${supportPerson.lname}</b><br> <label>Mobile:
		</label><b>${supportPerson.mobileNumber}</b><br><label>Email: </label><b>${supportPerson.email}</b><br> <label>Support
			Category: </label><b>${supportPerson.supportcategory.categoryName}</b><br>
			<label>Department: </label><b>${supportPerson.department.name}</b><br>
			 <label>Day
			Start At: </label><b>${supportPerson.dayStartTime}:00</b><br> <label>Day
			Start At: </label><b>${supportPerson.dayEndTime}:00</b><br>
			<c:choose>
			<c:when test="${supportPerson.workingFromHome == true}">
				<p style="color:red;">Working from home</p>
			</c:when>		
			</c:choose>
			<button class = "btn-danger" onclick="editSupportPerson(${supportPerson.personnelId})">Edit</button>
			<button class = "btn-primary" onclick="changePhoto(${supportPerson.personnelId})">Change Photo</button>
			<button class = "btn-success" onclick="changePassword(${supportPerson.personnelId})">Change Password</button>
	</div>

	<div class="w3-bar w3-black">
		<button class="w3-bar-item w3-button" onclick="openHoliday()">Holiday</button>
		<button class="w3-bar-item w3-button" onclick="openLeaves()">Leave</button>
		<button class="w3-bar-item w3-button" onclick="openAddForm()">Add
			Leave</button>
		<button class="w3-bar-item w3-button" onclick="openWorkFromHome()">Work from Home</button>
		<button class="w3-bar-item w3-button" onclick="addExchangeLeaveForm()">Add Exchange Leave</button>	
		<button class="w3-bar-item w3-button" onclick="listExchangeLeave()">List Exchange Leave</button>	
		
	</div>

	<div id="regholidaytable" class="w3-container city"
		style="display: block">
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

	<div id="regleavetable" class="w3-container city" style="display: none">
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
						<td><input type="submit" class="btn btn-danger" onclick="confirmDelete(${supportPerson.personnelId},${leave.id})" value="Delete"></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

	<div align="center" id="addleaveform" class="w3-container city" style="display: none">
		<form action="${pageContext.request.contextPath}/insertLeaveByUser" method="post">
			<input type="hidden" name="person.personnelId"
				value="${supportPerson.personnelId}" required> <label>Add Leave</label> <input
				type="Date" name="leaveDay" required><br> <label>Reason</label>
			<input type="text" name="leavediscription" required><br> <input
				type="submit" value="Enter">
		</form>
	</div>
	
	<div align = "center" id = "addWorkfromHome" class = "w3-container city" style="display: none">
		<label>Are you working from home today?</label>
		<button class = "btn-primary" onclick="addWFH(${supportPerson.personnelId})">Yes</button><br>
		<label>Do you want to remove work from home status?</label>
		<button class = "btn-danger" onclick="removeWFH(${supportPerson.personnelId})">Yes</button><br>
	</div>
	
	<div align="center" id="addExchangeleaveform" class="w3-container city" style="display: none">
		<form action="${pageContext.request.contextPath}/insertExchangeLeaveByUser" method="post">
			<input type="hidden" name="personnel.personnelId"
				value="${supportPerson.personnelId}" required> <label>Add Exchange Leave</label> <input
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
						<td><input type="submit" class="btn btn-danger" onclick="confirmDeleteExchange(${supportPerson.personnelId},${leave.id})" value="Delete"></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div> 
	
	


	<script>
		function openHoliday() {
			document.getElementById("regholidaytable").style.display = "block";
			document.getElementById("regleavetable").style.display = "none";
			document.getElementById("addleaveform").style.display = "none";
			document.getElementById("addWorkfromHome").style.display = "none";
			document.getElementById("addExchangeleaveform").style.display = "none";
			document.getElementById("exchangeleavetable").style.display = "none";
		}

		function openLeaves() {
			document.getElementById("regleavetable").style.display = "block";
			document.getElementById("regholidaytable").style.display = "none";
			document.getElementById("addleaveform").style.display = "none";
			document.getElementById("addWorkfromHome").style.display = "none";
			document.getElementById("addExchangeleaveform").style.display = "none";
			document.getElementById("exchangeleavetable").style.display = "none";
		}

		function openAddForm() {
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
		
		function editSupportPerson(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/editSupportPersonByUser";
		}
		
		function changePhoto(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/changePhotoByUser";
		}
		
		function deleteLeave(id, lid){
			window.location = "${pageContext.request.contextPath}/deleteLeaveByUser/"+id+"/"+lid;	
		}
		function deleteExchangeLeave(id, lid){
			window.location = "${pageContext.request.contextPath}/deleteExchangeLeaveByUser/"+id+"/"+lid;	
		}
		function changePassword(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/changePasswordByUser";
		}
		function confirmDelete(id,lid){
			if(confirm("Do you want to remove this leave entry?")){
				deleteLeave(id, lid);
			}
		}
		
		function confirmDeleteExchange(id,elid){
			if(confirm("Do you want to remove this Exchange leave entry?")){
				deleteExchangeLeave(id, elid);
			}
		}
		
		function addWFH(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/insertWorkFromHome";
		}
		
		function removeWFH(id){
			window.location = "${pageContext.request.contextPath}/"+id+"/removeWorkFromHome";
		}
	</script>
</body>
</html>