<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/styledropdown.css">
</head>
<body>
    <div class="menu-bar">
      <h1 class="logo"><a href = "${pageContext.request.contextPath}/support">ChhimekSupport</a></h1>
      <ul>
      <li><a href="${pageContext.request.contextPath}/">Dashboard</a></li>
        <li><a href="#">Head Office <i class="fas fa-caret-down"></i></a>

            <div class="dropdown-menu">
                <ul>
                <c:forEach var = "department" items = "${departmentList}">
                	<div id = "departmentId" onmouseover="checkIT(${department.id})" onmouseout="removeIT()"><li><a href = "${pageContext.request.contextPath}/${department.id}/getByDepartment">${department.name}<i class="fas fa-caret-right"></i></a>
                	<div class="dropdown-menu-1" id = "dropdown-menu-1">
                      <ul>
                      <c:forEach var = "category" items = "${categoryList}">
                      	<li><a href="${pageContext.request.contextPath}/${category.id}/getByCategory">${category.categoryName}</a></li>
                      </c:forEach>
                      </ul>
                    </div></li></div>
                </c:forEach>
                
                </ul>
              </div>
        </li>
        <li><a href ="${pageContext.request.contextPath}/listAllBranches">Branches<i class="fas fa-caret-down"></i></a>
        <div class = "dropdown-menu">
        <ul>
        	 <c:forEach var = "region" items = "${regionList}">
                	<li><a href="${pageContext.request.contextPath}/${region.id}/getBranchByRegion">${region.region}</a></li>
             </c:forEach>
        </ul>
        </div>
        </li>  
      	<li><a href="${pageContext.request.contextPath}/supportPageRg">Region<i class="fas fa-caret-down"></i></a>
      		 <div class="dropdown-menu">
                <ul>
                <c:forEach var = "region" items = "${regionList}">
                	<li><a href="${pageContext.request.contextPath}/${region.id}/getByRegion">${region.region}</a></li>
                </c:forEach>
                  <%-- <li><a href="${pageContext.request.contextPath}/4/getByRegion">East Region</a></li>
                  <li><a href="${pageContext.request.contextPath}/3/getByRegion">Mid Region</a></li>
                  <li><a href="${pageContext.request.contextPath}/5/getByRegion">Kathmandu</a></li>
                  <li><a href="${pageContext.request.contextPath}/2/getByRegion">West Region</a></li>
                   <li><a href="${pageContext.request.contextPath}/1/getByRegion">Far West Region</a></li>  --%>
                </ul>
              </div>
      	
      	</li>
        <li><a href="${pageContext.request.contextPath}/userlogin">Sign In</a></li>
      </ul>
    </div>

    <script>

     function checkIT(id){
    	 if(id == 2){
    		 document.getElementById("dropdown-menu-1").style.display = "block";
    	 } else {
    		 document.getElementById("dropdown-menu-1").style.display = "none";
    	 }
     }
     
     function removeIT(){ 
    	document.getElementById("dropdown-menu-1").style.display = "none";	 
     }
    </script>
  </body>
</html>