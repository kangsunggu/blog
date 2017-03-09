<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function read(){
	var url = "read";
	url+="?id=${id}";
	location.href=url;
}
function mlist(){
	var url = "${pageContext.request.contextPath}/admin/list";
	url+="?col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${param.nowPage}";
	
	location.href=url;
}


</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

 
<DIV class="title">정보수정</DIV>
<div class="content">
<c:choose>
<c:when test="${flag }">정보를 수정 하였습니다.</c:when>
<c:otherwise>정보수정을 실패하였습니다.</c:otherwise>
</c:choose>

</div>

  
  <DIV class='bottom'>
  <c:choose>
  <c:when test="${not empty id && grade =='A' }">
  <input type='submit' value='회원목록' onclick="mlist()">
  </c:when>
  <c:otherwise>
  <input type='submit' value='정보확인' onclick="read()">
  <input type='button' value='돌아가기' onclick="history.back()">
  </c:otherwise>
  </c:choose>
  
  </DIV>

 
 
</body>
<!-- *********************************************** -->
</html> 