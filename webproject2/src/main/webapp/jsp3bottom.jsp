<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<hr>
<%
LocalTime sigan = LocalTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
out.println(sigan.format(formatter));
%>