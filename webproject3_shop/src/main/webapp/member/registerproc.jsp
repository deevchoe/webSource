<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mbean" class="pack.member.MemberBean" />
<jsp:setProperty property="*" name="mbean" />
<jsp:useBean id="memberMgr" class="pack.member.MemberMgr" />

<%
boolean b = memberMgr.memberInsert(mbean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(b){
	out.println("<br>회원가입 성공 ;)</b><br>");
	out.println("<a href='login.jsp'></a><br>");
} else {
	out.println("<br>회원가입 실패 ㅠㅠ</b><br>");
	out.println("<a href='register.jsp'></a><br>");
}
%>
</body>
</html>