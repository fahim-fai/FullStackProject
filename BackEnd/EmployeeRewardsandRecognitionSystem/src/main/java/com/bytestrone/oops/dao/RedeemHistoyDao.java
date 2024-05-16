package com.bytestrone.oops.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bytestrone.oops.model.RedeemHistory;

import jakarta.transaction.Transactional;

public interface RedeemHistoyDao extends JpaRepository<RedeemHistory, Long>{
	
	@Query(value = "SELECT * FROM redeem_history WHERE user_id=:id",nativeQuery = true)
	List<RedeemHistory> findRedeemByUserId(Long id);
	
	@Transactional
	Long deleteByUserId(Long id);
	
	@Transactional
	Long deleteByProductId(Long id);

}
