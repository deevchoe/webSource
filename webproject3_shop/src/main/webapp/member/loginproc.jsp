<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.MemberMgr"></jsp:useBean>
<%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

boolean b = memberMgr.loginCheck(id, passwd);	// id와 비번을 db에서 읽어와서 

if(b){	// b가 true니까 로그인 성공한거네! 그럼 세션 생성
	session.setAttribute("idKey", id);	// login.jsp에서 idkey라고 줬다. 누굴 줄거야? id를 줘야지.
	response.sendRedirect("login.jsp");
}else{	// 아니라고? 로그인 실패 했다고?
	response.sendRedirect("logfail.html");
}
%>