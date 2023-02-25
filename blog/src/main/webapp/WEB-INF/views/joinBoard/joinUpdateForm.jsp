<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form>
		<input type="hidden" id="id" value="${board.id}"/>
		<div class="form-group">
			<label for="freeBoardTitle">Title</label>
			 <input value="${board.freeBoardTitle}" type="text" class="form-control" placeholder="Enter title" id="freeBoardTitle">
		</div>

	<div class="form-group">
 	 <label for="freeBoardContent">Content</label>
  	<textarea class="form-control summernote" rows="5" id="freeBoardContent">${board.freeBoardContent}</textarea>
	</div>
	</form>
	
	<button id="btn-freeUpdate" class="btn btn-primary">글수정완료</button>
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/freeBoard.js"></script>

<%@ include file="../layout/footer.jsp"%>

