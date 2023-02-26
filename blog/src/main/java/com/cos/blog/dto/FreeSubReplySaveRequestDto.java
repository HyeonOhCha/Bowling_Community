package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeSubReplySaveRequestDto {
	private int userId;
	private int freeReplyId;
	private String freeSubReplyContent;
	

}