<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 
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

 
<DIV class="title">로그인처리</DIV>
<div class="content">
<c:choose>
<c:when test="${flag }">로그인되었습니다.</c:when>
<c:otherwise>아이디 또는 비밀번호가 틀립니다.</c:otherwise>
</c:choose>
</div> 

  <DIV class='bottom'>
    <input type='button' value='홈' onclick="location.href='${pageContext.request.contextPath }/'">
    <input type='button' value='돌아가기' onclick="history.back()">
  </DIV>

</body>
<!-- *********************************************** -->
</html> 