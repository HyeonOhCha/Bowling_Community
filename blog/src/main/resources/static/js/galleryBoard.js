let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ 
				this.save();
			});
			$("#btn-delete").on("click", ()=>{ 
				this.deleteById();
			});
			$("#btn-update").on("click", ()=>{ 
				this.update();
			});
			$("#btn-reply-save").on("click", ()=>{ 
				this.replySave();
			});
		},

		save: function(){
			let data = {
					title: $("#title").val(),
					content: $("#content").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: "/api/galleryboard",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("갤러리게시판 글쓰기가 완료되었습니다.");
				location.href = "/galleryBoard/galleryMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		deleteById: function(){
			let id = $("#id").text();
			
			$.ajax({ 
				type: "DELETE",
				url: "/api/galleryboard/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("갤러리게시판 글삭제가 완료되었습니다.");
				location.href = "/galleryBoard/galleryMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		update: function(){
			let id = $("#id").val();
			
			let data = {
					title: $("#title").val(),
					content: $("#content").val()
			};

			$.ajax({ 
				type: "PUT",
				url: "/api/galleryboard/"+id,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("갤러리게시판 글수정이 완료되었습니다.");
				location.href = "/galleryBoard/galleryMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		replySave: function(){
			let data = {
					userId: $("#userId").val(),
					boardId: $("#boardId").val(),
					content: $("#reply-content").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: `/api/galleryBoard/${data.boardId}/galleryReply`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("갤러리게시판 댓글작성이 완료되었습니다.");
				location.href = `/galleryBoard/${data.boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		replyDelete : function(boardId, replyId){
			$.ajax({ 
				type: "DELETE",
				url: `/api/galleryBoard/${boardId}/galleryReply/${replyId}`,
				dataType: "json"
			}).done(function(resp){
				alert("갤러리게시판 댓글삭제 성공");
				location.href = `/galleryBoard/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
}

index.init();