package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.FreeBoard;
import com.cos.blog.model.FreeLike;
import com.cos.blog.model.User;


public interface FreeLikeRepository extends JpaRepository<FreeLike, Integer> {
	
//	Optional<FreeLike> findByUserAndFreeBoard(User user, FreeBoard freeBoard);

	@Modifying
	@Query(value = "INSERT INTO freeLike(userId, boardId) VALUES(?1, ?2)", nativeQuery = true)
	void mSave(int userId,int boardId);

	Optional<FreeLike> findByUserIdAndBoardId(int userId, int boardId);
	
}
