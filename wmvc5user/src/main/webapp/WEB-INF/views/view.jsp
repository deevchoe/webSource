<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="logincheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = () => {
	// false로 설정되어 있으면 이벤트 핸들러가 버블링 단계에서 실행
	// 즉, 하위 요소에서 상위 요소로 이벤트가 전파될 때 호출
	// 이 매개변수를 true로 설정하면 이벤트 핸들러가 캡처링 단계에서 실행
	document.querySelector("#btnUpdate").onclick = function(){
		if(confirm("정말 수정할까요?")){
			frm.action = "updateform.m2";
			frm.submit();
		}
	}
	
	document.querySelector("#btnDelete").onclick = function(){
		if(confirm("정말 삭제할까요?")){
			frm.action = "delete.m2";
			frm.submit();
		}
	}
	
	document.querySelector("#btnList").onclick = function(){
		frm.action = "list.m2";
		frm.submit();
	}
}
</script>
</head>
<body>
<h2>🐻🦤🙊🦛🐰🐵</h2>
<h3>🐧 사용자 상세보기 🐧</h3>
<h2>🐵🐰🦛🙊🦤🐻</h2>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>${user.userid}</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${user.password}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${user.name}</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${user.email}</td>
	</tr>
	<tr>
		<td colspan="2">
			<button id="btnUpdate">수정</button>
			<button id="btnDelete">삭제</button>
			<button id="btnList">목록</button>
		</td>
	</tr>
</table>

<form name="frm" method="post">
	<input type="hidden" name="userid" value="${user.userid}">
</form>
</body>
</html>