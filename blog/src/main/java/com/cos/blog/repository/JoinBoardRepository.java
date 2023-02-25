package com.cos.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.JoinBoard;



public interface JoinBoardRepository extends JpaRepository<JoinBoard, Integer>{

	@Modifying
	@Query(value="update JoinBoard j set j.joinBoardCount = j.joinBoardCount + 1 where j.id = :id", nativeQuery = true)
	int CountUp(int id); // 업데이트된 행의 개수를 리턴해줌.  
	
	// 제목으로 검색 
	Page<JoinBoard> findByJoinBoardTitleContaining(String searchText, Pageable pageable);

	// 내용으로 검색
	Page<JoinBoard> findByJoinBoardContentContaining(String searchType, Pageable pageable);
	 
   //Page<JoinBoard> findByFreeBoard_titleContainigOrFreeBoard_contentContainig(String freeBoard_title, String freeBoard_content, Pageable pageable);
	
	
}

