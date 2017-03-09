<%@ page contentType="text/html; charset=UTF-8" %>


<% 
	String col = request.getParameter("col");
	String word = request.getParameter("word");
	String nowPage = request.getParameter("nowPage");
	
	boolean flag = (Boolean)request.getAttribute("flag");
%> 

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function mlist(){
	var url = "list";
	url+="?col=<%=col%>";
	url+="&word=<%=word%>";
	url+="&nowPage=<%=nowPage%>";
	
	location.href=url;
	
}

</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
</style> 
</head> 
<body>
<div>
<%
	 if(flag){
		out.print("수정이 성공하였습니다.");
	}else{
		out.print("수정이 실패하였습니다.");
	}

%> 
<input type="button" value="목록" onclick="mlist()">
</div>
</body> 
</html> 