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
/* text-align: center; margin-top: 20px; */
/* 	margin-bottom: 20px;  */
/*  } */
</style> 
</head> 
<body> 
<div>
<c:choose>
<c:when test="${flag }">메모등록을 하였습니다.</c:when>
<c:otherwise>메모등록이 실패하였습니다.</c:otherwise>
</c:choose>
<br>
<input type="button" value="계속 등록" onclick="location.href='${pageContext.request.contextPath}/memo/create'">
<input type="button" value="목록" onclick="location.href='./list'">

</div>
</body> 
</html> 