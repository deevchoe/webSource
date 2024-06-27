<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String id = request.getParameter("id");

//request.setAttribute("idkey", id);	// 현재 jsp 파일에서만 유효
//application.setAttribute("idkey", id);	// 현재 서비스 중 모두에게 유효. 서비스가 살아있는동안 유효

// Servlet인 경우 HttpSession session = request.getSession(true);
// jsp에서는 이렇게만 써주면 된다. 그러면 세션이 만들어진다.
// session.setAttribute("idkey", id);
session.setAttribute("idkey", id);	// 세션을 참조하는 파일에서만 유효
session.setMaxInactiveInterval(10);	// 세션 유효시간 10초~ 기본값은 30분~~
// 서버가 클라이언트 컴에 sessionid를 쿠키에 저장해둠. 
// 다음부터는 클라이언트가 서버에 정보 요청시 sessionid가 담긴 쿠키를 들고 감.
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>* 세션 연습 *</h2>
<form action="jsp7session2.jsp">
보고 싶은 영화 선택 : <br>
<input type="radio" name="movie" value="원더랜드" checked="checked">원더랜드
&nbsp;&nbsp;
<input type="radio" name="movie" value="퓨리오사">퓨리오사
&nbsp;&nbsp;
<input type="radio" name="movie" value="설계자">설계자
<input type="submit" value="결과보기">
</form>
</body>
</html>