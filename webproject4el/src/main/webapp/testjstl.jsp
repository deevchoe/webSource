<%@page import="pack.Student"%>
<%@page import="pack.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
전통적인 방식 => <%=request.getAttribute("irum") %>
<%
out.println(request.getAttribute("irum"));
%>
<br>
EL 방식 => ${irum} ${requestScope.irum }<!-- 의 줄임 -->
<br><br>
전통적인 방식 : 
<% Person p = (Person)request.getAttribute("person"); %>
<%=p.getName() %>
<% Student s = (Student)request.getAttribute("student"); %>
<%=s.getAge() %>
<br>
EL 방식 : ${requestScope.person.name } ${student.age }
<br><br>
동물 : ${animal[0]} ${animal[1]} ${animal["2"]}
<br><br>
<c:if test="${list != null}">
	<c:forEach var="a" items="${list}">
		${a[0]}, ${a[1]}, ${a[2]}
	</c:forEach>
</c:if>
<br>
<c:if test="${list != null}">
	<c:forEach var="a" items="${list}">
		<c:forEach var="b" items="${a}">
			${b }
		</c:forEach>
		<br>
	</c:forEach>
</c:if>
<br>
<c:choose>
	<c:when test="${list eq null }"> <!-- list가 null과 같니 -->
		자료 없음
	</c:when>
	<c:otherwise>자료있음</c:otherwise>
</c:choose>
<br>

<hr>
예외 처리<br>
<c:catch var="myErr"> <!-- catch 블럭 안에 에러가 발생하면 myErr가 갖는다. 에러가 없으면 안갖는다. -->
	<%
	int a = 10 / 0;
	out.println("a : " + a);
	%>
</c:catch>
<c:if test="${myErr != null }">
	에러 발생 : ${myErr.message }
</c:if>
<hr>
계속
<br>다른 문서 포함<br>
include 지시어 사용 : <%@include file="poham.jsp" %>
<br>
jstl을 사용한다면! <c:import url="poham.jsp" />
<hr>
<%-- 
<c:import url="https://www.daum.net" />    소스를 읽어와서 바로 찍어버림
 --%>
<c:set var="url" value="https://www.naver.com" />
<c:import url="${url }" var="u" />	<!-- 가지고만 왔다 -->
<c:out value="${url}"></c:out> <!-- 주소만 찍어볼게 -->
<c:out value="${u }"></c:out> <!-- 소스코드를 갖고만올게 -->
</body>
</html>