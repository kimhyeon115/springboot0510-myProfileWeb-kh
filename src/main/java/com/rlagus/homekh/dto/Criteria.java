package com.rlagus.homekh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {

	private int pageNum = 1;	// 현재 페이지 번호를 저장할 변수(첫페이지 보이기 때문에 초기값 1)
	private int amount = 5;		// 한 페이지에 보여줄 글의 개수
	private int startNum;		// 현재 선택된 페이지에서 시작할 글의 번호
	
}
