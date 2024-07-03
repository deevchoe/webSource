<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>🐻🦤🙊🦛🐰🐵</h2>
<h3>&nbsp; 🐧  사용자 수정  🐧</h3>
<h2>🐵🐰🦛🙊🦤🐻</h2>

<form action="update.m2" method="post">
<input type="hidden" name="userid" value="${user.userid}" >
아이디: ${user.userid}<br>
비밀번호: <input type="text" name="password" value="${user.password}"><br>
이름: <input type="text" name="name" value="${user.name}"><br>
이메일: <input type="text" name="email" value="${user.email}"><br>
<br>
<input type="submit" value="수정">
<input type="button" value="목록" onclick="location.href='list.m2'">

</form>
</body>
</html>