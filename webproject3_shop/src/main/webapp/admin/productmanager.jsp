<%@page import="pack.product.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
** 전체 상품목록(관리자) ** <p/>

<%@ include file="admin_top.jsp"%>
<table>
	<tr style="background-color: silver;">
		<th>번호</th><th>상품명</th><th>가격</th><th>등록일</th><th>재고량</th><th>상세보기</th>
		<%
		ArrayList<ProductDTO> plist = productMgr.getproductAll();
		//if(plist.isEmpty == 0) {
		if(plist.size() == 0) {
		%>
		<tr>
			<td colspan="6">등록된 상품이 없습니다.
		</tr>
		<%
		}else{
			for(ProductDTO p:plist) {
		%>
		<tr style="text-align: center;">
			<td><%=p.getNo() %></td>
			<td><%=p.getName() %></td>
			<td><%=p.getPrice() %></td>
			<td><%=p.getSdate() %></td>
			<td><%=p.getStock() %></td>
			<td>
				<a href="javascript:productDetail('<%=p.getNo() %>')">보기</a>
			</td>
		</tr>
		<%
			}
		}
		%>
	</tr>
	<tr>
		<td colspan="6">
			[<a href="productinsert.jsp">상품 등록</a>]	<!-- 로그인하지 않으면 볼 수 없도록 처리하려 함 -->
		</td>
	</tr>
</table>	
<%@ include file="admin_bottom.jsp"%>

<form action="productdetail.jsp" name="detailForm" method="get">
	<input type="hidden" name="no">
</form>
</body>
</html>