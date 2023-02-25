<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container" id="wrap">



<H2>회원목록</H2>

 	<form method="GET"  action="/user/userList" class="form-inline d-Flex justify-content-end" role="search">
			<select name="field" id="field" class="form-control form-control-sm">
				<option value="username">회원이름</option>
				<option value="email">회원이메일</option> 
			</select>
        <input type="text" name="searchText" class="form-control" id="searchText" placeholder="검색" >
        <button type="submit" class="btn btn-primary mb-2">검색</button>
    </form> 


<table class="table table-hover table-striped text-center" style="border: 1px solid;">
	<thead>
		<tr>		
			<th>회원번호</th>
			<th>회원이름</th>
			<th>회원이메일</th>
			<th>가입날짜</th>
		</tr>
	</thead>

<c:forEach var="user" items="${userList.content}">
	<tbody>
		<tr>
			<th>${user.id }</th>
			<th>${user.username}</th>
			<th>${user.email}</th>
			<th><fmt:formatDate pattern="yyyy-MM-dd" value="${user.createDate}" /></th> 
		</tr>
	</tbody>
</c:forEach>
</table>

<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">
		
			<!-- 이전 -->
			<c:choose>
				<c:when test="${userList.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/user/userList?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=0">맨처음</a></li>
					<li class="page-item"><a class="page-link" href="/user/userList?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${userList.number-1}">이전</a></li>
				</c:otherwise>
			</c:choose>
			
			<!-- 페이지 그룹 -->
			<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
				<c:choose>
					<c:when test="${userList.pageable.pageNumber+1 == i}">
						<li class="page-item disabled"><a class="page-link" href="/user/userList?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${i-1}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="/user/userList?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${i-1}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
				<c:when test="${userList.last}"></c:when>
				<c:otherwise>
					<li class="page-item "><a class="page-link" href="/user/userList?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${userList.number+1}">다음</a></li>
					<li class="page-item "><a class="page-link" href="/user/userList?sort=${sort}&field=${param.field}&searchText=${param.searchText}&page=${userList.totalPages-1}">맨끝</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div> 
	<!-- 페이징 영역 끝 --> 
</div>

	

<%@ include file="../layout/footer.jsp"%>

