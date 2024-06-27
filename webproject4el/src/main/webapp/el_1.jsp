<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
EL(Expression Language)
 - JSP 스크립트 표현식을 대신해 속성값을 쉽게 출력하기 위한 언어
 - 출력, 반복처리를 태그 기반으로 제공
 - 웹 디자이너와 프로그래머의 업무분리가 가능
</pre>

<h2>환영합니다. EL 세계 방문을...</h2>
<%
if(request.getParameter("userName") == null){
%>
	<jsp:forward page="el_1.html" />	<!-- null인 경우에는 el_1.html로 이동할게 -->
<%
}
%>
사용자가 전송한 userName 출력<br>
1-1) jsp scriplet 사용 : 
<% out.println(request.getParameter("userName")); %>
<br>
1-2) jsp 표현식(Expression) 사용 :
<%= request.getParameter("userName") %>
<br>
2) EL의 내장 객체 param을 사용 : 
${param.userName}
</body>
</html>