<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="fbean" class="pack.business.DataFormBean" />
<jsp:setProperty property="*" name="fbean" />
<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<%
boolean b = processDao.insertData(fbean);

if(b)
	response.sendRedirect("list.jsp");
else
	response.sendRedirect("fail.jsp");
%>