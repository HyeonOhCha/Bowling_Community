package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.FreeReplySaveRequestDto;
import com.cos.blog.dto.FreeSubReplySaveRequestDto;
import com.cos.blog.model.FreeBoard;
import com.cos.blog.model.FreeReply;
import com.cos.blog.model.User;
import com.cos.blog.repository.FreeBoardRepository;
import com.cos.blog.repository.FreeReplyRepository;
import com.cos.blog.repository.FreeSubReplyRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IoC를 해준다
@Service
public class FreeBoardService {


	@Autowired
	private FreeBoardRepository freeBoard_Repository;

	@Autowired
	private FreeReplyRepository freeReply_Repository;
	
	@Autowired
	private FreeSubReplyRepository freeSubReply_Repository;
	
	

	@Transactional
	public void freeBoard_write(FreeBoard freeboard, User user) { // title , content
		freeboard.setFreeBoardCount(0);
		freeboard.setUser(user);
		freeBoard_Repository.save(freeboard);
	}



	@Transactional(readOnly = true)
	public Page<FreeBoard> freeBoard_list(Pageable pageable) {

		return freeBoard_Repository.findAll(pageable);
	}
	
	//  제목으로 검색
	@Transactional(readOnly = true)
	    public Page<FreeBoard> TitleSearch(String searchType, Pageable pageable) {

			Page<FreeBoard> boardList = freeBoard_Repository.findByFreeBoardTitleContaining(searchType, pageable);
	        
	        return boardList;
	   }
	
	//  내용으로 검색
	@Transactional(readOnly = true)
    public Page<FreeBoard> ContentSearch(String searchType, Pageable pageable) {

		Page<FreeBoard> boardList = freeBoard_Repository.findByFreeBoardContentContaining(searchType, pageable);
        
        return boardList;
   }
	



	@Transactional(readOnly = true)
	public FreeBoard freeBoard_detail(int id) {
		return freeBoard_Repository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("자유게시판 글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});

	}

	@Transactional
	public void freeBoard_delete(int id) {
		freeBoard_Repository.deleteById(id);
	}

	@Transactional
	public void freeBoard_update(int id, FreeBoard requestBoard) {
		FreeBoard freeboard = freeBoard_Repository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("자유게시판 글 찾기 실패 : 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		freeboard.setFreeBoardTitle(requestBoard.getFreeBoardTitle());
		freeboard.setFreeBoardContent(requestBoard.getFreeBoardContent());
		// 해당 함수로 종료시 (트랙젝션이 Service 가 종료될 때) 트랜젝션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 db flush
	}

	@Transactional
	public void freeReply_write(FreeReplySaveRequestDto freeRequest) {
		int result = freeReply_Repository.mSave(freeRequest.getUserId(), freeRequest.getFreeBoardId(), freeRequest.getFreeReplyContent());
	}
	
	@Transactional
	public void CountUp(int id) {
		int result = freeBoard_Repository.CountUp(id);
		}


	public void freeReply_delete(int id) {

		freeReply_Repository.deleteById(id);
	}


	@Transactional
	public void freeSubReply_write(FreeSubReplySaveRequestDto freeRequest) {
		int result = freeSubReply_Repository.mSave(freeRequest.getUserId(), freeRequest.getFreeReplyId(),freeRequest.getFreeSubReplyContent());
		
	}

	@Transactional
	public FreeReply freeBoard_subReplys(int id) {
		return freeReply_Repository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("FreeBoardService에 freeBoard_subReplys 에러");
		});
	}


	@Transactional
	public void LikeUp(int boardId) {
		int result = freeBoard_Repository.LikeUp(boardId);
	}

	

}
