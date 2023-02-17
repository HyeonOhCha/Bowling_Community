<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">



<H2 >자유게시판</H2>

 	<form method="GET"  action="/freeBoard/freeMain" class="form-inline d-Flex justify-content-end" role="search">
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
<a class="btn btn-primary" href="/freeBoard/freeSaveForm">글쓰기</a>

<ul class="pagination justify-content-center" >
  <c:choose>
  	<c:when test="${freeboards.first}">
  		 <li class="page-item disabled"><a class="page-link" href="?page=${freeboards.number-1}">Previous</a></li>
  	</c:when>
  	
  	<c:otherwise>
  		<li class="page-item "><a class="page-link" href="?page=${freeboards.number-1}">Previous</a></li>
  	</c:otherwise>
  </c:choose>
  
  <c:choose>
   	<c:when test="${freeboards.last}">
  		 <li class="page-item disabled"><a class="page-link" href="?page=${freeboards.number+1}">Next</a></li>
  	</c:when>
  	
  	<c:otherwise>
  		<li class="page-item"><a class="page-link" href="?page=${freeboards.number+1}">Next</a></li>
  	</c:otherwise>
  </c:choose>
  
  
</ul>

	

</div>

<%@ include file="../layout/footer.jsp"%>

