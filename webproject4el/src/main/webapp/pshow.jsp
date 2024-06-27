<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 .expensive {	/* class가 expensive면 굵고 빨갛게 */
 	color: red;
 	font-weight: bold;
 }
</style>
</head>
<body>
<h2>* 제품 목록 *</h2>
<c:choose>
	<c:when test="${empty products}">
		<p>제품이 없어요 ㅠㅠ</p>
	</c:when>
	<c:otherwise>
		<table border="1">
			<thead>
				<tr>
					<th>제품명</th><th>가격</th><th>설명</th><th>출시일</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="pro" items="${products }">
				<tr>
					<td>${pro.name}</td> <!-- getName과 동일 -->
					<td class='<c:if test="${pro.price >= 5000.0}">expensive</c:if>'>
						${pro.price}	
					</td>
					<td>${pro.description}</td>
					<td>${pro.releaseDate}</td>
				</tr>			
			</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<h2>제품 통계</h2>
<c:set var="totalProducts" value="${fn:length(products)}" />
<c:set var="totalPrice" value="0" />
<c:forEach var="p" items="${products}" varStatus="status"> <!-- varStatus : 인덱스값을 얻을 수 있다? -->
	<c:set var="totalPrice" value="${totalPrice + p.price}" />
</c:forEach>
<p>전체 건수 : ${totalProducts }</p>
<p>가격 평균 : <fmt:formatNumber value="${totalPrice / totalProducts}" type="currency" /><!-- currency : 3자리마다 콤마 찍고 원화 표시가 될거야 --></p>

<h3>*제품 설명 *</h3>
<ul>
	<c:forEach var="p" items="${products}">
		<li><c:out value="${fn:substring(p.description, 0, 5)}..."/> </li> <!-- 0부터 5글자까지만 찍고 ...3개로 말줄임표시 -->
	</c:forEach>
</ul>
</body>
</html>