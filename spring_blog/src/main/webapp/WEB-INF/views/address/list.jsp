<%@ page contentType="text/html; charset=UTF-8" %> 
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 

  
 
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
function read(no) {
	var url="read";
	url+="?col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${nowPage}";
	url+="&no="+no;
	location.href=url;
}
function cr() {
	var url="create";
	location.href=url;
}
function de(no) {
	var url="delete";
	url+="?no="+no;
	url+="&col=${col}";
	url+="&word=${word}";
	url+="&nowPage=${nowPage}";
    location.href=url;
}
function up(no) {
	var url="update";
	url+="?no="+no;
	url+="&col=${col}";
	url+="&word=${word}";
	url+="&nowPage=${nowPage}";
    location.href=url;
}

</script>
 
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 

<!-- *********************************************** -->

<body>



<!-- *********************************************** -->
 
 
 
<DIV class="title">List</DIV>
 <div class="content">
 <form name="frm" method='post' action='./list'>
 <select name="col">
 <option value="no"
 <c:if test="${col=='no'}">selected='selected'</c:if>
 
 >번호</option>
 <option value="name"
 <c:if test="${col=='name'}">selected='selected'</c:if>
  >이름</option>
 <option value="phone"
 <c:if test="${col=='phone'}">selected='selected'</c:if>

 >전화번호</option>
 <option value="total">전체</option>
 </select> 
   <input type="text" name="word" value="${word}"> 
    <input type='submit' value='검색'>


</FORM>
 
 </div>
 <div class="content">

 <table>


 <tr>
 <th>번호</th>
 <th>이름</th>
 <th>전화번호</th>
 <th>등록날짜</th>
 <th>삭제/수정</th>
 
 </tr>
<c:if test="${fn:length(list)==0 }">
  
 <tr>
<td colspan="5">등록한 정보가 없습니다</td> 
 </tr>
 </c:if>
 <c:forEach items="${list }" var="dto">

 
<tr>
<td>${dto.no}</td>
<td><a href="javascript:read('${dto.no }')">${dto.name }</a></td>
<td>${dto.phone }</td>
<td>${dto.wdate }</td>
<td><input type="button" value="삭제" onclick="de(${dto.no})">/
<input type="button" value="수정" onclick="up(${dto.no})"></td>

</tr>
 </c:forEach>
 </table>
 </div>
${paging }
 
<div class="bottom">
<input type="button" value="등록" onclick="cr()">
</div>
 


<!-- *********************************************** -->


</body>

<!-- *********************************************** -->
 
</html> 
 