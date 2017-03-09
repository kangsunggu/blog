<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" uri="/ELFunctions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<!--     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script type="text/javascript">
function read(no){
var url= "read";
url+= "?no="+no;
url+= "&col=${col}";
url+= "&word=${word}";
url+= "&nowPage=${nowPage}";

location.href=url;
}
function down(fname){
	var url = "${pageContext.request.contextPath}/download";
	url+="?dir=/img/storage";
	url+="&filename="+fname;
	
	location.href=url;
}
</script>
<style type="text/css">
/* *{  */
/*   font-family: gulim;  */
/*   font-size: 20px;  */
/* }  */
.search{
width: 40%;
text-align: center;
margin: 3px auto;
}
/* div{ */
/* text-align: center; */
/* margin-bottom: 20px; */
/* margin-top: 20px; */
/* } */
/* table{ */
/* margin: auto; */
/* width:50% */
/* } */

/* table, th, td{ */
/*  border: 1px solid black; */
/*     	border-collapse: collapse */
/* } */
img#im{
	width:250px;
	height:250px;
	margin-left: auto; 
	margin-right: auto;
	 display: block;
 
}
</style>
<link href="${pageContext.request.contextPath}../css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>


<DIV class="title">게시판 목록</DIV>
<div class="search">
<FORM method='POST' action='./list'>

<select name="col">
<!-- 검색할 컬럼 -->
<option value="wname"
<c:if test="${col==wname }">selected='selected'</c:if>
>성명</option>

<option value="title"
<c:if test="${col==title }">selected='selected'</c:if>
>제목</option>

<option value="content"
<c:if test="${col==content }">selected='selected'</c:if>
>내용</option>
<option value="total">전체</option>
</select>

<input type="text" name="word" value="${word}"> 
<input type='submit' value='검색'> 
<input type='button' value='등록' onclick="location.href='./create'">

</FORM>
</div>
<TABLE>
    <TR>
         <TH>번호</TH>
         <TH>사진</TH>
         <TH>제목</TH>
         <TH>성명</TH>
         <TH>조회수</TH>
         <TH>등록일</TH>       
         <TH>파일명</TH>             
    </TR>
    <c:choose>
    <c:when test="${fn:length(list)==0 }">
    <tr>
    	<td colspan="7">등록된 글이 없습니다</td>
    </tr>
    </c:when>
    <c:otherwise>
    <c:forEach items="${list }" var="dto">
    <tr>
    	<Td>${dto.no}</Td>
    	<td><img id="im" src="${pageContext.request.contextPath }/img/storage/${dto.fname}"></td>
    	<Td>
    	<c:forEach begin="1" end="${dto.indent}">&nbsp;&nbsp;</c:forEach>
    	<c:if test="${dto.indent>0 }"><img src="${pageContext.                                                 request.contextPath }/img/storage/re.png" width="20px" height="20px"></c:if>
    	<a href="javascript:read('${dto.no}')">${dto.title}</a>
    	<c:if test="${util:newImg(fn:substring(dto.wdate,0,10)) }">
<%--     	<img src="${pageContext.request.contextPath }/img/storage/re.png" width="20px" height="20px"> --%>
    	<img src="${pageContext.request.contextPath }/img/storage/new.gif">
    	</c:if>
    	</Td>
    	<Td>${dto.wname}</Td>
    	<Td>${dto.viewcnt}</Td>
        <Td>${fn:substring(dto.wdate,0,10)}</Td>    
        <Td>
         <c:choose>
         <c:when test="${empty dto.fname }">파일없음</c:when>
         <c:otherwise>
         <a href="javascript:down('${dto.fname}')">
    	<span class='glyphicon glyphicon-download-alt'></span>
    	</a>
         </c:otherwise>
         </c:choose>
        </Td> 
    </tr>
 </c:forEach>
 </c:otherwise>
 </c:choose>
    </TABLE>
    
  <DIV class='bottom'>
    ${paging }
  </DIV>
 

</body>
<!-- *********************************************** -->
</html>