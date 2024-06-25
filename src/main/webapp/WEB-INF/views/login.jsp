<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type = "text/css"
	href="${pageContext.request.contextPath}/resources/css/stylelogin.css">
<meta charset="ISO-8859-1">
<title>Login Here</title>
</head>
<body>

 <div align = "center">
 <img alt="Chhimek" src="${pageContext.request.contextPath}/resources/imgs/logo.png" width="150px" height="150px">
 <h1>Chhimek Lagubitta Login</h1>
 </div>
 <div class="login">
  <div class="login-triangle"></div>
  <h2 class="login-header">Log in</h2>

  <form action = "${pageContext.request.contextPath}/userlogin" method = "post" class="login-container">
  
    <p style="color: red">${error}</p>
    
    <p><input type="text" placeholder="Username" name = "username"></p>
    <p><input type="password" placeholder="Password" name = "password"></p>
    <p><input type="submit" value="Log in"></p>
  </form>
</div>
<script type="text/javascript">
	function  signup() {
		window.location = "${pageContext.request.contextPath}/signup";
		
	}
</script>
</body>
</html>