package com.bytestrone.oops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPoints {
	
	private Long id;
	private String name;
	private Long points;
	

}
