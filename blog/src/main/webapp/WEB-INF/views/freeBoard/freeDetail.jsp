<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container" id="wrap">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a href="/freeBoard/${board.id}/freeUpdateForm" class="btn btn-warning">수정</a>
		<button id="btn-freeDelete" class="btn btn-danger">삭제</button>
	</c:if>
	
	<c:if test="${empty principal.user.id}">
		<button disabled="disabled" onClick="index.freeLike(${board.id}, ${principal.user.id})"class="btn btn-danger"><span>추천👍${board.likeCount}</span></button>
	</c:if>
	<c:if test="${not empty principal.user.id}">
		<button  onClick="index.freeLike(${board.id}, ${principal.user.id})"class="btn btn-danger"><span>추천👍${board.likeCount}</span></button>
	</c:if>
	<br /> <br />
	<div>
		글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.user.username} </i></span> 조회수 : <span><i>${board.freeBoardCount} </i></span>
	</div>
	<br />
	<div>
		<h3>${board.freeBoardTitle}</h3>
	</div>
	<hr />
	<div>
		<div>${board.freeBoardContent}</div>
	</div>
	<hr />
<c:if test="${not empty principal.user.id}">
	<div class="card">
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" /> 
			<input type="hidden" id="freeBoardId" value="${board.id}" />
			<div class="card-body">
				<textarea id="freeReplyContent" class="form-control" rows="1"></textarea>
			</div>
			
				<div class="card-footer">
					<button type="button" id="btn-freeReply-save" class="btn btn-primary">등록</button>
				</div>
			
		</form>
	</div>
	</c:if>
	<br />
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.freeReplys}">

				<li id="freeReply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.freeReplyContent}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
			
						<c:if test="${reply.user.id eq principal.user.id}">
							<button onClick="index.freeReplyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
						</c:if>

					</div>
				</li>
				<!--  대댓글 시작 -->
				<br>
				<div class="card">
					<form>
						<input type="hidden" id="userId" value="${principal.user.id}" /> 
						<input type="hidden" id="freeBoardId" value="${board.id}" />
						<input type="hidden" id="freeReplyId" value="${reply.id}" />
						<div class="card-body">
							<textarea id="freeSubReplyContent" class="form-control" rows="1"></textarea>
						</div>
						<div class="card-footer">
							<button type="button" id="btn-freeSubReply-save" class="btn btn-primary">대댓등록</button>
						</div>
					</form>
				</div>
					<c:forEach var="subReply" items="${reply.subFreeReplys}">
						<li id="subReply" class="list-group-item d-flex justify-content-between">
							<div>${subReply.freeSubReplyContent}</div>
							<div class="d-flex">
							<div class="font-italic" >작성자 : ${subReply.user.username} &nbsp;</div>
							<c:if test="${subReply.user.id eq principal.user.id}">
								<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
							</c:if>
							

						</div>
					</li>
				</c:forEach> 
	     
				</c:forEach>
				
				
		
		</ul>
	</div>

</div>
<script src="/js/freeBoard.js"></script>
<%@ include file="../layout/footer.jsp"%>