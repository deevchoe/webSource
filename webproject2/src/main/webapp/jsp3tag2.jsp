<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = request.getParameter("msg");
out.println("msg는 " + msg);
%>