<%@page import="java.util.Map"%>
<%@page import="pack.order.OrderMgr"%>
<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session"/>
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr" />
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />

<%
//Hashtable hCart = cartMgr.getCartList();
//Enumeration enu = hCart.keys();
Hashtable<String, OrderBean> hCart = (Hashtable<String, OrderBean>)cartMgr.getCartList();

if(hCart.isEmpty()){
%>
	<script>
	alert("주문 내역이 없습니다.");
	location.href="orderlist.jsp";
	</script>
<%
}else{
	/*
	while(enu.hasMoreElements()){
		OrderBean orderBean = (OrderBean)hCart.get(enu.nextElement());	// 카트에 있는 내용을 하나씩 가져오는거야ㅏ
		// 아래 3개의 작업이 이루어지고 있다. 아래 두줄은 트랜잭션 처리 할 수 있다.
		orderMgr.insertOrder(orderBean);	// 주문정보 db에 저장
		productMgr.reduceProduct(orderBean);	// 주문 수량만큼 재고량 빼기 작업
		cartMgr.deleteCart(orderBean);
	}
	*/
	
	for(Map.Entry<String, OrderBean> entry:hCart .entrySet()){
		OrderBean orderBean = entry.getValue();	// 카트에 있는 내용을 하나씩 가져오는거야ㅏ
		// 아래 3개의 작업이 이루어지고 있다. 아래 두줄은 트랜잭션 처리 할 수 있다.
		orderMgr.insertOrder(orderBean);	// 주문정보 db에 저장
		productMgr.reduceProduct(orderBean);	// 주문 수량만큼 재고량 빼기 작업
		cartMgr.deleteCart(orderBean);
	}
	
	
%>
	<script>
	alert("주문 처리가 잘 되었습니다.\n고객님 감사합니다:D");
	location.href="orderlist.jsp";
	</script>
<%
}
%>