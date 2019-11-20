<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<c:set var ="n" value="${search}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- style="overflow:scroll" -->
<body>
	<section>
		<!-- style="visibility: hidden" -->
		<!-- <h1>주식회사검색</h1> -->
		<form action ="search" method="get">
			<div>
				<input type="text" name="search">
				<button>검색</button>
			</div>
		</form>
	</section>
		
	<section>
		<h1>추천 검색어</h1>
		<ul>
			<li>광동 사운드</li>
			<li>기현 알고리즘</li>
			<li>나람 금융</li>
			<li>명훈 소프트</li>
		</ul>
	</section>
	
	<section >
		<h1>주식회사 검색 결과</h1>
		<table border="1px">
			<thead>
				<tr>
					<td>번호</th>
					<td>종목명</td>
					<td>산업분류</td>
					<td>홈페이지</td>
					<td>관심유무?</td>
				</tr>
			</thead>
			
			<tbody>
			<%-- <c:forEach var="n" items="${search}"> --%>
				<tr>
					<th>1</th>
					<td><a href=""></a>${n.companyName}</td>
					<td>${n.stockItemName}</td>
					<td>${n.website}</td>
				</tr>
			<%-- </c:forEach> --%>
			</tbody>
			
			<!-- <tbody>
				<tr>
					<th>2</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>관심?</td>
					
				</tr>
			</tbody> -->
			
			
		</table>
	</section>
	
</body>
</html>