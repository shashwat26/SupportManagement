<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Photo</title>
</head>
<body>
	<h1 align="center">
		You are editing: <br> ${supportperson.fname} ${supporperson.lname}
	</h1>

	<form action="updateRegPhoto" method="post" enctype="multipart/form-data">
		<p>Please Select a file:
		<p>
			<input type="file" name="file"><br> <input type="submit" value="Upload"> 
	</form>

</body>
</html>