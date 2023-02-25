0<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">



	<H2>조인게시판</H2>


	<a class="btn btn-primary" href="/joinBoard/joinMain?sort=joinBoardCount,DESC&field=${param.field}&searchText=${param.searchText}">조회순</a> <a class="btn btn-primary"
		href="/joinBoard/joinMain?sort=joinBoardTitle,ASC&field=${param.field}&searchText=${param.searchText}">제목순</a> <a class="btn btn-primary"
		href="/joinBoard/joinMain?sort=userId,DESC&field=${param.field}&searchText=${param.searchText}">작성자순</a>  
	<form method="GET" action="/joinBoard/joinMain" class="form-inline d-Flex justify-content-end" role="search">
		<select name="field" id="field" class="form-control form-control-sm">
			<!-- <option value="TitleOrContent">제목+내용</option> -->
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>         <input type="text" name="searchText" class="form-control" id="searchText" placeholder="검색">         
		<button type="submit" class="btn btn-primary mb-2">검색</button>
		    
	</form>


	<table class="table table-hover table-striped text-center" style="border: 1px solid;">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>

		<c:forEach var="board" items="${joinboards.content}">
			<tbody>
				<tr>
					<th>${board.id }</th>
					<th><a href="/joinBoard/${board.id}" style="color: black; text-decoration: none;"> ${board.joinBoardTitle }</th>
					</a>
					<th>${board.user.username }</th>
					<th><fmt:parseDate value="${board.createDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="lastupdate" type="both" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${lastupdate}" /></th>
					<th>${board.joinBoardCount }</th>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<c:if test="${not empty principal}">
		<div>
			<a class="btn btn-primary" href="/joinBoard/joinSaveForm">글쓰기</a>
		</div>
	</c:if>
</div>

<!-- 페이징 영역 시작 -->
<div class="text-xs-center">
	<ul class="pagination justify-content-center">

		<!-- 이전 -->
		<c:choose>
			<c:when test="${joinboards.first}"></c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="/joinBoard/joinMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=0">처음</a></li>
				<li class="page-item"><a class="page-link" href="/joinBoard/joinMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${joinboards.number-1}">&larr;</a></li>
			</c:otherwise>
		</c:choose>

		<!-- 페이지 그룹 -->
		<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
			<c:choose>
				<c:when test="${joinboards.pageable.pageNumber+1 == i}">
					<li class="page-item disabled"><a class="page-link" href="/joinBoard/joinMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${i-1}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/joinBoard/joinMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${i-1}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 다음 -->
		<c:choose>
			<c:when test="${joinboards.last}"></c:when>
			<c:otherwise>
				<li class="page-item "><a class="page-link" href="/joinBoard/joinMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${joinboards.number+1}">&rarr;</a></li>
				<li class="page-item "><a class="page-link" href="/joinBoard/joinMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${joinboards.totalPages-1}">마지막</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<!-- 페이징 영역 끝 -->

<%@ include file="../layout/footer.jsp"%>