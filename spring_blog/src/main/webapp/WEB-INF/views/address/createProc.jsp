<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html> 
 
<html> 
 
<head> 
 
<meta charset="UTF-8"> 
 
<title></title> 
 
<style type="text/css"> 
 
*{ 
 
  font-family: gulim; 
 
  font-size: 20px; 
 
} 
 
</style> 
 
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 

<!-- *********************************************** -->

<body>



<!-- *********************************************** -->
 
 
 
<DIV class="title">CreateProc</DIV>
<div class="content"> 
<c:choose>
<c:when test="${flag }">성공
<div class="bottom">
 
    <input type='button' value='목록' onclick="location.href='list.do'">
 
  </DIV>
  </c:when>
<c:otherwise>실패
     <input type='button' value='목록' onclick="location.href='list.do'">
     <input type='button' value='다시시도' onclick="history.back()">
</c:otherwise>
 </c:choose>
 </div>
 

 


<!-- *********************************************** -->


</body>

<!-- *********************************************** -->
 
</html> 
 