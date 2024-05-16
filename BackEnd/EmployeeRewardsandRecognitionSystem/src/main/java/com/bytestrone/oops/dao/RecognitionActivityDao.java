package com.bytestrone.oops.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytestrone.oops.model.RecognitionActivity;

import jakarta.transaction.Transactional;

@Repository
public interface RecognitionActivityDao extends JpaRepository<RecognitionActivity, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE recognition_activity SET name=:name , points=:points WHERE id=:id",nativeQuery = true)
	void updateById(@Param("id")Long id,@Param("name") String name, @Param("points")Long points);
	

}
