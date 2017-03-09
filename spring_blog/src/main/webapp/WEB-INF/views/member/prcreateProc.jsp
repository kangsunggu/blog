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
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 

<body>

 
<DIV class="title"></DIV>
 <div class= "content">
 	${str} 
  
  
  <DIV class='bottom'>
    <input type='submit' value='다시시도' onclick="history.back()">
  </DIV>
 </div>
 

</body>

</html> 