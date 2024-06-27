<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session" />	<!-- cartMgr은 session이 살아있는 동안 유효하도록 scope를 세션으로 써줘 -->
<jsp:useBean id="order" class="pack.order.OrderBean" />
<jsp:setProperty property="*" name="order" />

<%
String orderFlag = request.getParameter("flag");	// 구매목록 보기, 수정, 삭제 판단용
String id = (String)session.getAttribute("idKey");	// 로그인 안하면 못보는 곳이니까 세션을 달아줬다.

//out.print(order.getProduct_no() + ", 주문 수량 : " + order.getQuantity());

if(id == null){
	response.sendRedirect("../member/login.jsp");	// 회원 로그인을 안한 경우
}else{
	if(orderFlag == null) {
		// cart에 주문 상품 담기
		order.setId(id);	// order : id, quantity, product_no
		cartMgr.addCart(order);		// 얘가 cart에 주문 상품 담기가 된다.
%>
		<script>
		alert("장바구니에 담았습니다.");
		location.href = "cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기     보통은 상품목록 보는데로 넘어가는데  장바구니로 갈거야
		</script>
<%
//	}else if(orderFlag == "update") {	// 문자 비교는 이렇게 하는거아니다
	}else if(orderFlag.equals("update")) {	// 문자 비교는 이렇게 하는거아니다
		order.setId(id);
		cartMgr.updateCart(order);
%>
		<script>
		alert("장바구니의 내용을 수정했습니다.");
		location.href = "cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기     보통은 상품목록 보는데로 넘어가는데  장바구니로 갈거야
		</script>
<%
	}else if(orderFlag.equals("del")) {
		cartMgr.deleteCart(order);
%>
	<script>
	alert("해당 상품의 주문을 삭제했습니다.");
	location.href = "cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기     보통은 상품목록 보는데로 넘어가는데  장바구니로 갈거야
	</script>
<%
	}
}
%>