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
<title>Holiday List</title>
<%@include file="header.jsp"%>
</head>
<body>
<h1 align="center">Holiday List</h1>
<h2 align="center"><p style="color: red">${msg}</p></h2>
	<div align = "center">
	Add Holiday
		<button onclick="addHoliday()">Add</button>
	</div>
	<table id="myTable" class="table table-striped">
		<thead>
			<tr class="info">
				<th>S.N</th>
				<th>Department</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${holidaylist}" var="holiday">
				<tr class="success">
					<td>${holiday.id}</td>
					<td>${holiday.holiday}</td>
					<td>${holiday.description}</td>
					<td>
						<input type="submit" class="btn btn-danger"
						onclick="confirmDelete(${holiday.id})" value="Delete">
					</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>
<script>
function addHoliday() {
	window.location = "${pageContext.request.contextPath}/setHoliday";
}

function confirmDelete(id){
	if(confirm("Do you want to delete this Holiday?")){
		deleteHoliday(id);
	}
}
	
function deleteHoliday(id){
	window.location = "${pageContext.request.contextPath}/"+id+"/deleteHoliday";	
}

var addSerialNumber = function () {
	var i = 0;
    $('table tr').each(function(index) {
        $(this).find('td:nth-child(1)').html(index-1+1);
    });
};

addSerialNumber();

	$(document).ready(function() {
		$('#myTable').DataTable();
	});
</script>

</body>
</html>