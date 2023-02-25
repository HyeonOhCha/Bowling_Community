package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.JoinReplySaveRequestDto;
import com.cos.blog.model.JoinBoard;
import com.cos.blog.model.User;
import com.cos.blog.repository.JoinBoardRepository;
import com.cos.blog.repository.JoinReplyRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IoC를 해준다
@Service
public class JoinBoardService {


	@Autowired
	private JoinBoardRepository joinBoard_Repository;

	@Autowired
	private JoinReplyRepository joinReply_Repository;
	
	

	@Transactional
	public void joinBoard_write(JoinBoard joinboard, User user) { // title , content
		joinboard.setJoinBoardCount(0);
		joinboard.setUser(user);
		joinBoard_Repository.save(joinboard);
	}



	@Transactional(readOnly = true)
	public Page<JoinBoard> joinBoard_list(Pageable pageable) {

		return joinBoard_Repository.findAll(pageable);
	}
	
	//  제목으로 검색
	@Transactional(readOnly = true)
	    public Page<JoinBoard> TitleSearch(String searchType, Pageable pageable) {

			Page<JoinBoard> boardList = joinBoard_Repository.findByJoinBoardTitleContaining(searchType, pageable);
	        
	        return boardList;
	   }
	
	//  내용으로 검색
	@Transactional(readOnly = true)
    public Page<JoinBoard> ContentSearch(String searchType, Pageable pageable) {

		Page<JoinBoard> boardList = joinBoard_Repository.findByJoinBoardContentContaining(searchType, pageable);
        
        return boardList;
   }
	
//	제목+내용 검색
//	@Transactional(readOnly = true)
//	public Page<JoinBoard> TitleOrContent_Search(String searchText, Pageable pageable) {
//		Page<JoinBoard> boardList = joinBoard_Repository.findByJoinBoardTitleContainingOrJoinBoardContentContaining(searchType, searchText, pageable);
//		return boardList;
//	}


	@Transactional(readOnly = true)
	public JoinBoard joinBoard_detail(int id) {
		return joinBoard_Repository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시판 글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});

	}

	@Transactional
	public void joinBoard_delete(int id) {
		joinBoard_Repository.deleteById(id);
	}

	@Transactional
	public void joinBoard_update(int id, JoinBoard requestBoard) {
		JoinBoard joinboard = joinBoard_Repository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시판 글 찾기 실패 : 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		joinboard.setJoinBoardTitle(requestBoard.getJoinBoardTitle());
		joinboard.setJoinBoardContent(requestBoard.getJoinBoardContent());
		// 해당 함수로 종료시 (트랙젝션이 Service 가 종료될 때) 트랜젝션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 db flush
	}

	@Transactional
	public void joinReply_write(JoinReplySaveRequestDto joinRequest) {
		int result = joinReply_Repository.mSave(joinRequest.getUserId(), joinRequest.getJoinBoardId(), joinRequest.getJoinReplyContent());
		System.out.println("JoinBoardService : "+result);
	}
	
	@Transactional
	public void CountUp(int id) {
		int result = joinBoard_Repository.CountUp(id);
		}


	public void joinReply_delete(int id) {

		joinReply_Repository.deleteById(id);
	}


	

}
