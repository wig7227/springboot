package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JarsController {
	@RequestMapping("/")
	public String root() {
		return "index";
	}
}
