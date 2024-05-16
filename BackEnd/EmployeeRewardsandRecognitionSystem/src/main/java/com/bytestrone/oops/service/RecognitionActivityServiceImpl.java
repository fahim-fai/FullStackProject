package com.bytestrone.oops.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.dao.RecognitionActivityDao;
import com.bytestrone.oops.dto.ErrorResponse;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.RecognitionActivity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecognitionActivityServiceImpl implements RecognitionActivityService {
	
	
	private final RecognitionActivityDao recognitionActivityDao;
	private final UserService userServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(RecognitionActivityServiceImpl.class);

	//adding activity
	@Override
	public ResponseEntity<ResponseWrapper<RecognitionActivity>> addRecognitionActivity(RecognitionActivity recognitionActivity) {
	    
	        if (!userServiceImpl.userIsAdminByToken()) {
	            ErrorResponse errorResponse = new ErrorResponse("Not Authenticated");
	            ResponseWrapper<RecognitionActivity> responseWrapper = new ResponseWrapper<>(null, errorResponse);
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseWrapper);
	        }
	        
	        recognitionActivity.creationDate();
	        recognitionActivity.setCreatedUser(userServiceImpl.getUserByToken());
	        recognitionActivityDao.save(recognitionActivity);
	        
	        ResponseWrapper<RecognitionActivity> responseWrapper = new ResponseWrapper<>(recognitionActivity, null);
	        return ResponseEntity.ok(responseWrapper);
	    
	}

//	//deleting activity with given id
//	@Override
//	public void deleteRecognitionActivity(Long id) {
//	    try {
//	        recognitionActivityDao.deleteById(id);
//	    } catch (EmptyResultDataAccessException ex) {
//	        logger.warn("No recognition activity found with id: {}", id);
//	   
//	}

	//updating activity with given id
	@Override
	public ResponseEntity<RecognitionActivity> updateRecognitionActivity(Long id, RecognitionActivity recognitionActivity) {
	   
	        Optional<RecognitionActivity> activities = recognitionActivityDao.findById(id);
	        if (activities.isPresent()) {
	            RecognitionActivity activity = activities.get();
	            activity.setName(recognitionActivity.getName());
	            activity.setPoints(recognitionActivity.getPoints());
	            activity.modifiedDate();
	            activity.setModifiedUser(userServiceImpl.getUserByToken());
	            recognitionActivityDao.save(activity);
	            RecognitionActivity responseActivity=  recognitionActivityDao.findById(id).get();
	            return ResponseEntity.ok().body(responseActivity);
	        } else {
	            logger.warn("No recognition activity found with id: {}", id);
	            return ResponseEntity.badRequest().body(null);
	        }
	   
	}

	//get all activities
	@Override
	public ResponseEntity<List<RecognitionActivity>> getRecognitionActivities() {
	    
	        List<RecognitionActivity> recognitionActivityList = recognitionActivityDao.findAll();
	        return ResponseEntity.ok(recognitionActivityList);
	    
	}
	
	//get activity with given id
	@Override
	public ResponseEntity<RecognitionActivity> getRecognitionActivity(Long id) {
	    try {
	        Optional<RecognitionActivity> recognitionActivities = recognitionActivityDao.findById(id);
	        RecognitionActivity recognitionActivity = recognitionActivities.orElseThrow(() -> new NotFoundException());
	        return ResponseEntity.ok(recognitionActivity);
	    } catch (Exception ex) {
	        logger.error("Recognition activity not found with id: {}", id, ex);
	        return ResponseEntity.badRequest().build();
	    }
	}

	

}
