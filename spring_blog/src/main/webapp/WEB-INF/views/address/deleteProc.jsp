<%@ page contentType="text/html; charset=UTF-8" %> 
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
 
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
 <script type="text/javascript">
 function ml() {
		var url="list.do";
		url+="?col=${param.col}";
		url+="&word=${param.word}";
		url+="&nowPage=${param.nowPage}";
		location.href=url;
	 }
 </script>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 

<!-- *********************************************** -->

<body>



<!-- *********************************************** -->
 
 
 
<DIV class="title">DeleteProc</DIV>

  <div class="content">
  <c:choose>
  <c:when test="${flag }">성공
  <DIV class='bottom'>
     
    <input type='button' value='목록' onclick="ml()">
 
  </DIV>
  </c:when>
  <c:otherwise>실패

    <input type='button' value='목록' onclick="ml()">
    <input type='button' value='되돌아가기' onclick="history.back()">

</c:otherwise>
  </c:choose>
</div>
 
 

 


<!-- *********************************************** -->


</body>

<!-- *********************************************** -->
 
</html> 
 