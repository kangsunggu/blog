<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("utf-8");%> 


 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function incheck(f){
	if(f.filename.value==""){
		alert("파일을 선택하세요");
		f.filename.focus();
		return false;
	}
}

</script>

<style type="text/css"> 
/* *{  */
/*   font-family: gulim;  */
/*   font-size: 20px;  */
/* }  */
img{
	width: 400px;
	height: 400px;
}
</style> 
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>


 
<DIV class="title">사진숮엉</DIV>
 
<FORM name='frm' 
method='POST' 
action='./fileProc.do'
enctype="multipart/form-data"
onsubmit="return incheck(this)"
>
<input type = "hidden" name="no" value="${param.no }" >
<input type = "hidden" name="col" value="${param.col }" >
<input type = "hidden" name="word" value="${param.word }" >
<input type = "hidden" name="nowPage" value="${param.nowPage }" >
<input type = "hidden" name="oldfile" value="${param.oldfile }" >
 
  
  <TABLE>
    <TR>
      <TH>원본파일</TH>
      <TD><img src="${pageContext.request.contextPath }/views/team/storage/${param.oldfile }"><br>
      원본파일명:${param.oldfile }
      </TD>
    </TR>
     <TR>
      <TH>변경파일</TH>
      <TD><input type="file" name="filename" accept=".gif, .jpg, .png"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
  
    <input type='submit' value='변경'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 

</body>
<!-- *********************************************** -->
</html> 