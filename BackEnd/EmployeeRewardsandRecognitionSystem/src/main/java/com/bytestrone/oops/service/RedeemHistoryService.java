package com.bytestrone.oops.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bytestrone.oops.dto.RedeemRequest;
import com.bytestrone.oops.dto.RedeemResponse;
import com.bytestrone.oops.dto.ResponseWrapper;

public interface RedeemHistoryService {

	

	ResponseEntity<ResponseWrapper<RedeemRequest>> redeem(RedeemRequest redeemRequest);

	ResponseEntity<List<RedeemResponse>> getRedeemHistoryById();

	ResponseEntity<List<RedeemResponse>> getRedeemHistory();

	void deleteRedeemByUserId(Long id);

	void deleteRedeemByProductId(Long id);

}
