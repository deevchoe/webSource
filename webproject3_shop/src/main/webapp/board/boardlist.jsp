<%@page import="pack.board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />
<jsp:useBean id="dto" class="pack.board.BoardDTO" />


<%
int spage = 1, pageSu = 0;	// 페이지 변수 초기화.
int start, end;	// 페이지를 나누기 위한 변수 선언.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">

<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnSearch").onclick = function(){
		if(frm.sword.value === ""){
			frm.sword.focus();
			frm.sword.placeholder = "검색어를 입력하세요";// 이건 value가 아니라 튤팁? 툴팁?이다.
			return;
		}
		frm.submit();
	}
}
</script>
</head>
<body>
<table>
	<tr>
		<td>
			<a href="../guest/guest_index.jsp">메인으로</a>
			<a href="boardlist.jsp?page=1">최근목록</a>&nbsp;
			<a href="boardwrite.jsp">새글작성</a>&nbsp;
			<a href="#" onclick="window.open('admin.jsp','','width=300,height=150,top=200,left=300')">관리자용</a>&nbsp; <!-- windows.open() 새창 띄우기 -->
			<br><br>
			<table style="width: 100%">
				<tr style="background-color: pink;">
					<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
				</tr>
				<%
				
				try{
					spage = Integer.parseInt(request.getParameter("page"));
				}catch(Exception e){
					spage = 1;	// 페이지 지정을 따로 안해줬을 때 1페이지 보여줌
				}
				if(spage <= 0) spage = 1;	// 페이지에 음수 지정해줬을때 1page로 이동
				
				// 검색일 경우 ------------------
				String stype = request.getParameter("stype");
				String sword = request.getParameter("sword");
				// ----------------------------
				
				boardMgr.totalList(); 	// 전체 레코드 수 계산
				pageSu = boardMgr.getPageSu(); 	// 전체 페이지 수 얻기
				
				//ArrayList<BoardDTO> list = boardMgr.getDataAll(spage);
				ArrayList<BoardDTO> list = boardMgr.getDataAll(spage, stype, sword);	// 검색도 처리 
				// boardMgr.getDataAll(spage, stype, sword) 메소드를 호출하여 게시글 목록을 가져온다.
				// spage : 현재 페이지 번호를 나타낸다.
				// stype : 검색 유형을 나타낸다. 제목인지 작성자인지.
				// sword : 검색어를 나타낸다. 검색 기능
				
				for(int i = 0; i < list.size(); i++){
					dto = (BoardDTO)list.get(i);
					// 댓글 들여쓰기 준비 -----------------
					int nst = dto.getNested();
					String tab = "";
					for(int k=0; k < nst; k++){
						tab += "&nbsp;&nbsp;";
					}
					// --------------------------------
				%>
				<tr>
					<td><%=dto.getNum() %></td>
					<td>
					<%=tab %><a href="boardcontent.jsp?num=<%=dto.getNum()%>&page=<%=spage%>"><%=dto.getTitle() %></a>
					</td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getBdate() %></td>
					<td><%=dto.getReadcnt() %></td>
				</tr>
				<%
				}
				%>
			</table>
			<br>
			<table style="width: 100%">
				<tr>
					<td style="text-align: center;">
					<%
					for(int i = 1; i <= pageSu; i++){
						if(i == spage){
							out.print("<b style='font-size:13pt;color:pink'>[" +i + "]</b>");							
						}else{
							out.print("<a href='boardlist.jsp?page=" + i +"'>[" +i + "]</a>");
						}
					}
					%>
					
					<br><br>
					<form action="boardlist.jsp" name="frm" method="get">
						<select name="stype">
						<option value="title" selected="selected">글제목</option>
						<option value="name">작성자</option>
						</select>
						<input type="text" name="sword">
						<input type="button" value="검색" id="btnSearch">
					</form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>