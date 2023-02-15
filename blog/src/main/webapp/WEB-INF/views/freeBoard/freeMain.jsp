<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

<a class="btn btn-primary" href="/freeBoard/freeSaveForm">글쓰기</a>


<c:forEach var="board" items="${freeboards.content}">
	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title" >	<a href="/freeBoard/${board.id}"  style="color:black; text-decoration:none;">${board.freeBoard_title }</a></h4>
		</div>
	</div>
</c:forEach>

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

