package com.bytestrone.oops.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
	
	private String name;
	private Long points;
	private String category;
	private Long quantity;
	private String termsAndCondition;
	private Date expiryDate; 
	private String image;

}
