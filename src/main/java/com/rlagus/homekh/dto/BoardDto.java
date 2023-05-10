package com.rlagus.homekh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private int bnum;
	private String bid;
	private String bname;	
	private String bcontent;
	private String bemail;
	private String bdate;
}
