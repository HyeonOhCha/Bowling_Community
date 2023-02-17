package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.FreeReply;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Integer>{
	@Modifying
	@Query(value="INSERT INTO freeReply(userId, freeBoardId, freeReplyContent, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	int mSave(int userId, int freeBoardId, String freeReplyContent); // 업데이트된 행의 개수를 리턴해줌.  
}
