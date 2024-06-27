<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="form" class="pack.business.DataForm" />
<jsp:setProperty property="*" name="form" />
<!-- 이 두 줄에 의해서 사용자가 입력한 값이 다 DataForm에 담긴다 -->
<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<%
processDao.insData(form); 
response.sendRedirect("list.jsp");
%>