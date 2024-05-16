package com.bytestrone.oops.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bytestrone.oops.model.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>{
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product_table SET name=:name , points=:points,category=:category,quantity=:quantity, terms_and_condition=:termsAndCondition,expiry_date=:expiryDate WHERE id=:id",nativeQuery = true)
	void updateById(Long id, String name, Long points, String category, Long quantity, String termsAndCondition,
			Date expiryDate);

}
