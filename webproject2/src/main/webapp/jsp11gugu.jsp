<%@page import="pack.Gugudan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
** 지금까지 배운 방법 사용 **<br>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
out.println(dan + "단 출력<br>");

//Gugudan gugudan = new Gugudan();	// 클래스의 포함관계. 객체가 요청 수 만큼 생성됨.
Gugudan gugudan = Gugudan.getInstance();	// 클래스의 포함관계. 싱글톤패턴 사용 => 객체가 한번만 만들어진다.

int re[] = gugudan.computeGugu(dan);

for(int a = 0; a < 9; a++){
	out.println(dan + "*" + (a + 1) + "=" + re[a] + "&nbsp;&nbsp;");
}
%>
<hr>
** jsp 액션태그 중 useBean을 사용 **<br>
<jsp:useBean id="gugu" class="pack.Gugudan" scope="page"/>	<!-- Gugudan gugudan = new Gugudan(); -->
<%--
Page : 페이지 내에서 지역변수처럼 사용. 매번 객체 생성
Request : http 요청을 WAS가 받아서 웹 브라우저에게 응답할 때까지 변수가 유지되는 경우 사용. 매번 객체 생성
Session : 웹 브라우저 별로 변수가 관리되는 경우 사용. 객체 1회만 사용
Application : 웹 어플리케이션이 시작되고 종료될 때까지 변수가 유지되는 경우 사용. 객체 1회만 생성
--%>

<%
int re2[] = gugudan.computeGugu(dan);

for(int a = 0; a < 9; a++){
	out.println(dan + "*" + (a + 1) + "=" + re2[a] + "&nbsp;&nbsp;");
}
%>
</body>
</html>