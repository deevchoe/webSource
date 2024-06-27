<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />

<%
request.setCharacterEncoding("utf-8");
String flag = request.getParameter("flag");
boolean result = false;

// controller 역할
if(flag.equals("insert")){
	result = productMgr.insertProduct(request); //클라이언트가 넘겨준 정보를 받을 수가 있다.
}else if(flag.equals("update")){
	result = productMgr.updateProduct(request);
}if(flag.equals("delete")){
	result = productMgr.deleteProduct(request.getParameter("no"));
}else{
	response.sendRedirect("productmanager.jsp");
}

if(result){
%>
	<script>
		alert("정상 처리되었습니다.");
		location.href="productmanager.jsp";
	</script>	
<%}else{%>
	<script>
		alert("오류 발생!");
		location.href="productmanager.jsp";
	</script>	

<%	
}
%>