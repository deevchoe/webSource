<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String password = request.getParameter("password");

// Authentication(인증) : 실제는 DB 정보를 읽어 확인
String validId = "ok";
String validPassword = "123";

if(id != null && password != null && 
		id.equalsIgnoreCase(validId) && password.equals(validPassword)){
	// 인증이 유효하다면 
	HttpSession ses = request.getSession();
	ses.setAttribute("userid", id);		// 세션 생성 후 sessionid를 클라이언트 컴 쿠키에 저장
	
	// 인증에 성공한 경우 보여줄 페이지로 이동
	response.sendRedirect("jsp9success.jsp");	// success.html로 갈 수도 있다.
}else{
	// 인증에 실패한 경우 처리 작업
	out.println("<html><body>");
	out.println("<h3>로그인 실패</h3>");
	out.println("<a href='jsp9sessionlogin.html'>다시 시도</a>");
	out.println("</html></body>");
}
%>