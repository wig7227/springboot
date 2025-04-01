package com.study.springboot.controller;

import static org.mockito.ArgumentMatchers.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/enrollForm")
	public String enrollForm() {
		return "member/enrollForm";
	}
	
	@GetMapping("/idCheck")
	public @ResponseBody boolean idCheck(@RequestParam("id") String id) {
		return memberService.idCheck(id);
	}
	
	@PostMapping("/memberInsert")
	public String insert(Member member) {
		String enPass = pEncoder.encode(member.getPassword());
		member.setPassword(enPass);
		
		
		
		return "redirect:/";
	}
	
	@RequestMapping("/Gologin")
	public String Gologin(Member member) {
		String pEncode.matches(member.getPassword());
	}
	
	/*
	 * 로그인 시
	 * password
	 * 
	 * pEncode.matches(사용자가 넣은 패스워드, DB에서 가져온 패스워드)
	 */
	
	
}
