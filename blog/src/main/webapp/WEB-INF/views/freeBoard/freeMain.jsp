<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container" id="wrap">



<H2 >자유게시판</H2>


<a class="btn btn-primary" href="/freeBoard/freeMain?sort=freeBoardCount,DESC&field=${param.field}&searchText=${param.searchText}">조회순</a>
<a class="btn btn-primary" href="/freeBoard/freeMain?sort=freeBoardTitle,ASC&field=${param.field}&searchText=${param.searchText}">제목순</a>
<a class="btn btn-primary" href="/freeBoard/freeMain?sort=userId,DESC&field=${param.field}&searchText=${param.searchText}">작성자순</a>

 	<form method="GET"  action="/freeBoard/freeMain" class="form-inline d-Flex justify-content-end" role="search">
			<select name="field" id="field" class="form-control form-control-sm">
				<!-- <option value="TitleOrContent">제목+내용</option> -->
				<option value="title">제목</option>
				<option value="content">내용</option> 
			</select>
        <input type="text" name="searchText" class="form-control" id="searchText" placeholder="검색" >
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

<c:forEach var="board" items="${freeboards.content}">
	<tbody>
		<tr>
			<th>${board.id }</th>
			<th><a href="/freeBoard/${board.id}"  style="color:black; text-decoration:none;"> ${board.freeBoardTitle }</th></a>
			<th>${board.user.username }</th>
			<th>	<fmt:parseDate value="${board.createDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="lastupdate" type="both" />
					<fmt:formatDate pattern="yyyy-MM-dd" value="${lastupdate}" /></th>
			<th>${board.freeBoardCount }</th>
		</tr>
	</tbody>
</c:forEach>
</table>

<c:if test="${not empty principal}">
<a class="btn btn-primary" href="/freeBoard/freeSaveForm">글쓰기</a>
</c:if>
<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">
		
			<!-- 이전 -->
			<c:choose>
				<c:when test="${freeboards.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/freeBoard/freeMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=0">맨처음</a></li>
					<li class="page-item"><a class="page-link" href="/freeBoard/freeMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${freeboards.number-1}">이전</a></li>
				</c:otherwise>
			</c:choose>
			
			<!-- 페이지 그룹 -->
			<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
				<c:choose>
					<c:when test="${freeboards.pageable.pageNumber+1 == i}">
						<li class="page-item disabled"><a class="page-link" href="/freeBoard/freeMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${i-1}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="/freeBoard/freeMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${i-1}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
				<c:when test="${freeboards.last}"></c:when>
				<c:otherwise>
					<li class="page-item "><a class="page-link" href="/freeBoard/freeMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${freeboards.number+1}">다음</a></li>
					<li class="page-item "><a class="page-link" href="/freeBoard/freeMain?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${freeboards.totalPages-1}">맨끝</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div> 
	<!-- 페이징 영역 끝 -->
</div>

	

<%@ include file="../layout/footer.jsp"%>

