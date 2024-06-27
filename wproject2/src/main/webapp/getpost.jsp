<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String irum = request.getParameter("name");
String nai = request.getParameter("age");
%>

<%=irum + "님의 나이는 " + nai + "살" %>