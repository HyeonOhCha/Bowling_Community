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
import com.cos.blog.dto.GalleryReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.GalleryBoard;
import com.cos.blog.service.GalleryBoardService;

@RestController
public class GalleryBoardApiController {
	
	@Autowired
	private GalleryBoardService galleryBoardService;
	

	@PostMapping("/api/galleryboard")
	public ResponseDto<Integer> save(@RequestBody GalleryBoard galleryBoard, @AuthenticationPrincipal PrincipalDetail principal) {
		galleryBoardService.글쓰기(galleryBoard, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
	@DeleteMapping("/api/galleryboard/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		galleryBoardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/galleryboard/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody GalleryBoard galleryBoard){
		galleryBoardService.글수정하기(id,galleryBoard);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	// dto 사용하지 않은 이유는!! 
	@PostMapping("/api/galleryBoard/{boardId}/galleryReply")
	public ResponseDto<Integer> replySave(@RequestBody GalleryReplySaveRequestDto galleryReplySaveRequestDto) {
		galleryBoardService.댓글쓰기(galleryReplySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/galleryBoard/{boardId}/galleryReply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		galleryBoardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	

}
