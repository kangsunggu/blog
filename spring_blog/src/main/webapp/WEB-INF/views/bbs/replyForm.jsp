<%@ page contentType="text/html; charset=UTF-8" %> 

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
/* *{  */
/*   font-family: gulim;  */
/*   font-size: 20px;  */
/* }  */

</style> 
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>
</head> 
<!-- *********************************************** -->
<body>

 
<DIV class="title">답변</DIV>
 
<FORM name='frm' 
method='POST' 
action='./reply'
enctype="multipart/form-data">
<!-- 답변을 등록하기 위해서 -->
<input type="hidden" name="grpno" value="${dto.grpno }">
<input type="hidden" name="indent" value="${dto.indent }">
<input type="hidden" name="ansnum" value="${dto.ansnum }">
<!-- 페이지와 검색유지를 위해서 -->
<input type="hidden" name="col" value="${param.col }">
<input type="hidden" name="word" value="${param.word }">
<input type="hidden" name="nowPage" value="${param.nowPage }">
<!-- 부모글 삭제를 막기 위해서 -->
<input type="hidden" name="bbsno" value="${dto.bbsno }">

  <TABLE>
    <TR>
      <TH>성명</TH>
      <TD class="td1"><input type="text" name="wname"></TD>
    </TR>
     <TR>
      <TH>제목</TH>
      <TD class="td1"><input type="text" name="title" value="${dto.title }"></TD>
    </TR>
     <TR>
      <TH>내용</TH>
      <TD>
      	<textarea rows="10" cols="45" name="content"></textarea>
      </TD>
    </TR>
     <TR>
      <TH>비밀번호</TH>
      <TD class="td1"><input type="password" name="passwd"></TD>
    </TR>
    <TR>
      <TH>파일명</TH>
      <TD class="td1"><input type="file" name="fileMF"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='등록'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 

</body>
<!-- *********************************************** -->
</html> 