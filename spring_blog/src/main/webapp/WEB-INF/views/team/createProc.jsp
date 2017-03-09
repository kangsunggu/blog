<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html> 
<html>
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
/* *{  */
/*   font-family: gulim;  */
/*   font-size: 24px;  */
/* }  */
/* div{ */
/* 	text-align: center; */
/* 	margin-top: 100px; */
/* } */
a:LINK {
	color:#000000;
	text-decoration: none;
	}
a:visited {
	color:#000000;
	text-decoration: none;
	}
	a:hover {
	color:#ff0000;
	text-decoration: none;
	}
	a:active {
	color:#ff0000;
	text-decoration: none;
	}
	
	
		
</style> 
<script type="text/javascript">
	function read(no){
		var url="read.do";
		url +="?no="+no;
		
		location.href=url;
		
	}
	function update(no){
		url +="?no="+no;
	}
		function del(no){
		url +="?no="+no;
	}
</script>
</head> 
<body> 
<div>
<c:choose>
<c:when test="${flag }">
팀정보를 등록했습니다.
</c:when>
<c:otherwise>
팀등록에 실패하였습니다.
</c:otherwise>
</c:choose>
	<br>
	<input type="button" value="계속등록" onclick="location='./create.do'">
	<input type="button" value="목록" onclick="location='./list.do'">
	
	
</div>
</body> 
</html> 