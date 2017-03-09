<%@ page contentType="text/html; charset=UTF-8" %> 

<!-- 로그아웃 될경우 인덱스페이지로 가는것 -->
 <script type="text/javascript">
	var url = "${pageContext.request.contextPath}/home";
	location.href=url;
</script>
