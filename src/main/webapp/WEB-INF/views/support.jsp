<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chhimek Lagubitta Support Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
<%@include file="headerChhimek.jsp"%>
	<div style="height:20px; width:100%; clear:both;"></div>
	<div align="center">
		<img alt="Chhimek" src="${pageContext.request.contextPath}/resources/imgs/logo.png" width="150px" height="150px">
		<h2>Chhimek Lagubitta Bittiya Sanstha<br> Support Page</h2>
	</div>


 	<div align = "center">
		Filter By:<br>
		Department<input type = "checkbox" id = "cfil" onchange = "catfilter()"><br>
		Region<input type = "checkbox" id = "rfil" onchange = "regfilter()">
	</div> 
	
	
	<div align="center" id = "cselect">
		<select onchange="departmentFilter()" id="depSelect">
			<option>---Select Department---</option>
			<c:forEach var="department" items="${departmentList}">
				<option value="${department.id}">${department.name}</option>
			</c:forEach>
		</select>
	</div><br>
	
	<div align="center" id = "rselect">
		<select onchange="regionFilter()" id="regSelect">
			<option>---Select Region---</option>
			<c:forEach var="region" items="${regionList}">
				<option value="${region.id}">${region.region}</option>
			</c:forEach>
		</select>
	</div>
	


	<div align="center" id="dashboard" >
		<h3>${catRegName}</h3>
		<div class="grid-container">
		<c:forEach var="person" items="${personList}">
			<div class="grid-item">
				${person.fname} ${person.lname}<br> <img
					alt="${person.photoName}"
					src="${pageContext.request.contextPath}/resources/imgs/${person.photoName}"
					width="200px" height="200px"><br>
					Mobile Number:${person.mobileNumber}<br>
					Email : ${person.email}<br>
					
					<%-- <c:choose>
						<c:when test="${person.present == true }">
							<p style="color:blue;">Present in Office</p>
						</c:when>
						<c:otherwise>
							<p style = "color:red">Not in Office</p>
						</c:otherwise>
					</c:choose> --%>
					
					
			</div>
		</c:forEach>
		</div>
	</div>

	<!-- <input type = "checkbox" class = "present" id = "present" onchange="presentFilter()"> -->
	<script type="text/javascript">
		var status = ${status};
		document.getElementById("dashboard").style.display = "none";
		if (status != 0) {
			document.getElementById("dashboard").style.display = "block";
		}

		function departmentFilter() {
			var id = document.getElementById("depSelect").value;
			window.location = "${pageContext.request.contextPath}/" + id
					+ "/getDepartment";
		}
		
		function regionFilter(){
			var id = document.getElementById("regSelect").value;
			window.location = "${pageContext.request.contextPath}/" + id +"/getRegion";
		}
		
	 	document.getElementById("cselect").style.display = "none";
		document.getElementById("rselect").style.display = "none";

		function catfilter(){
			if(document.getElementById("cfil").checked){
				document.getElementById("cselect").style.display = "block";
				document.getElementById("rselect").style.display = "none";
				document.getElementById("rfil").checked = false;
			}
		}
		function regfilter(){
			if(document.getElementById("rfil").checked){
				document.getElementById("rselect").style.display = "block";
				document.getElementById("cselect").style.display = "none";
				document.getElementById("cfil").checked = false;
			} 
		} 
		
		
		
		/* function presentFilter() {

			if (document.getElementById("present").checked) {
				document.getElementById("demo").style.display = "block";
			} else {
				document.getElementById("demo").style.display = "none";
			}
		}
 */
		//document.getElementById("cbone").style.display = "none";
		/* if(${msg}.equals(null)){
		 document.getElementById("cbone").style.display = "none";
		 } */
		/* document.getElementById("demo").style.display = "none"; */
		//document.getElementById("works").style.display = "none";
		/* if($('.present').is(":checked"))   
		    $(".demo").show();
		else
		    $(".demo").hide(); */
	</script>

</body>
</html>