package com.bytestrone.oops.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bytestrone.oops.model.AllocationHistory;

import jakarta.transaction.Transactional;

public interface AllocationHistoryDao extends JpaRepository<AllocationHistory, Long>{

	@Query(value = "SELECT * FROM allocation_history WHERE user_id=:id",nativeQuery = true)
	List<AllocationHistory> findAllocationsByUserId(Long id);
	
	@Transactional
	Long deleteByUserId(Long id);
	
	@Transactional
	Long deleteByRecognitionActivityId(Long id);

	@Query(value = "SELECT COUNT(*) FROM allocation_history WHERE user_id=:userId",nativeQuery = true)
	Integer getRewardCount(Long userId);

}
