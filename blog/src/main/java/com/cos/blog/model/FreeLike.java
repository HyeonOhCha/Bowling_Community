package com.cos.blog.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FreeLike {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private int userId;

	private int boardId;

//	@ManyToOne(fetch = FetchType.EAGER) 
//    private User user;
//	
//	@ManyToOne(fetch = FetchType.EAGER) 
//    private FreeBoard freeBoard;
//
//
//    public FreeLike(FreeBoard freeBoard, User user) {
//        this.freeBoard = freeBoard;
//        this.user = user;
//    }
}
