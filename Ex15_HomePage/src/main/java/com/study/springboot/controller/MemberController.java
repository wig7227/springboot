package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
@SessionAttributes("loginUser")
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
		memberService.insert(member);
		return "redirect:/";
	}
	
	
	
	/*
	 * @PostMapping("/login") public String login(@RequestParam("id") String id,
	 * 
	 * @RequestParam("password") String password) { Member member =
	 * memberService.login2(id); if (member != null && pEncoder.matches(password,
	 * member.getPassword())) { } return "redirect:/"; }
	 */
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		System.out.println(member.getId());
		Member loginUser = memberService.login(member);
		System.out.println(loginUser.getId());
		
		if(loginUser != null && pEncoder.matches(member.getPassword(), loginUser.getPassword())) {
			model.addAttribute("loginUser", loginUser);  
			// request scope -> session scope로 변환
			//   class에 추가 어노테이션 추가(@SessionAttributes({"loginUser","여러개"}))
		}
		return "redirect:/";
	}
	/*
	 * 로그인 시
	 * password
	 * 
	 * pEncode.matches(사용자가 넣은 패스워드, DB에서 가져온 패스워드)
	 */
	
	
	/*
	 * @SessionAttributes + model 을 통해 로그인정보를 관리하는 경우
	 * SessionStatus객체를 통해 사용완료 처리를 해야 함.
	 * - session객체를 폐기하지 않고 재사용
	 * 
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) {
			status.setComplete();
		}
		return "redirect:/";
	}
	

	
}
