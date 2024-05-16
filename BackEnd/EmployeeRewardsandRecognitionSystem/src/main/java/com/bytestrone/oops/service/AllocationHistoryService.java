package com.bytestrone.oops.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bytestrone.oops.dto.AllocationHistoryResponse;
import com.bytestrone.oops.dto.ResponseWrapper;

public interface AllocationHistoryService {

	

//	void addReward(RewardRequest rewardRequest);

	void addRewards(Map<Long, List<Long>> rewardMap);

	ResponseEntity<List<AllocationHistoryResponse>> getAllocationHistory();

	void deleteAllocationHistoryById(Long id);

	ResponseEntity<AllocationHistoryResponse> getAllocationHistoryById(Long id);

	ResponseEntity<List<AllocationHistoryResponse>> getAllocationHistoryByUser();

	void deleteAllocationByUserId(Long id);

	void deleteAllocationByActivityId(Long id);

	ResponseEntity<ResponseWrapper<Integer>> getRewardCount();

}
