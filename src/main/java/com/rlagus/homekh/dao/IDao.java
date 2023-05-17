package com.rlagus.homekh.dao;


import java.util.List;

import com.rlagus.homekh.dto.BoardDto;
import com.rlagus.homekh.dto.MemberDto;

public interface IDao {
	
	// 회워관리
	public int joinDao(String mid, String mpw, String mname, String memail);	// 회원가입
	// 반환 값필요시 int 가능(1 or 0)
	
	public int checkIdDao(String mid);					// 가입하려는 id의 존재여부 체크
	
	public int checkIdPwDao(String mid, String mpw);	// 아이디와 비밀번호의 일치여부 체크
	
	public MemberDto getMemberInfo(String mid);			// 아이디로 조회하여 회원 정보 가져오기
	
	public int modifyMemberDao(String mid, String mpw, String mname, String memail);
	
	// 게시판관리
	public void quesionWriteDao(String bid, String bname, String bcontent, String bemail);	// 질문하기 insert
	
	public List<BoardDto> questionListDao(int amount, int pageNum);			// 모든 글 목록 가져오기
	
	public BoardDto cententViewDao(String bnum);
	
	public void deleteDao(String bnum);
	
	public int boardAllCountDao();						//	모든 글의 개수를 반환
	
}
