<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Leave Days Here</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1>Please select Leave days</h1>
	<p>${success}</p>
	
	
		<label>Select Staff</label> 
		<div>
			Select:<br>
			HeadOffice<input type = "checkbox" id = "sfil" onchange = "headofficefilter()"><br>
			Region<input type = "checkbox" id = "rfil" onchange = "regionalfilter()">
		</div>
		<div id = "sselect"> 
		<form action="insertLeave" method="post">

		<select name="person.personnelId" required>
			<c:forEach var="person" items="${personList}">
				<option value="${person.personnelId}">${person.fname} ${person.lname}</option>
			</c:forEach>
		</select><br>
		
			<label>Add Holiday</label> <input type="Date" name="leaveDay" required><br> <label>Reason</label> <input
			type="text" name="leavediscription" required><br>
			<input type = "submit" value = "Enter">
			</form>
		</div>
		
	
		
		<div id = "rselect">
		<form action="insertregLeave" method="post">
		<select name = "regperson.id" required>
			<c:forEach var = "regperson" items = "${regPersonList}">
				<option value = "${regperson.id}">${regperson.fname} ${regperson.lname}</option>
			</c:forEach>
		</select>
		<label>Add Holiday</label> <input type="Date" name="leaveDay" required><br> <label>Reason</label> <input
			type="text" name="leavediscription" required><br>
			<input type = "submit" value = "Enter">
		</form>
		</div>
		 
	<a href="dashboard">Go To Dashboard</a>
	
	<script type="text/javascript">
	
	document.getElementById("sselect").style.display = "none";
	document.getElementById("rselect").style.display = "none";
	
	function headofficefilter(){
		if(document.getElementById("sfil").checked){
			document.getElementById("sselect").style.display = "block";
			document.getElementById("rselect").style.display = "none";
			document.getElementById("rfil").checked = false;
			
			
		}
	}
	function regionalfilter(){
		if(document.getElementById("rfil").checked){
			document.getElementById("rselect").style.display = "block";
			document.getElementById("sselect").style.display = "none";
			document.getElementById("sfil").checked = false;
			
		} 
	} 
	</script>
</body>
</html>