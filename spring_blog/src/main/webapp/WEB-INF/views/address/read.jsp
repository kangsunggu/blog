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
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 

<!-- *********************************************** -->

<body>



<!-- *********************************************** -->
 
 
 
<DIV class="title">read</DIV>
 
<div class="content">
<table>
		<tr>
			<th>이름:</th>
			<td>${dto.name}</td>
		</tr>
		<tr>
			<th>핸드폰번호:</th>
			<td>${dto.phone}</td>
		</tr>
		<tr>
			<th>우편번호:</th>
			<td>${dto.zipcode}</td>
		</tr>
		<tr>
			<th>주소:</th>
			<td>${dto.address1}</td>
		</tr>
		<tr>
			<th>상세주소:</th>
			<td>${dto.address2}</td>
		</tr>
		<tr>
			<th>날짜:</th>
			<td>${dto.wdate}</td>
		</tr>
		
  </table>
 </div>
  <DIV class='bottom'>
 

 
    <input type='button' value='목록' onclick="ml()">
 
  </DIV>
 

 
 
 

 


<!-- *********************************************** -->


</body>

<!-- *********************************************** -->
 
</html> 
 