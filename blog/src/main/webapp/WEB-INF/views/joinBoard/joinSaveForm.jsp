<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form>
		<div class="form-group">
			<label for="joinBoardTitle">Title</label>
			 <input type="text" class="form-control" placeholder="Enter title" id="joinBoardTitle">
	
		</div>

	<div class="form-group">
 	 <label for="joinBoardContent">Content</label>
  	<textarea class="form-control summernote" rows="5" id="joinBoardContent"></textarea>
	</div>
	</form>
	
	<button id="btn-joinSave" class="btn btn-primary">글쓰기완료</button>
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/joinBoard.js"></script>

<%@ include file="../layout/footer.jsp"%>

