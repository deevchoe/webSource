<%@page import="pack.product.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />
<%
ProductDTO dto = productMgr.getProduct(request.getParameter("no"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<%@ include file="admin_top.jsp"%>	<!-- 이걸 걸어줘야 로그인 한 사람만 업데이트 가능 -->
<form action="productproc.jsp?flag=update" method="post" enctype="multipart/form-data"> <!-- insert,update,delete할 때 똑같이 productproc.jsp를 부를거야   productproc에서 무슨 요청을 할거니?를 flag로 구분할거야 여기선 파일 업로드를 할거야 => insert 하나의 파일에서 따로 나눠서 하는걸 컨트롤러라고 해-->
<table>
	<tr>
		<td colspan="2">** 상품 수정 **</td>
	</tr>
	<tr>
		<td>상품명</td>
		<td><input type="text" name="name" value="<%=dto.getName() %>"></td>
	</tr>
	<tr>
		<td>가 격</td>
		<td><input type="text" name="price" value="<%=dto.getPrice() %>"></td>
	</tr>
	<tr>
		<td>설 명</td>
		<td><textarea rows="5" style="width:99%" name="detail"><%=dto.getDetail() %></textarea></td> <!-- detail은 db에서 타입을 text를 줬어 설명은 길수도 있으니까 textarea로 넣자 -->
	</tr>
	<tr>
		<td>재고량</td>
		<td><input type="text" name="stock" value="<%=dto.getStock() %>"></td>
	</tr>
	<tr>
		<td>이미지</td>	<!-- 파일의 이름을 넣을건데 이건 선택하는걸로 할게 -->
		<td>
			<img src="../upload/<%=dto.getImage() %>">
			<input type="file" name="image" size="30">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br>
			<input type="hidden" name="no" value="<%=dto.getNo() %>">
			<input type="submit" value="상품 수정">
			<input type="reset" value="수정 취소" onclick="history.back()">
		</td>
	</tr>
</table>
</form>
<%@ include file="admin_bottom.jsp"%>
</body>
</html>