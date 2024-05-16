package com.bytestrone.oops.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedeemResponse {
	private Long id;
	private String userName;
	private String ProductName;
	private Long redeemedPoints;
	private LocalDate redeemedDate;

}



