package com.bytestrone.oops.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationHistoryResponse {
	private Long id;
	private String userName;
	private String recognitionActivityName;
	private Long awardedpoints;
	private LocalDate allocatedDate;

}
