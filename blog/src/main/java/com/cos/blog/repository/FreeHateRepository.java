package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.FreeHate;


public interface FreeHateRepository extends JpaRepository<FreeHate, Integer> {
	

	@Modifying
	@Query(value = "INSERT INTO freeHate(userId, boardId) VALUES(?1, ?2)", nativeQuery = true)
	void mSave(int userId,int boardId);

	Optional<FreeHate> findByUserIdAndBoardId(int userId, int boardId);
	
}
