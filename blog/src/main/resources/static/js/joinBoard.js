let index = {
		init: function(){
			$("#btn-joinSave").on("click", ()=>{ 
				this.save();
			});
			$("#btn-joinDelete").on("click", ()=>{ 
				this.deleteById();
			});
			$("#btn-joinUpdate").on("click", ()=>{ 
				this.update();
			});
			$("#btn-joinReply-save").on("click", ()=>{ 
				this.replySave();
			});
		},

		save: function(){
			let data = {
					joinBoardTitle: $("#joinBoardTitle").val(),
					joinBoardContent: $("#joinBoardContent").val()
					
			};
		//	alert(content);
			
			$.ajax({ 
				type: "POST",
				url: "/api/joinBoard",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("글쓰기가 완료되었습니다.");
				location.href = "/joinBoard/joinMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		deleteById: function(){
			let id = $("#id").text();
			
			$.ajax({ 
				type: "DELETE",
				url: "/api/joinBoard/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/joinBoard/joinMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		update: function(){
			let id = $("#id").val();
			
			let data = {
					joinBoardTitle: $("#joinBoardTitle").val(),
					joinBoardContent: $("#joinBoardContent").val()
			};

			$.ajax({ 
				type: "PUT",
				url: "/api/joinBoard/"+id,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("글수정이 완료되었습니다.");
				location.href = "/joinBoard/joinMain";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		replySave: function(){
			let data = {
					userId: $("#userId").val(),
					joinBoardId: $("#joinBoardId").val(),
					joinReplyContent: $("#joinReplyContent").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: `/api/joinBoard/${data.joinBoardId}/joinReply`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("댓글작성이 완료되었습니다.");
				location.href = `/joinBoard/${data.joinBoardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		joinReplyDelete : function(boardId, replyId){
			let id = replyId;
			$.ajax({ 
				type: "DELETE",
				url: "/api/joinBoard/joinReply/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("댓글삭제 성공");
				location.href = `/joinBoard/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
}

index.init();