<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
** 상품 목록 (MyBatis) **<br>
<% 
ArrayList<DataDto> list = (ArrayList<DataDto>)processDao.selectDataAll(); 
//request.setAttribute("list", list);
%>


<a href="ins.html">상품 추가</a>&nbsp;
코드를 클릭하면 삭제, 품명을 클릭하면 수정
<table border="1">
	<tr>
		<th>코드</th><th>품명</th><th>수량</th><th>단가</th>
	</tr>
	
	<%-- <c:forEach var="s" items="${list }">rerquestscope.list의 준말이니까 --%>
	<c:forEach var="s" items="<%=list %>">	<!-- 변수명은 s 누가 갖고있어? list가 -->
	<tr>
		<td><a href="delete.jsp?code=${s.code}">${s.code }</a></td>
		<td><a href="update.jsp?code=${s.code}">${s.sang }</a></td>
		<td>${s.su }</td>
		<td>${s.dan }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>