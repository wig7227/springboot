package com.study.springboot.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.springboot.domain.Board;
import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;
import com.study.springboot.service.BoardService;
import com.study.springboot.service.ReplyService;

import jakarta.servlet.http.HttpSession;


/*
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
*/

@Controller
@SessionAttributes("loginUser")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ReplyService replyService;
	
	@GetMapping("/list")
	public String list(@RequestParam(value="nowPage", defaultValue="0") int nowPage, Model model) {
		Page<Board> pageList = boardService.list(PageRequest.of(nowPage, 10, Sort.by(Sort.Direction.DESC, "bno")));
		
		model.addAttribute("boardPage", pageList);
		return "board/list";
	}
	
	@GetMapping("/insertForm")
	public String insertForm() {
		return "board/insertForm";
	}
	
	@PostMapping("/insert")
	public String insert(Board board) {
		boardService.insert(board);
		return "redirect:list";
	}
	
	
	@RequestMapping("/detailForm")
	public String detail(@RequestParam("bno") Long bno, Model model) {
		Optional<Board> result = boardService.detail(bno);
		if(result.isPresent()) {
			model.addAttribute("board", result.get());
			model.addAttribute("reply", replyService.selectAll(bno));
			
//			List<Reply> reply = replyService.selectAll(bno);
			
		} else {
			model.addAttribute("board", null);
		}
		return "board/detailForm";
	}
	
	//
	//
	
	@Autowired
    private MemberRepository memberRepository;
    
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        // Check if user is logged in
        Member loginMember = (Member) session.getAttribute("loginUser");
        
        if(loginMember == null) {
            return "redirect:/";
        }
        
        // Get fresh user data from database
        Member member = memberRepository.findById(loginMember.getId()).orElse(null);
        
        if(member == null) {
            // Handle unexpected situation where user is in session but not in DB
            session.invalidate();
            return "redirect:/";
        }
        
        model.addAttribute("member", member);
        return "board/mypage";
    }
    
    @PostMapping("/updateMember")
    public String updateMember(HttpSession session, 
                            @RequestParam String name,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String password,
                            @RequestParam(required = false) String phone,
                            @RequestParam(required = false) String address,
                            @RequestParam(required = false) String birthdayStr,
                            RedirectAttributes redirectAttributes) {
        
        Member loginMember = (Member) session.getAttribute("loginUser");
        
        if(loginMember == null) {
            return "redirect:/";
        }
        
        Member member = memberRepository.findById(loginMember.getId()).orElse(null);
        
        if(member == null) {
            session.invalidate();
            return "redirect:/";
        }
        
        // Update member data
        member.setName(name);
        
        if(email != null && !email.trim().isEmpty()) {
            member.setEmail(email);
        }
        
        if(password != null && !password.trim().isEmpty()) {
            // In a real application, you would hash the password here
            member.setPassword(password);
        }
        
        if(phone != null && !phone.trim().isEmpty()) {
            member.setPhone(phone);
        }
        
        if(address != null && !address.trim().isEmpty()) {
            member.setAddress(address);
        }
        
        // Handle birthday if provided
        if(birthdayStr != null && !birthdayStr.trim().isEmpty()) {
            try {
                LocalDate birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ISO_DATE);
                member.setBirthday(birthday);
            } catch (Exception e) {
                // Handle invalid date format
                redirectAttributes.addFlashAttribute("error", "생년월일 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력해주세요.");
                return "redirect:/mypage";
            }
        }
        
        // Save updated member
        memberRepository.save(member);
        
        // Update session with new member data
        session.setAttribute("loginUser", member);
        
        redirectAttributes.addFlashAttribute("message", "프로필이 성공적으로 업데이트되었습니다.");
        return "redirect:/mypage";
    }	
	
	
	
}
