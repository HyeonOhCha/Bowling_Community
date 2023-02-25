package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.FreeReplySaveRequestDto;
import com.cos.blog.dto.JoinReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.FreeBoard;
import com.cos.blog.model.JoinBoard;
import com.cos.blog.service.JoinBoardService;

@RestController
public class JoinBoardApiController {
	
	@Autowired
	private JoinBoardService joinboard_Service;
	

	@PostMapping("/api/joinBoard")
	public ResponseDto<Integer> save(@RequestBody JoinBoard joinBoard, @AuthenticationPrincipal PrincipalDetail principal) {
		joinboard_Service.joinBoard_write(joinBoard, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/joinoard/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		joinboard_Service.joinBoard_delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/joinBoard/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody JoinBoard joinBoard){
		joinboard_Service.joinBoard_update(id,joinBoard);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
//	 dto 사용하지 않은 이유는!! FreeReplySaveRequestDto
	@PostMapping("/api/joinBoard/{joinBoardId}/joinReply")
	public ResponseDto<Integer> replySave(@RequestBody JoinReplySaveRequestDto joinRequest) {
		joinboard_Service.joinReply_write(joinRequest);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/joinBoard/joinReply/{id}")
	public ResponseDto<Integer> replyDelete(@PathVariable int id) {
		joinboard_Service.joinReply_delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
//	/대댓글용
//	@PostMapping("/api/freeBoard/{freeBoardId}/freeSubReply")
//	public ResponseDto<Integer> subReplySave(@RequestBody FreeReplySaveRequestDto freeRequest) {
//		freeboard_Service.freeReply_write(freeRequest);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
//	}
	
//	@DeleteMapping("/api/freeBoard/freeSubReply/{id}")
//	public ResponseDto<Integer> subReplyDelete(@PathVariable int id) {
//		freeboard_Service.freeReply_delete(id);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
//	}


}
