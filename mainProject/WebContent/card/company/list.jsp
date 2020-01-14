<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%-- <c:set var ="n" value="${search}"/> --%>

<html>
<head>

<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<link rel="stylesheet" type="text/css" href="../../css/company/list.css">
<script src="../../js/company/list.js"></script>
<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2019.3.1023/styles/kendo.default-v2.min.css"/>

    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.3.1023/js/kendo.all.min.js"></script>
</head>




<body class="scrollbar custom-scrollbar-style">
	
	
	<section id="recommendKeyword">
		<ul>
			<li id="recommendKeyword_fixed">인기 검색어 또는 종목명을 검색해보세요.</li>

			<%-- <c:forEach var="r" items="${recommendKeyword}">
				<a href="list?companyName=${r}"><span>${r}</span></a>
				<span id = ajaxTest>${r}</span>
			</c:forEach> --%>
			
		</ul>
	</section>
	
	
	<div>
	<section id="search-form">
		<h1 class="d-none">주식회사검색</h1>
		<form action ="list" method="get">
			<div id ="search-div">
				<!-- <label for= "search-text"> -->
					<input id ="search-text" type="text" name="companyName">
					<button class="search-button"></button>
				<!-- </label> -->
			</div>
		</form>
	</section>
		
	
	
	<section id ="search-result">
		<h1 class="d-none">주식회사 검색 결과</h1>
		<table>
			<thead>
				<tr>
					<td>번호</td>
					<td>종목명</td>
					<td>산업분류</td>
					<td>관심</td>
				</tr>
			</thead>
			
			
			<tbody>
				<c:forEach var="sector" items="${search}" varStatus="status" >
				<tr>
					<td>${status.count}</td>
						
					<td id="companyNameWebsite">
						<div data-codenum="${sector.stockCode }" class="companyName">${sector.companyName}
						<a href="${sector.website}" target="_blank"><img src="/css/company/link.png"></a></div>
					</td>
					
					<%-- <td id ="stockItemName" class="wrap">
						<a>${sector.mainProduct}</a>
						<div class="help">${sector.mainProduct}</div>
					</td> --%>
					
					<div id="example">
						<td>${sector.mainProduct}</td>
					</div>
					
					
					
					
					
					<!-- 로그인 체크 -->
					
					<c:if test="${logIn == -1}">
						<td class="interest_no" id="UncertifiedLogin">
						</td>
					</c:if>
					
					<c:if test="${logIn != -1}">
					<!-- 값이 들어감  -->
						<c:set target="${interestStocks}" var="interestStocksListCheck"/>
							<c:choose>
								<c:when test="${not empty interestStocks }">
									<!-- 값이 들어감 -->
									<c:forEach var="interestStocks" items="${interestStocks}" varStatus="status1" >
											<c:choose>
												<c:when test="${sector.stockCode eq interestStocks.stockCode}">
													<td class="interest_yes" data-attention="${sector.stockCode}" id="certifiedLoing">
													</td>
												</c:when>
												<c:otherwise>
													<td class="interest_no" data-attention="${sector.stockCode}" id="certifiedLoing">
		                        					</td>
												</c:otherwise>
											</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<td class="interest_no" data-attention="${sector.stockCode}" id="certifiedLoing">
		                        	</td>
								</c:otherwise>
							</c:choose>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
			
			
			<script>
		      $("#example").kendoTooltip({
		        filter: "td",
		        show: function(e){
		          if(this.content.text() !=""){
		            $('[role="tooltip"]').css("visibility", "visible");
		          }
		        },
		        hide: function(){
		          $('[role="tooltip"]').css("visibility", "hidden");
		        },
		        content: function(e){
		          var element = e.target[0];
		          if(element.offsetWidth < element.scrollWidth){
		            return e.target.text();
		          }else{
		            return "";
		          }
		        }
		      })
			</script>
			
		</table>
	</section>
	</div>
</body>
</html>