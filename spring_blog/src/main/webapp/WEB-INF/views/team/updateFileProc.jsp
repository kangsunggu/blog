<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function tread(){
	var url = "read.do";
	url+="?no=${no}";
	url+="&nowPage=${nowPage}";
	url+="&col=${col}";
	url+="&word=${word}";
	location.href=url;
	
	
}

</script>

<style type="text/css"> 
/* *{  */
/*   font-family: gulim;  */
/*   font-size: 20px;  */
/* }  */
</style> 
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

 
<DIV class="title">사진변경</DIV>
<div class = "content">
<c:choose>
<c:when test="${flag }">
사진을 변경했습니다.
</c:when>
<c:otherwise>
사진변경을 실패하였습니다.
</c:otherwise>
</c:choose>
</div>
  
  <DIV class='bottom'>
    <input type='button' value='조회' onclick="tread()">
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>


</body>
<!-- *********************************************** -->
</html> 