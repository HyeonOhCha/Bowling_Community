package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinReplySaveRequestDto {
	
	private int userId;
	private int joinBoardId;
	private String joinReplyContent;
	

}