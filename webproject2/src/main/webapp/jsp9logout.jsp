<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션 읽기
HttpSession ses = request.getSession(false);
//ses.invalidate();	// 세션에 설정된 모든 속성 삭제
ses.removeAttribute("userid");	// 특정 속성 삭제

response.sendRedirect("jsp9sessionlogin.html");
%>