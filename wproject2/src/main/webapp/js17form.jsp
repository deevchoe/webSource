<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 영역
request.setCharacterEncoding("utf-8");

// 데이터를 받은 후에
String irum = request.getParameter("name");
String id = request.getParameter("id");

// 수신된 자료를 자바에서 표준 출력 장치로 출력
System.out.println(irum + " " + id);

String email = request.getParameter("email");
String nai = request.getParameter("age");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 자바스크립트 영역
console.log(`자바스크립트 표준 출력장치로 출력<%=irum%>`)
</script>
</head>
<body>
<!-- HTML body 영역 -->
이름은 <%=irum %> 아이디는 <%=id %> 이메일은 <%=email %> 나이는 <%=nai %>
</body>
</html>