package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "JSP 실행";
	}
	
	@RequestMapping("/test1")	// url => localhost:8080/test1
	public String test1() {	//  string => 파일명
		return "test1";		// 호출할 페이지 : /WEB-INF/views/test1.jsp
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "sub/test2";	// 호출할 페이지 : /WEB-INF/views/sub/test2.jsp
	}
}
