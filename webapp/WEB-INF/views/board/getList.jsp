<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- //header -->
		<!-- //nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<tbody>
						
								<%-- 리스트 출력 --%>
								<%
									if (list != null && paging != null) {
										for (int i = 0; i < list.length; i++) {
								%>
						
								<tr>
									<td style="width: 60%">
										<!-- 게시물 제목, 답글에 대한 인덴트 적용 --> <%
											if (list[i].getBdIndent() > 0) {
													for (int j = 0; j < list[i].getBdIndent(); j++) {
										%> &nbsp&nbsp&nbsp&nbsp <%
											}
										%> └ re:&nbsp <%
											}
										%> <a class="text-reset"
										href="board/requestBdCont?requestBdNum=<%=list[i].getBdNum()%>">
											<%=list[i].getBdTitle()%></a>
									</td>
									<!-- 작성자, 작성일, 조회수 -->
									<td style="width: 10%" class="text-center"><%=list[i].getBdUserID()%></td>
									<td style="width: 20%" class="text-center"><%=list[i].getBdDate()%></td>
									<td style="width: 10%" class="text-center"><%=list[i].getBdViewCount()%></td>
								</tr>
						
								<%
									}
								%>
							
							<c:forEach items="${boardList}" var="boardVo">
								<tr>
									<td>${boardVo.no }</td>
									<td class="text-left"><a href="${pageContext.request.contextPath }/board/read?no=${boardVo.no }">${boardVo.title}</a></td>
									<td>${boardVo.name}</td>
									<td>${boardVo.hit}</td>
									<td>${boardVo.regdate}</td>
									<td>
										<c:if test="${boardVo.userno == authUser.no }">
											<a href="${pageContext.request.contextPath }/board/remove?no=${boardVo.no }">[삭제]</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
					<!-- 페이징 목록 처리 -->
					<p class="text-center">
					
						<%
							for (int i = paging[0]; i <= paging[1]; i++) {
						%>
					
						<a class="text-reset" href="/boardChat?requestedPage=<%=i%>"><%=i%></a>&nbsp&nbsp
					
						<%
							}
						%>
					</p>
					
											
											
					<div class="clear"></div>
					</div>
					<c:if test="${authUser != null}">
						<a id="btn_write" href="${pageContext.request.contextPath }/board/writeForm">글쓰기</a>
					</c:if>
				
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>

</html>