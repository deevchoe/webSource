<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<%@ include file="admin_top.jsp"%>
<form action="productproc.jsp?flag=insert" method="post" enctype="multipart/form-data"> <!-- insert,update,delete할 때 똑같이 productproc.jsp를 부를거야   productproc에서 무슨 요청을 할거니?를 flag로 구분할거야 여기선 파일 업로드를 할거야 => insert 하나의 파일에서 따로 나눠서 하는걸 컨트롤러라고 해-->
<table>
	<tr>
		<td colspan="2">** 상품 등록 **</td>
	</tr>
	<tr>
		<td>상품명</td>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>가 격</td>
		<td><input type="text" name="price"></td>
	</tr>
	<tr>
		<td>설 명</td>
		<td><textarea rows="5" style="width:99%" name="detail"></textarea></td> <!-- detail은 db에서 타입을 text를 줬어 설명은 길수도 있으니까 textarea로 넣자 -->
	</tr>
	<tr>
		<td>재고량</td>
		<td><input type="text" name="stock"></td>
	</tr>
	<tr>
		<td>이미지</td>	<!-- 파일의 이름을 넣을건데 이건 선택하는걸로 할게 -->
		<td><input type="file" name="image" size="30"></td>
	</tr>
	<tr>
		<td colspan="2">
		<br>
		<input type="submit" value="상품 등록">	<!-- 원래 이러면 안돼. 입력자료 검사하고 넘어가야해.. -->
		<input type="reset" value="새로 입력">
		</td>
	</tr>
</table>
</form>
<%@ include file="admin_bottom.jsp"%>
</body>
</html>