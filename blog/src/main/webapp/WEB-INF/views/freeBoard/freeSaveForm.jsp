<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form>
		<div class="form-group">
			<label for="freeBoardTitle">Title</label>
			 <input type="text" class="form-control" placeholder="Enter title" id="freeBoardTitle">
	
		</div>

	<div class="form-group">
 	 <label for="freeBoardContent">Content</label>
  	<textarea class="form-control summernote" rows="5" id="freeBoardContent"></textarea>
	</div>
	</form>
	
	<button id="btn-freeSave" class="btn btn-primary">글쓰기완료</button>
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/freeBoard.js"></script>

<%@ include file="../layout/footer.jsp"%>

