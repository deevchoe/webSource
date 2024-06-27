<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="form" class="pack.business.DataForm" />
<jsp:setProperty property="*" name="form" />
<!-- 수정자료를 싹 읽어오겠지!! -->
<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<%
processDao.upData(form);
response.sendRedirect("list.jsp");
%>