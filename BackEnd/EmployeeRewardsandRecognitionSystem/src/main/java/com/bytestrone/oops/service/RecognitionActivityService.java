package com.bytestrone.oops.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.RecognitionActivity;

public interface RecognitionActivityService {
	
	public ResponseEntity<ResponseWrapper<RecognitionActivity>> addRecognitionActivity(RecognitionActivity recognitionActivity);

//	public void deleteRecognitionActivity(Long id);

	public ResponseEntity<RecognitionActivity> updateRecognitionActivity(Long id, RecognitionActivity recognitionActivity);

	public ResponseEntity<List<RecognitionActivity>> getRecognitionActivities();

	public ResponseEntity<RecognitionActivity> getRecognitionActivity(Long id);

}
