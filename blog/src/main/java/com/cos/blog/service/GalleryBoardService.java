package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.GalleryReplySaveRequestDto;
import com.cos.blog.model.GalleryBoard;
import com.cos.blog.model.User;
import com.cos.blog.repository.GalleryBoardRepository;
import com.cos.blog.repository.GalleryReplyRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IoC를 해준다
@Service
public class GalleryBoardService {

	@Autowired
	private GalleryBoardRepository galleryBoardRepository;

	@Autowired
	private GalleryReplyRepository galleryReplyRepository;

	@Transactional
	public void 글쓰기(GalleryBoard galleryBoard, User user) { // title , content
		galleryBoard.getGalleryBoardCount();
		galleryBoard.setUser(user);
		galleryBoardRepository.save(galleryBoard);
	}

	@Transactional(readOnly = true)
	public Page<GalleryBoard> 글목록(Pageable pageable) {

		return galleryBoardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public GalleryBoard 글상세보기(int id) {
		return galleryBoardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}
	
	@Transactional
	public int CountUp(int id) {
		return galleryBoardRepository.CountUp(id);
	}

	@Transactional
	public void 글삭제하기(int id) {
		galleryBoardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, GalleryBoard requestBoard) {
		GalleryBoard galleryBoard = galleryBoardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		galleryBoard.setTitle(requestBoard.getTitle());
		galleryBoard.setContent(requestBoard.getContent());
		// 해당 함수로 종료시 (트랙젝션이 Service 가 종료될 때) 트랜젝션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 db flush
	}

	@Transactional
	public void 댓글쓰기(GalleryReplySaveRequestDto galleryReplySaveRequestDto) {
		int result = galleryReplyRepository.mSave(galleryReplySaveRequestDto.getUserId(), galleryReplySaveRequestDto.getBoardId(), 
																						galleryReplySaveRequestDto.getContent());
		System.out.println("GalleryBoardService : "+result);
	}

	public void 댓글삭제(int replyId) {
		galleryReplyRepository.deleteById(replyId);
	}
}
