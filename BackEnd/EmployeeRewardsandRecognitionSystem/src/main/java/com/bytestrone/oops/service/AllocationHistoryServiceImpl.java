package com.bytestrone.oops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.dao.AllocationHistoryDao;
import com.bytestrone.oops.dao.RecognitionActivityDao;
import com.bytestrone.oops.dao.UserDao;
import com.bytestrone.oops.dto.AllocationHistoryResponse;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.AllocationHistory;
import com.bytestrone.oops.model.RecognitionActivity;
import com.bytestrone.oops.model.User;

@Service
public class AllocationHistoryServiceImpl implements AllocationHistoryService {

	@Autowired
	private AllocationHistoryDao allocationHistoryDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RecognitionActivityDao recognitionActivityDao;
	@Autowired
	private UserService userServiceImpl;

	Logger logger = LoggerFactory.getLogger(AllocationHistoryServiceImpl.class);

//	@Override
//	public void addReward(RewardRequest rewardRequest) {
//		Long userId=rewardRequest.getUserId();
//		Long rewardId=rewardRequest.getRewardId();
//		Optional<User> users=userDao.findById(userId);
//		User user=users.get();
//		
//		Optional<RecognitionActivity> recognitionActivities=recognitionActivityDao.findById(rewardId);
//		RecognitionActivity recognitionActivity=recognitionActivities.get();
//		
//		AllocationHistory allocationHistory=new AllocationHistory(user,recognitionActivity);
//		allocationHistoryDao.save(allocationHistory);
//				
//	}

	//bulk allocation
	@Override
	public void addRewards(Map<Long, List<Long>> rewardMap) {
		
			Set<Long> rewardIds = rewardMap.keySet();
			Long rewardId = !rewardIds.isEmpty() ? rewardIds.iterator().next() : 0L;

			Optional<RecognitionActivity> recognitionActivityOptional = recognitionActivityDao.findById(rewardId);
			RecognitionActivity recognitionActivity = recognitionActivityOptional.get();

			Long pointToBeAdded = recognitionActivity.getPoints();
			List<Long> userIds = rewardMap.get(rewardId);

			for (Long userId : userIds) {
				Optional<User> users = userDao.findById(userId);
				User user = users.get();
				Long currentPoint = user.getPoints();
				Long acquiredPoint = user.getAcquiredPoints();

				acquiredPoint =acquiredPoint + pointToBeAdded;
				currentPoint =currentPoint + pointToBeAdded;

				user.setPoints(currentPoint);
				user.setAcquiredPoints(acquiredPoint);
				user.modifiedDate();
				user.setModifiedUser(userServiceImpl.getUserByToken());
				userDao.save(user);

				AllocationHistory allocationHistory = new AllocationHistory(user, recognitionActivity);
				allocationHistory.creationDate();
				allocationHistory.setCreatedUser(userServiceImpl.getUserByToken());
				allocationHistoryDao.save(allocationHistory);
			}
		
	}

	//Every Allocation history 
	@Override
	public ResponseEntity<List<AllocationHistoryResponse>> getAllocationHistory() {
		
			List<AllocationHistoryResponse> allocationHistoryResponseList = new ArrayList<>();

			List<AllocationHistory> allocationList = allocationHistoryDao.findAll();
			for (AllocationHistory allocationData : allocationList) {
				User user = allocationData.getUser();
				RecognitionActivity recognitionActivity = allocationData.getRecognitionActivity();
				AllocationHistoryResponse allocationHistoryResponse = new AllocationHistoryResponse();
				allocationHistoryResponse.setId(allocationData.getId());
				allocationHistoryResponse.setUserName(user.getUsername());
				allocationHistoryResponse.setRecognitionActivityName(recognitionActivity.getName());
				allocationHistoryResponse.setAwardedpoints(recognitionActivity.getPoints());
				allocationHistoryResponse.setAllocatedDate(allocationData.getAllocationDate());
				allocationHistoryResponseList.add(allocationHistoryResponse);
			}

			return ResponseEntity.ok(allocationHistoryResponseList);
		
	}

	//Delete Allocationhistory with given id
	public void deleteAllocationHistoryById(Long id) {
	
			logger.info("Deleting allocation history entry with ID: " + id);

			Long currentPoints = 0L;
			Long pointsToBeReduced = 0L;

			Optional<AllocationHistory> allocationHistory = allocationHistoryDao.findById(id);
			
			AllocationHistory allocationData = allocationHistory.get();
			User user = allocationData.getUser();
			RecognitionActivity recognitionActivity = allocationData.getRecognitionActivity();

			currentPoints = user.getPoints();
			pointsToBeReduced = recognitionActivity.getPoints();
			currentPoints = currentPoints - pointsToBeReduced;

			if (currentPoints < 0) {
				currentPoints = 0L;
			}

			user.setPoints(currentPoints);
			userDao.save(user);

			allocationHistoryDao.deleteById(id);

			logger.info("Allocation history entry with ID " + id + " deleted successfully");
		
	}

	//Get allocation history with given id
	@Override
	public ResponseEntity<AllocationHistoryResponse> getAllocationHistoryById(Long id) {
		
			AllocationHistory allocationData = allocationHistoryDao.findById(id)
					.orElseThrow(() -> new NoSuchElementException("Object with ID " + id + " not found"));
			User user = allocationData.getUser();
			RecognitionActivity recognitionActivity = allocationData.getRecognitionActivity();
			AllocationHistoryResponse allocationHistoryResponse = new AllocationHistoryResponse();
			allocationHistoryResponse.setId(allocationData.getId());
			allocationHistoryResponse.setUserName(user.getUsername());
			allocationHistoryResponse.setRecognitionActivityName(recognitionActivity.getName());
			allocationHistoryResponse.setAwardedpoints(recognitionActivity.getPoints());
			allocationHistoryResponse.setAllocatedDate(allocationData.getAllocationDate());
			return ResponseEntity.ok(allocationHistoryResponse);
		
	}

	//get allocation history of user with token
	@Override
	public ResponseEntity<List<AllocationHistoryResponse>> getAllocationHistoryByUser() {
	
			List<AllocationHistoryResponse> allocationHistoryResponseList = new ArrayList<>();
			RecognitionActivity recognitionActivity = new RecognitionActivity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();
			List<AllocationHistory> allocationList = allocationHistoryDao.findAllocationsByUserId(user.getId());
			for (AllocationHistory allocationData : allocationList) {
				user = allocationData.getUser();
				recognitionActivity = allocationData.getRecognitionActivity();
				AllocationHistoryResponse allocationHistoryResponse = new AllocationHistoryResponse();
				allocationHistoryResponse.setId(allocationData.getId());
				allocationHistoryResponse.setUserName(user.getUsername());
				allocationHistoryResponse.setRecognitionActivityName(recognitionActivity.getName());
				allocationHistoryResponse.setAwardedpoints(recognitionActivity.getPoints());
				allocationHistoryResponse.setAllocatedDate(allocationData.getAllocationDate());
				allocationHistoryResponseList.add(allocationHistoryResponse);
			}
			logger.info("Allocation history retrieved successfully for user {}", user.getUsername());
			return ResponseEntity.ok(allocationHistoryResponseList);
		
	}

	//delete Allocation of a specific user with userid
	@Override
	public void deleteAllocationByUserId(Long userId) {
		
			logger.info("Deleting allocation history entries for user ID: " + userId);
			allocationHistoryDao.deleteByUserId(userId);
			logger.info("Allocation history entries deleted for user ID: " + userId);
	
	}

	//delete allocationhistory of a specfic activity
	@Override
	public void deleteAllocationByActivityId(Long activityId) {
		
			logger.info("Deleting allocationhistory  for  activity ID: " + activityId);
			allocationHistoryDao.deleteByRecognitionActivityId(activityId);
			logger.info("Allocationhistory  deleted for  activity ID: " + activityId);
		
	}

	@Override
	public ResponseEntity<ResponseWrapper<Integer>> getRewardCount() {
		User user=userServiceImpl.getUserDetailByToken();
		Long userId=user.getId();
		Integer rewardCount=allocationHistoryDao.getRewardCount(userId);
		ResponseWrapper<Integer> responseWrapper= new ResponseWrapper<>(rewardCount,null);
		return ResponseEntity.ok( responseWrapper);
	}

}
