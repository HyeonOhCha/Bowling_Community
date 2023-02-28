package com.cos.blog.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class FreeBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String freeBoardTitle;

	@Lob // 대용량 데이터
	private String freeBoardContent; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.

	private int freeBoardCount; // 조회수

	@ManyToOne(fetch = FetchType.EAGER) // Many = Many, User = One
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	@OneToMany(mappedBy = "freeBoard", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) 
	@JsonIgnoreProperties({ "freeBoard" })
	@OrderBy("id desc")
	private List<FreeReply> freeReplys;

	@CreationTimestamp
	private LocalDateTime createDate;


	// 추천 수
	private int likeCount;



}
