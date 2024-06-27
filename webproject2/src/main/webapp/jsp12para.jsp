<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mbc = request.getParameter("msg");
%>

<jsp:useBean id="my" class="pack.Para1Class"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>Beans 연습 : 빈즈에 값 전달</b>
<%
// 우리가 현재까지 알고 있는 기술 사용
my.setMsg(mbc);
out.println("<br>메세지 출력 : " + my.getMsg());
%>
<br>
<!-- String msg = request.getParameter("msg"); 내부적으로 자동 처리 -->
<jsp:setProperty property="msg" name="my"/><!-- setter를 부르는 것 -->
<br>메세지 출력(action tag 사용) : 
<jsp:getProperty property="msg" name="my"/>
</body>
</html>