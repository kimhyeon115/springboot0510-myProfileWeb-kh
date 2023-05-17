package com.rlagus.homekh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

	private int startPage;		// 현재 보여질 시작 페이지 번호
	private int endPage;		// 현재 보여질 마지막 페이지 번호
	private boolean prev;		// 더 이하 페이지가 있는지의 여부
	private boolean next;		// 더 이상 페이지가 있는지의 여부
	private int total;			// 전체 글의 개수( 전체 글의수 / 한페이지당 보여줄 글의 개수 = 반올림값 )
	
	private Criteria criteria;	// 의존 관계(class)
	
	public PageDto(Criteria criteria, int total) {
		
		this.criteria = criteria;
		this.total = total;
		this.endPage = (int) (Math.ceil(criteria.getPageNum()/5.0) * 5);		// Math.ceil() 소수점 이하 무조건 반올림(+1?) 공식
		// 53개의 글이 존재(예시)													// 게시판 end 페이지 구하는 공식
		//       1  2  3  4  5  next
		// prev  6  7  8  9  10 next
		// prev  11
		
//		this.endPage = (int)(Math.ceil(criteria.getPageNum()/criteria.getAmount()*1.0)*criteria.getAmount()); // 오류?(논리적으론 맞음)
		
		
		this.startPage = this.endPage - 4;
		
		int realEndPage = (int) Math.ceil(total*1.0/criteria.getAmount());
		// 전체 글의 개수( 전체 글의수 / 한페이지당 보여줄 글의 개수 = 반올림값 )
		// 글이 53개면 53/5=10.6 -> 올림 11로 변환한 값이 실제 끝 페이지수가 됨
		
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}	// 처음 계산한 마지막 페이지의 값이 endPage 값보다 realEndpage값이 작게 나오면
			// 그 값으로 대신해야 실제 마지막 페이지 값이 마지막 페이지 번호로 출력됨
		
		this.prev = this.startPage > 5;	// 1로 해도됨(예,startPage가 1,6,11,16,21,26,31...)이기때문
		// 시작 페이지 번호가 5보다 크면 prev가 존재해야 함
		
		this.next = this.endPage < realEndPage;
		// 마지막페이지 번호가 실제 페이지번호보다 작은 경우에만 next가 존재해야 함
	}
	
}
