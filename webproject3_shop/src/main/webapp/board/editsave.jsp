<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<!-- 두 줄 셋트. 한꺼번에 불러올거니까 formbean 소환 -->
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean" />

<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />

<%
String spage = request.getParameter("page");

boolean b = boardMgr.checkPass(bean.getNum(), bean.getPass()); // 비번 비교

if(b) {
	boardMgr.saveEdit(bean);
	response.sendRedirect("boardlist.jsp?page=" + spage);
}else{
%>
	<script>
		alert("비밀번호 불일치!");
		history.back();
	</script>
<%
}
%>