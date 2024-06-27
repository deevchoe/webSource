<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 수정을 실질적으로 처리해주는 부분 -->
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="bean" class="pack.SangpumBean"></jsp:useBean>
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="connP" class="pack.ConnPooling" scope="page" />

<%
//boolean b = connP.updateDataOk(bean);
//if(b) ...
if(connP.updateDataOk(bean))
	response.sendRedirect("jsp17dbcp.jsp");		// 수정 후 상품 목록 보기로 돌아감
else
	response.sendRedirect("jsp17fail.html");	
%>