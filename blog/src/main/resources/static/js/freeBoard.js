let index = {
		init: function(){
			$("#btn-freeSave").on("click", ()=>{ 
				this.save();
			});
			$("#btn-freeDelete").on("click", ()=>{ 
				this.deleteById();
			});
			$("#btn-freeUpdate").on("click", ()=>{ 
				this.update();
			});
			$("#btn-freeReply-save").on("click", ()=>{ 
				this.replySave();
			});
			$("#btn-freeSubReply-save").on("click", ()=>{ 
				this.subReplySave();
			});
		},

		save: function(){
			let data = {
					freeBoardTitle: $("#freeBoardTitle").val(),
					freeBoardContent: $("#freeBoardContent").val()
					
			};
		//	alert(content);
			
			$.ajax({ 
				type: "POST",
				url: "/api/freeBoard",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("자유게시판 글쓰기가 완료되었습니다.");
				location.href = "/freeBoard/freeMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		deleteById: function(){
			let id = $("#id").text();
			
			$.ajax({ 
				type: "DELETE",
				url: "/api/freeBoard/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/freeBoard/freeMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		update: function(){
			let id = $("#id").val();
			
			let data = {
					freeBoardTitle: $("#freeBoardTitle").val(),
					freeBoardContent: $("#freeBoardContent").val()
			};

			$.ajax({ 
				type: "PUT",
				url: "/api/freeBoard/"+id,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("자유게시판 글수정이 완료되었습니다.");
				location.href = "/freeBoard/freeMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		replySave: function(){
			let data = {
					userId: $("#userId").val(),
					freeBoardId: $("#freeBoardId").val(),
					freeReplyContent: $("#freeReplyContent").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: `/api/freeBoard/${data.freeBoardId}/freeReply`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("자유게시판 댓글작성이 완료되었습니다.");
				location.href = `/freeBoard/${data.freeBoardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		freeReplyDelete : function(boardId, replyId){
			let id = replyId;
			$.ajax({ 
				type: "DELETE",
				url: "/api/freeBoard/freeReply/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("자유게시판 댓글삭제 성공");
				location.href = `/freeBoard/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		subReplySave : function(){
			let freeBoardId = $("#freeBoardId").val();
		
			let data = {
					userId: $("#userId").val(),	
					freeReplyId: $("#freeReplyId").val(),
					freeSubReplyContent: $("#freeSubReplyContent").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: `/api/freeBoard/freeSubReply/${data.freeReplyId}`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("자유게시판 댓글작성이 완료되었습니다.");
				location.href = `/freeBoard/${freeBoardId}`;
				//location.href = `/`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
}

index.init();