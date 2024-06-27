<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String adminId = (String)session.getAttribute("adminOk");	// 요 이름은 boardcontent adminOk라는 이름으로 있다.

if(adminId == null){
	response.sendRedirect("adminlogin.jsp");
	//return;		// 이 말 안써도돼. adminlogin.jsp 만나면 만나게될거니까!!
}
%>
<table>
	<tr style="background-color: gray; text-align: center; ">
		<td><a href="../guest/guest_index.jsp">홈페이지</a></td>
		<td><a href="adminlogout.jsp">로그아웃</a></td>	<!-- 로그인 했을때만 보여 -->
		<td><a href="membermanager.jsp">회원관리</a></td>
		<td><a href="productmanager.jsp">상품관리</a></td>
		<td><a href="ordermanager.jsp">주문관리</a></td>
	</tr>
</table>