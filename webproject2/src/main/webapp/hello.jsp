<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println("클라이언트에 의해 호출됨");
System.out.println("서버에서 WEB-INF 영역 내의 hi.jsp 호출 시도");
// 주의 : redirect는 불가
// forward만 가능
%>
<jsp:forward page="WEB-INF/hi.jsp"></jsp:forward>