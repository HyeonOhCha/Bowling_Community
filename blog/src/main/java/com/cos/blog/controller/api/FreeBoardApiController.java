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
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.FreeBoard;
import com.cos.blog.service.FreeBoardService;

@RestController
public class FreeBoardApiController {
	
	@Autowired
	private FreeBoardService freeboard_Service;
	

	@PostMapping("/api/free_board")
	public ResponseDto<Integer> save(@RequestBody FreeBoard free_board, @AuthenticationPrincipal PrincipalDetail principal) {
		freeboard_Service.freeBoard_write(free_board, principal.getUser());
		System.out.println("FreeBoardApiController !!");
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/freeBoard/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		freeboard_Service.freeBoard_delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/freeBoard/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody FreeBoard free_board){
		freeboard_Service.freeBoard_update(id,free_board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	// dto 사용하지 않은 이유는!! FreeReplySaveRequestDto
	@PostMapping("/api/freeBoard/{free_boardId}/freeReply")
	public ResponseDto<Integer> replySave(@RequestBody FreeReplySaveRequestDto freeRequest) {
		freeboard_Service.freeReply_write(freeRequest);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/freeBoard/freeReply/{id}")
	public ResponseDto<Integer> replyDelete(@PathVariable int id) {
		freeboard_Service.freeReply_delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}


}
