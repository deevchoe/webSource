<%@page import="pack.product.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />
<%
String no = request.getParameter("no");

ProductDTO dto = productMgr.getProduct(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2>* 상품 상세 보기 *</h2>
<%@ include file="guest_top.jsp"%>
<form action="cartproc.jsp">
<table>
	<tr>
		<td style="width: 30%">
			<img src="../upload/<%=dto.getImage() %>" width="150" />
		</td>
		<td style="width: 50%; vertical-align: top">
			<table style="width: 100%">
				<tr><td>번호 : <%=dto.getNo() %></td></tr>
				<tr><td>상품명 : <%=dto.getName() %></td></tr>
				<tr><td>가격 : <%=dto.getPrice() %></td></tr>
				<tr><td>등록일 : <%=dto.getSdate() %></td></tr>
				<tr><td>재고수 : <%=dto.getStock() %></td></tr>
				<tr>
					<td>주문 수량 : </td> <!-- 이 부분이 제일 중요해!!! -->
					<td>
						<input type="number" min="1" value="1" 
							name="quantity"
							style="text-align: center; width:3cm">
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;">
			<h3>* 상품 설명 *</h3>
			<%=dto.getDetail() %>
		</td>
	</tr>
	<tr>
		<td colspan="3" style="text-align: center;">
			<br>
			<input type="hidden" name="product_no" value="<%=dto.getNo() %>">
			<input type="submit" value="장바구니에 담기">
			<input type="button" value="이전 페이지로 이동" onclick="history.back()">
		</td>
	</tr>
</table>
</form>
<%@ include file="guest_bottom.jsp"%>
</body>
</html>