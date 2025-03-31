package com.study.springboot.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() {
		return "menu";
	}
	
	@RequestMapping("/insert")
	public String insert(@RequestParam("username") String name, Model model) {
		Member member = Member.builder()
							  .username(name)
							  .createDate(LocalDate.now())
							  .build();
		//Member member = new Member();
		//member.setUsername(name);
		//member.setCreateDate(LocalDate.now()) 같은 뜻
		
		Member result = memberService.insert(member);
		
		model.addAttribute("member", result);
		return "insert";
	}
	
	@RequestMapping("/select")
	public String select(@RequestParam("id") Long id, Model model) {
		/*
		 * Optional<T> : NullpointerException 발생을 방지하기 위해 (Null값이 들어오지 않게)
		 * 				 기존의 반환 값 타입 T에 Optional<T>를 wrapping하여,
		 * 				 null 대신 Optional안에 빈 타입 객체를 돌려주는 기법
		 * ex) Member member = memberRepository.findById("user01"); => 없는 아이디
		 * 	   member.getUserName(); => NullpointerException
		 * 
		 */
		Optional<Member> result = memberService.select(id);
		if(result.isPresent()) {	// isPresent() : 데이터 있는지 없는지 체크
			model.addAttribute("member", result.get());
		} else {
			model.addAttribute("member", null);
		}
		return "select";
	}
	
	@RequestMapping("/selectAll")
	public String selectAll(Model model) {
		List<Member> result = memberService.selectAll();
		model.addAttribute("members", result);
		return "selectAll";
	}
	
	/*
	 * delete후 menu로 가기

	 * update의 반환형은 member
	 *      update후 update.jsp로 가기
	 */
	
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		memberService.delete(id);
		return "menu";
	}
	
	
	@RequestMapping("/update")
	public String update(Member member, Model model) {
		// update시 primary key를 검색하여 키가 있으면 다른 필드 모두 업데이트
		// update시 기존내용을 업데이트 하지 않는다고 안넣으면 null이 들어감
		// => select를 하여 그 결과에 update할 내용만 넣으면 됨
		member.setCreateDate(LocalDate.now());
		memberService.update(member);
		Member result = memberService.update(member);
		model.addAttribute("member", result);
		return "update";
	}
	
}
