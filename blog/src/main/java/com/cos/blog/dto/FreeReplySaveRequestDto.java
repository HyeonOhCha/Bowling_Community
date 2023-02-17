package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeReplySaveRequestDto {
	private int userId;
	private int freeBoardId;
	private String freeReplyContent;
	

}