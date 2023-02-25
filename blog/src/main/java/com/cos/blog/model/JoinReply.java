package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class JoinReply {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(nullable = false, length = 200)
	private String joinReplyContent;
	
	
	@ManyToOne
	@JoinColumn(name="joinBoardId")
	private JoinBoard joinBoard;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	@CreationTimestamp
	private Timestamp createDate;


	@Override
	public String toString() {
		return "JoinReply [id=" + id + ", joinReplyContent=" + joinReplyContent + ", joinBoard=" + joinBoard + ", user="
				+ user + ", createDate=" + createDate + "]";
	}


	



}
