package com.cos.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.FreeBoard;



public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{

	@Modifying
	@Query(value="update FreeBoard f set f.freeBoardCount = f.freeBoardCount + 1 where f.id = :id", nativeQuery = true)
	int CountUp(int id); // 업데이트된 행의 개수를 리턴해줌.  
	
	// 제목으로 검색 
	Page<FreeBoard> findByFreeBoardTitleContaining(String searchText, Pageable pageable);

	// 내용으로 검색
	Page<FreeBoard> findByFreeBoardContentContaining(String searchType, Pageable pageable);

	@Modifying
	@Query(value="update FreeBoard f set f.likeCount = f.likeCount + 1 where f.id = :id", nativeQuery = true)
	int LikeUp(int id);
	
	@Modifying
	@Query(value="update FreeBoard f set f.hateCount = f.hateCount + 1 where f.id = :id", nativeQuery = true)
	int HateUp(int id);


}

