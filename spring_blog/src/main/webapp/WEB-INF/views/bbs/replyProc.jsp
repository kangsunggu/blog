<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>createProc</title>
<script type="text/javascript">
function blist() {
var url = "list.do";
url+="?col=${col}";
url+="&word=${word}";
url+="&nowPage=${nowPage}";

location.href = url;
}
</script>
<style type="text/css">
/* * { */
/* font-family: gulim; */
/* font-size: 20px; */
/* } */
</style>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body leftmargin="0" topmargin="0">
<!-- *********************************************** -->
<div class="title">처리 결과</div>
<DIV class="content">
<c:choose>
<c:when test="${flag }">
답글이 등록되었습니다.
</c:when>
<c:otherwise>
답글이 등록되지 못했습니다.
</c:otherwise>
</c:choose>
</DIV>
<div class="bottom">
<input type="button" value="새글 작성" onclick="location='./create.do'"> <input type="button" value="목록" onclick="blist()">
</div>
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>