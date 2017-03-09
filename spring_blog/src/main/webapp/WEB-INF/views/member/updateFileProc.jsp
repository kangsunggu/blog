<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function read(){
	var url="read";
	url+="?id=${id}";
	
	location.href=url;
	
}

</script>
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

 
<DIV class="title">사진변경</DIV>
 <div class="content">
 <c:choose>
 <c:when test="${flag }">${param.oldfile }</c:when>
 <c:otherwise>사진변경을 실패하였습니다.</c:otherwise>
 </c:choose>
 
 
 </div>
  
  <DIV class='bottom'>
   <c:choose>
 <c:when test="${flag }">
 <input type='button' value='나의정보' onclick="read()">
 </c:when>
 <c:otherwise>
 <input type='button' value='돌아가기' onclick="history.back()">
 </c:otherwise>
 </c:choose>

  </DIV>

 
 
</body>
<!-- *********************************************** -->
</html> 