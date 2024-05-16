package com.bytestrone.oops.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.bytestrone.oops.dto.UserPoints;
import com.bytestrone.oops.model.User;



@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	
	
	
	@Query(value = "Select * from user_details where email=:email",nativeQuery = true)
	User returnUserByEmail(@Param("email") String email);



	@Query(value = "SELECT SUM(points)FROM allocation_history JOIN recognition_activity ON activity_id = recognition_activity.id JOIN user_details ON user_id = user_details.id	WHERE user_id =:id",nativeQuery = true)
	Long pointsForAUser(@Param("id")Long id);



	@Query(value = "SELECT user_details.id as id ,user_details.name as name,  SUM(points) as points FROM allocation_history JOIN recognition_activity ON activity_id = recognition_activity.id JOIN user_details ON user_id = user_details.id GROUP BY user_id,user_details.name,user_details.id;",nativeQuery = true)
	List<UserPoints> getUserTotalPoints();



	@Query(value = "Select * from user_details where email=:email",nativeQuery = true)
	List<Long> getUserIds();



	@Query(value = "SELECT * FROM user_details WHERE is_active=true ORDER BY points DESC;",nativeQuery = true)
	List<User> getUsersOrderByPoints();



	@Query(value = "SELECT * FROM user_details WHERE is_active=true ;",nativeQuery = true)
	List<User> getActiveUsers();
	
	@Query(value = "SELECT * FROM user_details WHERE is_active=false ;",nativeQuery = true)
	List<User> getInActiveUsers();

}
