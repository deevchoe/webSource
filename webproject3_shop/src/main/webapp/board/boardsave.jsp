<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 클라이언트로부터 받은 요청의 문자 인코딩을 UTF-8로 설정 -->
<% request.setCharacterEncoding("utf-8"); %>

<!-- 사용자가 입력한 데이터를 담을 BoardFormBean 인스턴스 생성 -->
<!-- 이를 통해 사용자가 작성한 게시글의 정보를 저장하고 관리. -->
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean" />
<!-- 사용자가 입력한 데이터를 bean 객체의 property에 설정 -->
<!-- property="*"는 모든 property에 대해 값을 설정한다는 의미 -->

<!-- 게시글을 관리하는 BoardMgr 클래스의 인스턴스를 생성 -->
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />

<%
bean.setBip(request.getRemoteAddr());	// 클라이언트의 ip address가 bean 객체의 setBip에 등록된다.
bean.setBdate(); // 현재 시스템이 가지고 있는 연월일
//현재 등록된 게시글 중 가장 큰 번호를 가져와서 그보다 1 큰 번호를 새로운 게시글의 번호로 설정
int newNum = boardMgr.currentMaxNum() + 1;
bean.setNum(newNum);
bean.setGnum(newNum);

// 사용자가 작성한 게시글을 데이터 베이스에 저장
boardMgr.saveData(bean);

response.sendRedirect("boardlist.jsp?page=1");
%>