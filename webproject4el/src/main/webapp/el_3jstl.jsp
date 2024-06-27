<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
JSTL은 JavaServer Pages Standard Tag Library의 약어로, Java 코드를 바로 사용하지 않고 HTML 태그(<>) 형태로 직관적인 코딩을 지원하는 라이브러리입니다.
Java EE 기반의 웹 애플리케이션 개발 플랫폼을 위한 컴포넌트 모음
XML 데이터 처리와 조건문, 반복문, 국제화와 지역화와 같은 일을 처리하기 위한 JSP 태그 라이브러리
자신만의 태그를 추가할 수 있는 기능을 제공합니다.
<br>
변수, 제어문 사용이 가능. 일반적으로 EL과 함께 사용!
<hr>
** 변수 처리 **<br>
<c:set var="irum" value="이기자" scope="page"></c:set> <%-- 변수의 유효범위 : scope=page(기본), request, session, application --%>
이름:<c:out value="${irum}"></c:out>
<br>
<c:set var="ir" scope="session">
공기밥
</c:set>
이름:<c:out value="${ir}" />
<br>
<c:remove var="irum"/>
이름:<c:out value="${irum}"></c:out>
<br>
<c:remove var="ir" scope="session" />
이름:<c:out value="${ir}"></c:out>
<br><br>
<c:set var="abc" value="${header['User-Agent']}" scope="page" />
abc 값은 (현재 사용중인 브라우저 정보) <c:out value="${abc }" />
<br>
<c:set var="su1" value="10" />
<c:set var="su2">
20
</c:set>
두 수의 합은 ${su1 + su2}
<hr>
** 조건 판단문 if **<br>
<c:set var="nice" value="star" />

<c:if test="${nice == 'star'}">	<%-- ${nice eq 'star'} --%> <!-- test는 키워드이다. 그대로 써줘야 한다. -->
	if 연습 : nice 값은 <c:out value="${nice }" />
</c:if> 
<p/>
** 조건 판단문 choose ** <br>
<c:choose>
	<c:when test="${nice == 'moon' }">
		달 <c:out value="${nice }"></c:out>
	</c:when>
	<c:when test="${nice == 's' }">
		별 <c:out value="${nice }"></c:out>
	</c:when>
	<c:otherwise>어떠한 조건도 만족하지 않은 경우</c:otherwise>
</c:choose>
<br>
<c:choose>
	<c:when test="${empty param.myid}">
		<form>
			아이디 : <input type="text" name="myid">
			<input type="submit">
		</form>
	</c:when>
	<c:when test="${param.myid == 'admin'}">
		와우 관리자군요
	</c:when>
	<c:otherwise>
		환영합니다. 회원 <c:out value="${param.myid }" />
	</c:otherwise>
</c:choose>
<hr>
** 반복문 forEach **<br>
연습1 : 
<c:forEach var="i" begin="1" end="10" step="2"><!-- while은 없어! forEach만 있다 -->
	${i}&nbsp;&nbsp;
</c:forEach>
<br>
구구단(3단)<br>
<c:forEach var="i" begin="1" end="9">
	3 * ${i} = ${3 * i }<br>
</c:forEach>
<br>
<%
HashMap<String, Object> map = new HashMap<>();	// map은 순서가 없어! arraylist는 순서가 있어!
map.put("name", "한국인");
map.put("today", new Date());
%>

<c:set var="m" value="<%=map %>"></c:set> <!-- 변수 m은 map을 갖는다ㅏ -->
<c:forEach var="i" items="${m}">
	${i.key} - ${i.value}<br>
</c:forEach>

<br>
배열 생성 후 출력 <br>
<c:set var="arr" value="<%=new int[]{1,2,3,4,5} %>"></c:set>
<c:forEach var="a" items="${arr }" begin="2" end="4" step="1"> <!-- a라는 변수로 for를 돌려보자 -->
	${a}&nbsp;
</c:forEach>
<br>
* 문자열 분할 후 출력 *<br>
<c:forTokens var="animal" items="horse,dog*cat,lion,tiger,pig" delims=",*">	<!-- 문자열과 관련있는 for 구분자는 ,와 * 두개 쓰기도 가능   띄어쓰기 하지마!! -->
	동물 : ${animal }&nbsp;
</c:forTokens>

<hr>
** 숫자 및 날짜 서식 **<br>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
숫자 : <fmt:formatNumber value="12345.678" type="number" /><br> <!-- 숫자처리는 fotmatNumber가 하고 있다. type number는 일반 숫자로 처리할거야      알아서 3자리마다 콤마 찍어 -->
숫자 : <fmt:formatNumber value="12345.678" type="cuttency" />
숫자 : <fmt:formatNumber value="0.123" type="percent" />
숫자 : <fmt:formatNumber value="12345.678" type="number" pattern="#,##0"/><br>	<!-- 3자리마다 콤마 찍어. 소수점 제거 -->
숫자 : <fmt:formatNumber value="12345.678" pattern="#,##0.0"/><br>	<!-- 소수 첫째자리까진 표시해 둘째자리에서 반올림 함 #과 0의 차이점은 #은  무효의 0은 공백 처리  0은 무효의 0는 0을 찍어 -->
숫자 : <fmt:formatNumber value="12345.678" pattern="0,000.0"/><br>
숫자 : <fmt:formatNumber value="12" pattern="0,000.0"/><br>
<br>
<c:set var="now" value="<%=new Date() %>" />

날짜 : <fmt:formatDate value="${now}" type="date"/><br>
시간 : <fmt:formatDate value="${now}" type="time"/><br>
모두 : <fmt:formatDate value="${now}" type="both"/><br>
모두 : <fmt:formatDate value="${now}" type="both" pattern="yyyy년 MM월 dd일"/><br> <!-- 대소문자 구분한다. 월은 대문자로 써줘야 한다. -->
</body>
</html>