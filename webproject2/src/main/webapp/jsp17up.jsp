<%@page import="pack.SangpumDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String code = request.getParameter("code"); %> <!-- 코드 받아야지 하나만 읽을거니까 빈을 쓸 필요 없지 -->

<jsp:useBean id="connP" class="pack.ConnPooling"></jsp:useBean> <!-- db 연결하는 객체 생성? -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
SangpumDto dto = connP.updateData(code);   // 코드를 들고 가서 읽기만 할 뿐. 수정은 아직 하지 않는다.
if(dto == null){
%>
	<script type="text/javascript">
		alert("등록된 상품 코드가 없습니다.\n수정 불가!");
		//history.back(); 이전으로 돌아가는거
		location.href="jsp17dbcp.jsp"; // 좀 더 명시적으로 돌아가ㅏ
	</script>
<%
	return;
}
%>

** 상품 수정 **<br>
<form action="jsp17upok.jsp" method="post"><br>
<input type="hidden" name="code" value="<%=dto.getCode() %>"><!-- 수정은 안하지만 넘어는가야돼. 그래야 submit 했을때 코드상수단이 넘어간다. -->
코드 : <%=dto.getCode() %><br>	<!-- 코드는 수정대상이 아니다. primarykey는 수정하는게 아니야. 보여만줘ㅓ -->
품명 : <input type="text" name="sang" value="<%=dto.getSang() %>"><br>
수량 : <input type="text" name="su" value="<%=dto.getSu() %>"><br>
단가 : <input type="text" name="dan" value="<%=dto.getDan() %>"><br>
<br>
<input type="submit" value="자료 수정">	<!-- 이걸 누르면 실제적으로 수정하는 jsp16upok.jsp로 갈거야. 코드는 안넘어가 name(sang,su,dan)만 넘어가. 수정자료 오류검사 해야되는데 여기선 생략할게 -->
<input type="button" value="수정 취소" onclick="javascript:location.href='jsp17dbcp.jsp'">
</form>
</body>
</html>