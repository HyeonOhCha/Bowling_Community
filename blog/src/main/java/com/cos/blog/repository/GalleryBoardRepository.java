package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.GalleryBoard;

public interface GalleryBoardRepository extends JpaRepository<GalleryBoard, Integer>{
	
	@Modifying
	@Query("update GalleryBoard g set g.galleryBoardCount = g.galleryBoardCount + 1 where g.id = :id")
	int CountUp(int id);
	
}

