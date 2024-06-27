<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>EL 연산자 / 내장객체 경험</h2>
--- 연산자 ---<br>
\${3 + 4} ==> ${3 + 4}<br><!-- \를 붙이면 $의 기능을 잃어버려 ${3 + 4} 7 이렇게 출력된다. -->
\${5 / 4} ==> ${5 / 4} <!-- ${5 div 4} --><br>	<!-- 나누기는 div라는 키워드를 사용하여 사용 가능 이클립스는 에러를 띄우지만 사용 가능하다ㅏ -->
\{5 % 4} ==> ${5 % 4}, ${5 mod 4 }<br>

\${5 > 4} ==> ${5 > 4 }, ${5 gt 4}, ${5 lt 4 } <br>
\${5 >= 4} ==> ${5 >= 4 }, ${5 ge 4}, ${5 le 4 } <br>

\${5 > 4 and 3 > 2} ==> ${5 > 4 and 3 > 2}, {5 > 4 or 3 > 2}<br>

\${5 >= 4?10:10+5 } ==> ${5 >= 4?10:10+5 }
<hr>
--- 내장 객체 ---<br>
<%
request.setAttribute("aa", "air");
session.setAttribute("bb", "burger");
application.setAttribute("cc", "cat");
%>
* 생존범위 관련 내장객체 출력 *<br>
jsp: <%= request.getAttribute("aa") %> EL ${requestScope.aa}, ${aa} <!-- requestScope는 생략하고 사용 가능 -->
jsp: <%= session.getAttribute("bb") %> EL ${requestScope.bb}, ${bb} <br>
jsp: <%= application.getAttribute("cc") %> EL ${requestScope.cc}, ${cc} <br>
<br>
jsp의 header : <%=request.getHeader("host") %><br>
EL의 header : ${header.host}, ${header["host"]}<br>
<br>
* 컬렉션 객체 값 출력 <br>
<%
ArrayList<String> list = new ArrayList<>();
list.add("새우버거");
list.add("불고기버거");
list.add("치킨버거");
request.setAttribute("list", list);

ArrayList<String> list2 = new ArrayList<>();
list2 = (ArrayList)request.getAttribute("list");
out.println(list2.get(1));
%>
<br>
EL로 출력 : ${list[0]}, ${list[1]}	<!-- EL에는 반복문이 없다. jstl 들어가야 한다. -->
<br><br>
-- HTML 문서 자료 받기 --<p/>
<a href="el_2.html">el_2 html 호출</a><br>
이름 : ${param.irum} ${param["irum"]}<br>
성격 : ${paramValues.sung[0]} ${paramValues.sung["1"]}	<!-- 배열이니까 paramValues로 받아 -->
</body>
</html>