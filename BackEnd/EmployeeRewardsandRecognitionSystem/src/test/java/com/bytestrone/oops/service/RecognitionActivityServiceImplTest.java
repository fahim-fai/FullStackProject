package com.bytestrone.oops.service;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.bytestrone.oops.dao.RecognitionActivityDao;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.RecognitionActivity;

public class RecognitionActivityServiceImplTest extends MockitoExtension{
	
	@InjectMocks
	RecognitionActivityServiceImpl recognitionActivityServiceImpl;
	
	
	private RecognitionActivityDao recognitionActivityDao;
	private UserService userServiceImpl;
	private Logger logger;
	
	@Before
	public void setUp() {
		logger=mock(Logger.class);
		recognitionActivityDao=mock(RecognitionActivityDao.class);
		 userServiceImpl=mock(UserServiceImpl.class);
		 recognitionActivityServiceImpl= new RecognitionActivityServiceImpl(recognitionActivityDao, userServiceImpl);
		 ReflectionTestUtils.setField(recognitionActivityServiceImpl, "logger", logger);
		 
	}

	
	
	@Test
	public void addRecognitionActivityFailureTest() {
		RecognitionActivity recognitionActivity=new RecognitionActivity();
		when(userServiceImpl.userIsAdminByToken()).thenReturn(false);
		
		assertEquals(HttpStatus.UNAUTHORIZED, recognitionActivityServiceImpl.addRecognitionActivity(recognitionActivity).getStatusCode());
	}
	@Test
	public void addRecognitionActivitySuccessTest() {
		RecognitionActivity recognitionActivity=new RecognitionActivity();
		
		when(userServiceImpl.userIsAdminByToken()).thenReturn(true);
		 ResponseEntity<ResponseWrapper<RecognitionActivity>> responseEntity = recognitionActivityServiceImpl.addRecognitionActivity(recognitionActivity);
		
		 	verify(recognitionActivityDao,times(1)).save(any());
		 
		    assertEquals(recognitionActivity, responseEntity.getBody().getData());
		    
		    assertNull(responseEntity.getBody().getError());
		
		assertEquals(HttpStatus.OK, recognitionActivityServiceImpl.addRecognitionActivity(recognitionActivity).getStatusCode());
	}
	@Test
	public void updateRecognitionActivityFailureTest() {
		Long id=2L;
	   
	    when(recognitionActivityDao.findById(id)).thenReturn(Optional.empty());
	    

	    recognitionActivityServiceImpl.updateRecognitionActivity(id, new RecognitionActivity());
	    
	    verify(logger, times(1)).warn("No recognition activity found with id: {}", id);
	}
	@Test
	public void updateRecognitionActivitySuccessTest() {
		Long id=2L;
		RecognitionActivity recognitionActivity=new RecognitionActivity();
	    when(recognitionActivityDao.findById(id)).thenReturn(Optional.of(recognitionActivity));
	    recognitionActivityServiceImpl.updateRecognitionActivity(id, new RecognitionActivity());
	    verify(recognitionActivityDao, times(1)).save(any());
	    
		
		
	}
	@Test
	public void getRecognitionActivitiesSuccessTest() {
		List<RecognitionActivity> recognitionActivities= new ArrayList<>();
        when(recognitionActivityDao.findAll()).thenReturn(recognitionActivities);
        recognitionActivityServiceImpl.getRecognitionActivities();
		assertEquals(HttpStatus.OK, recognitionActivityServiceImpl.getRecognitionActivities().getStatusCode());
		assertEquals(recognitionActivities, recognitionActivityServiceImpl.getRecognitionActivities().getBody() );


	}
	@Test
    public void testGetRecognitionActivityFailureTest() {
        long id = 123L;
        when(recognitionActivityDao.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<RecognitionActivity> responseEntity = recognitionActivityServiceImpl.getRecognitionActivity(id);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
	@Test
    public void testGetRecognitionActivitySucccessTest() {
        long id = 123L;
		RecognitionActivity recognitionActivity= new RecognitionActivity();
        when(recognitionActivityDao.findById(id)).thenReturn(Optional.of(recognitionActivity));
        ResponseEntity<RecognitionActivity> responseEntity = recognitionActivityServiceImpl.getRecognitionActivity(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(recognitionActivity, recognitionActivityServiceImpl.getRecognitionActivity(id).getBody() );

    }

}
