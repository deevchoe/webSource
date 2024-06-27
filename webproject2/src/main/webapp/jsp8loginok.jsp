<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
로그인 성공했을 때<p>
<table border="1">
	<tr>
		<td colspan="2">해당 페이지로 이동</td>
	</tr>
	<%
	String id = (String)session.getAttribute("id");
	
	if(id.equals("admin")){
	%>
		<tr>
			<td style="background-color: pink;"><%=id %></td>
			<td><a href="https://naver.com">관리자 화면</a></td>
		</tr>
	<%
	}else if(id.equals("user")){
	%>
		<tr>
			<td style="background-color: silver;"><%=id %></td>
			<td><a href="https://daum.net">사용자 화면</a></td>
		</tr>
	<%
	}
	%>
</table>
</body>
</html>