package com.bytestrone.oops.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytestrone.oops.dto.AllocationHistoryResponse;
import com.bytestrone.oops.dto.PasswordDto;
import com.bytestrone.oops.dto.RedeemRequest;
import com.bytestrone.oops.dto.RedeemResponse;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.service.AllocationHistoryService;
import com.bytestrone.oops.service.RedeemHistoryService;
import com.bytestrone.oops.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	private final RedeemHistoryService redeemHistoryServiceImpl;
	private final AllocationHistoryService allocationHistoryServiceImpl; 
	private final UserService userServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/allocationHistory")
	public ResponseEntity<List<AllocationHistoryResponse>> getAllocationByUser(){
		return allocationHistoryServiceImpl.getAllocationHistoryByUser();
	}

	@PostMapping("/redeem")
	public ResponseEntity<ResponseWrapper<RedeemRequest>> redeem( @RequestBody RedeemRequest redeemRequest){
		 
		logger.info("redeemRequest-{}",redeemRequest);
		return redeemHistoryServiceImpl.redeem(redeemRequest);
		
	}
	@PutMapping("/changepassword")
	public ResponseEntity<ResponseWrapper<String>> changePassword(@RequestBody PasswordDto passwordDto){
		System.out.println(passwordDto);
		return userServiceImpl.changePassword(passwordDto);
		
	}
	

	@GetMapping("/redeem")
	public ResponseEntity<List<RedeemResponse>> getRedeemByUserId(){
		return redeemHistoryServiceImpl.getRedeemHistoryById();
	}
	@GetMapping("/rewardCount")
	public ResponseEntity<ResponseWrapper<Integer>> getRewardCount(){
		return allocationHistoryServiceImpl.getRewardCount();
	}
	
	

}
