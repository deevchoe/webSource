<%@page import="java.time.ZoneId"%>
<%@ page 
language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="java.time.LocalDate"
import="java.sql.*, java.util.*"
session="false"
buffer="8kb"
autoFlush="true"
isThreadSafe="true"
info="jsp 문서 정보를 기술"
errorPage="jsp2err.jsp"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>page 지시어 관련</h2>
페이지 지시어는 jsp 문서의 앞에서 꼭 필요한 것만 적어준다.
<hr>
날짜 출력<br>
<%
LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
int year = localDate.getYear();
int month = localDate.getMonthValue();
int day = localDate.getDayOfMonth();
out.println("오늘은 " + year + "년 " + month + "월 " + day + "일");
%>
<br>
<%= this.getServletInfo() %>
<br>
<%
int num1 = 20;	// 이 변수는 외부에서 주어지는 값을 기억하기 위함
int num2 = 5;
out.print("나누기 결과 : " + num1 / num2);
%>
</body>
</html>