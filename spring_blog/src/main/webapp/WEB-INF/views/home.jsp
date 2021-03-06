<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
<c:when test="${empty sessionScope.id }">

<c:set var="str">Spring MVC blog 페이지 입니다.</c:set>

</c:when>
<c:otherwise>
<c:set var="str">안녕하세요. ${sessionScope.id }님!</c:set>
</c:otherwise>
</c:choose> 
<c:set var="title">나의 여행 블로그 Spring MVC</c:set>
<c:if test="${not empty sessionScope.id && sessionScope.grade=='A' }">
<c:set var="title">관리자 페이지</c:set>
</c:if>
<html>
<head>
	<title>Home</title>
	<link href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
<div class="title">${title }
<h1 class="h1">${str }</h1>
<img src="${pageContext.request.contextPath }/images/kakao2.png" width="25%" class="h1"><br>

<c:choose>
<c:when test="${empty sessionScope.id }">
<input class="h1" type="button" value="로그인" onclick="location.href='${pageContext.request.contextPath}/member/login'">

</c:when>
<c:otherwise>
<input class="h1" type="button" value="로그아웃" onclick="location.href='${pageContext.request.contextPath}/member/logout'">
</c:otherwise>
</c:choose>

<P>  The time on the server is ${serverTime}.안녕~~ </P>
</div>




</body>
</html>
