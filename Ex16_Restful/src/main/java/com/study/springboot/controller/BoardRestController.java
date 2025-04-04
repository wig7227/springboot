package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Board;
import com.study.springboot.dto.BoardDto;
import com.study.springboot.dto.ResponseDto;
import com.study.springboot.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rest")
public class BoardRestController {
	@Autowired
	BoardRepository bRepository;
	
	// http://localhost:8000/rest/test
	@GetMapping("/test")
	public String test() {
		log.info("test");
		return "test입니다";
	}
	
	
	
	// http://localhost:8000/rest/boards
	// http://localhost:8000/rest/boards?Title=제목
	@GetMapping("/boards")
	public String getBoard(@RequestParam(value="title", defaultValue="제목1") String title) {
		log.info("제목 : ", title);
		return "ok : " + title;
	}
	
	//http://localhost:8000/rest/board/제목1
	@GetMapping("/board/{title}")
	public String getTitle(@PathVariable("title") String title) {
		log.info("title : ", title);
		return "ok : " + title;
	}
	
	
	
	@PostMapping("/boarddto")
	public ResponseDto saveBoardDto(@RequestBody BoardDto boardDto) {
		Board b = bRepository.saveBoardDto(boardDto);
		
		ResponseDto responseDto = new ResponseDto();
		
		if(b.getTitle() != null) {
			responseDto.setMessage("ok");
			return responseDto;
		} else {
			responseDto.setMessage("fail");
			return responseDto;
		}
	}
	
	@GetMapping("/boarddto")
	public BoardDto getBoard1(@RequestParam("title") String title) {
		log.info("title : ", title);
		return bRepository.getBoardById(title);
	}
	
	@GetMapping("/boarddto/{title}")
	public BoardDto getBoardDto(@PathVariable("title") String title) {
		log.info("title : ", title);
		return bRepository.getBoardById(title);
	}
	
	
	
	@GetMapping("/boardall")
	public List<Board> getboardAll() {
		return bRepository.getboardAll();
	}
	
}
