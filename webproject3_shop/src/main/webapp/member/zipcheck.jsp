<%@page import="pack.member.ZipcodeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.MemberMgr" />

<%
request.setCharacterEncoding("utf-8");
String check = request.getParameter("check"); // y(동이름 입력하면 출력) or n
String dongName = request.getParameter("dongName");

ArrayList<ZipcodeDTO> zlist = memberMgr.zipcodeRead(dongName);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnZipFind").onclick = dongCheck;
	document.querySelector("#btnZipClose").onclick = function(){
		window.close();
	}
}

function dongCheck(){
	if(zipForm.dongName.value === ""){
		alert("검색할 동이름을 입력하세요");
		zipForm.dongName.focus();
		return;
	}
	zipForm.submit();
}

function sendFunc(zipcode, area1, area2, area3, area4){
	// register.jsp 파일의 우편번호(zipcode)에 zipcode, 주소(address)에 area들을 자동입력
	// opener는 해당 파일을 open한 아이. 여기서는 register.jsp가 됨
	opener.document.regForm.zipcode.value = zipcode;
	opener.document.regForm.address.value = area1 + " " + area2 + " " + area3 + " " + area4;
	window.close(); // 주소 검색 창 닫기
}
</script>
</head>
<body>
<b>** 우편번호 자료 찾기 **</b>
<form action="zipcheck.jsp" name="zipForm" method="post">
<table>
	<tr>
		<td>
		동 이름 입력: <input type="text" name="dongName">
		<input type="button" value="검색" id="btnZipFind">
		<input type="button" value="닫기" id="btnZipClose">
		<input type="hidden" name="check" value="n">
		</td>
	</tr>
</table>
</form>

<%
if(check.equals("n")){
	if(zlist.isEmpty()){
%>
	<b>검색 결과 없음</b>
<%		
	} else {
%>
	<table>
		<tr>
			<td style="text-align: center;">
			검색 자료 클릭 시 자동으로 주소가 입력됩니다.
			</td>
		</tr>
		<tr>
			<td>
			<%
			for(int i=0; i<zlist.size(); i++){
				ZipcodeDTO dto = (ZipcodeDTO)zlist.get(i);
				String zipcode = dto.getZipcode();
				String area1 = dto.getArea1();
				String area2 = dto.getArea2();
				String area3 = dto.getArea3();
				String area4 = dto.getArea4();
				if(area4 == null) area4 = "";
			%>
			<a href="javascript:sendFunc('<%=zipcode %>', '<%=area1 %>', '<%=area2 %>', '<%=area3 %>', '<%=area4 %>')">
			<%=zipcode %> <%=area1 %> <%=area2 %> <%=area3 %> <%=area4 %>
			</a>
			<br>
			<%
			}
			%>
			</td>
		</tr>
	</table>
<%		
	}
}	
%>
</body>
</html>