package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FreeReply {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(nullable = false, length = 200)
	private String freeReply_content;
	
	
	@ManyToOne
	@JoinColumn(name="free_boardId")
	private FreeBoard free_board;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	@CreationTimestamp
	private Timestamp createDate;


	@Override
	public String toString() {
		return "FreeReply [id=" + id + ", freeReply_content=" + freeReply_content + ", free_board=" + free_board
				+ ", user=" + user + ", createDate=" + createDate + "]";
	}



}
