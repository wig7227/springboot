package com.study.springboot.dto;

import com.study.springboot.domain.Board;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BoardDto {
	private String title;
	private String content;
	
	public BoardDto(Board b) {
		this.title = b.getTitle();
		this.content = b.getContent();
	}
}
