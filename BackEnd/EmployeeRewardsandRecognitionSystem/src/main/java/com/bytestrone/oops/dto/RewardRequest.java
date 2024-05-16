package com.bytestrone.oops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardRequest {
	private Long userId;
	private Long rewardId;

}
