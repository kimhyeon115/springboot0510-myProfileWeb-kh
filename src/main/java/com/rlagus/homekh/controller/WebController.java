package com.rlagus.homekh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rlagus.homekh.dao.IDao;
import com.rlagus.homekh.dto.BoardDto;
import com.rlagus.homekh.dto.MemberDto;

import oracle.net.aso.m;

@Controller
public class WebController {
	
	@Autowired
	private SqlSession sqlSession;
	// DB정보를 가진 객체
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/")
	public String index2() {
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/profile")
	public String profile() {
		return "profile";
	}
	
	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping(value = "/question")
	public String question(HttpSession session, Model model) {
		
		String sessionId = (String)session.getAttribute("sessionId");
		
		MemberDto memberDto = new MemberDto("GUEST", " ", "비회원", " ", " ");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(sessionId == null) {
			model.addAttribute("memberDto", memberDto);
		} else {
			model.addAttribute("memberDto", dao.getMemberInfo(sessionId));
		}		
		return "question";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int joinCheck = 0;
		int checkId = dao.checkIdDao(mid);	// 가입하려는 id 존재여부 체크 1이면 존재
		
		if(checkId == 0) {
			joinCheck = dao.joinDao(mid, mpw, mname, memail);
			// joinCheck 값이 1이면 회원가입 성공, 0이면 가입 실패
			model.addAttribute("checkId", checkId);
		} else {
			model.addAttribute("checkId", checkId);
		}		
		
		if(joinCheck == 1) {
			model.addAttribute("joinFlag", joinCheck);
			model.addAttribute("memberName", mname);
			model.addAttribute("memberId", mid);
		} else {
			model.addAttribute("joinFlag", joinCheck);
		}
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {	// HttpSession 로그인 정보저장 객체

		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		int checkIdPwFlag = dao.checkIdPwDao(mid, mpw);
		model.addAttribute("checkIdPwFlag", checkIdPwFlag);
				
		if(checkIdPwFlag == 1) {	// 로그인 성공
			session.setAttribute("sessionId", mid);			
			model.addAttribute("memberDto", dao.getMemberInfo(mid));
		}		
		return "loginOk";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();	// 세션 삭제 -> 로그아웃
		
		return "redirect:login";
	}
	
	@RequestMapping(value = "/modify")
	public String modify(HttpSession session, Model model) {
		
		String sessionId = (String)session.getAttribute("sessionId");
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("memberDto", dao.getMemberInfo(sessionId));		
		
		return "modifyForm";
	}
	
	@RequestMapping(value = "/modifyOk")
	public String modifyOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.modifyMemberDao(mid, mpw, mname, memail);
		
		model.addAttribute("memberDto", dao.getMemberInfo(mid));	// 수정이된후 회원 정보
	
		return "modifyOk";
	}
	
	@RequestMapping(value = "/questionOk")
	public String questionOk(HttpServletRequest request) {
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String bcontent = request.getParameter("bcontent");
		String bemail = request.getParameter("bemail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.quesionWriteDao(bid, bname, bcontent, bemail);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		List<BoardDto> boardDtos = dao.questionListDao();
		model.addAttribute("boardDtos", boardDtos);
		
		return "list";
	}
	
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum");		
		IDao dao = sqlSession.getMapper(IDao.class);
		BoardDto boardDto = dao.cententViewDao(bnum);
		model.addAttribute("boardDto", boardDto);
		
		return "contentView";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		
		String bnum = request.getParameter("bnum");
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.deleteDao(bnum);
		
		return "redirect:list";
	}
}