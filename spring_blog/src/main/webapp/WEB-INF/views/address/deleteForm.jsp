<%@ page contentType="text/html; charset=UTF-8" %> 

  
 
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
	var url="list";
	url+="?col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${param.nowPage}";
	location.href=url;
 }
 </script>
<link href="${pageContext.request.contextPath }}/css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 

<!-- *********************************************** -->

<body>



<!-- *********************************************** -->
 
 
 
<DIV class="title">DeleteForm</DIV>
<div class="content">삭제 하시겠습니까?
<form action="./delete" name="frm" method="post">
<input type="hidden" name="col" value="${param.col } }">
<input type="hidden" name="word" value="${param.word }">
<input type="hidden" name="nowPage" value="${param.nowPage }">
<input type="hidden" name="no" value="${param.no }"> 
<input type="submit" value="삭제" >
</form>
 </div>
 

 


<!-- *********************************************** -->


</body>

<!-- *********************************************** -->
 
</html> 
 