<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %> 


<%
	String col = request.getParameter("col");
	String word = request.getParameter("word");
	String nowPage = request.getParameter("nowPage");
	
	int memono = Integer.parseInt(request.getParameter("memono"));
	
	boolean flag = (Boolean)request.getAttribute("flag");
%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type="text/javascript">
function mlist(){
	var url = "list.do";
	url+="?col=<%=col%>";
	url+="&word=<%=word%>";
	url+="nowPage=<%=nowPage%>";
	
	location.href=url;
}

</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 14px; 
} 
div{
	text-align: center;
	margin-top: 20px;
}
</style> 
</head> 
<body> 
<div>
	<%
		if(flag){
			out.print("메모를 삭제하였습니다.");
		}else{
			out.print("메모삭제를 실패했습니다.");
		}
	%>
	<br>
	<input type="button" value="목록" onclick="mlist()">
</div>


</body> 
</html> 

