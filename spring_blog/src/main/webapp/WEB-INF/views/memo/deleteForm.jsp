<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import = "java.sql.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<%
	String root = request.getContextPath();
	String col=request.getParameter("col");
	String word=request.getParameter("word");
	String nowPage=request.getParameter("nowPage");
	
	int memono=Integer.parseInt(request.getParameter("memono"));
%>

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
/* 	margin-top: 20px; */
/* 	margin-bottom: 20px; */
/* } */
</style> 
</head> 
<body> 
<div>삭제확인</div>
<form method="post" action="deleteProc.do">
<input type="hidden" name="memono" value="<%=memono%>">
<input type="hidden" name="col" value="<%=col %>">
<input type="hidden" name="word" value="<%=word %>">
<input type="hidden" name="nowPage" value="<%=nowPage %>">

<div>
	삭제를 하면 복구 될 수 없습니다.<br><br>
	삭제하시려면 삭제버튼을 클릭하세요<br><br>
	취소는 '목록'버튼을 누르세요.<br><br>
<input type="submit" value="삭제처리">
<input type="button" value="목록" onclick="location.href='<%=root%>/views/memo/list.do'">


</div>

</form>
</body> 
</html> 
