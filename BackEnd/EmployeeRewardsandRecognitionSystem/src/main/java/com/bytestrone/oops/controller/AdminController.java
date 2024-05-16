package com.bytestrone.oops.controller;



import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytestrone.oops.dto.AllocationHistoryResponse;
import com.bytestrone.oops.dto.ProductRequest;
import com.bytestrone.oops.dto.RedeemResponse;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.Product;
import com.bytestrone.oops.model.RecognitionActivity;
import com.bytestrone.oops.model.User;
import com.bytestrone.oops.service.AllocationHistoryService;
import com.bytestrone.oops.service.ProductService;
import com.bytestrone.oops.service.RecognitionActivityService;
import com.bytestrone.oops.service.RedeemHistoryService;
//import com.bytestrone.oops.service.UserService;
import com.bytestrone.oops.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private final RecognitionActivityService recognitionActivityServiceImpl;
	
	private final AllocationHistoryService allocationHistoryServiceImpl;
	
	private final ProductService productServiceImpl;
	
	private final UserService userServiceImpl;
	
	private final RedeemHistoryService redeemHistoryServiceImpl;
	

	@PostMapping("/addRecognitionActivity")
	public ResponseEntity<ResponseWrapper<RecognitionActivity>> addRecognitionActivity( @RequestBody RecognitionActivity recognitionActivity){
		 
		    
		    
		    return recognitionActivityServiceImpl.addRecognitionActivity(recognitionActivity);
		
	}
	

//	@DeleteMapping("/deleteRecognitionActivity/id/{id}")
//	public ResponseEntity<String> deleteRecognitionActivity(@PathVariable("id")Long id) {
//		 
//		    if(!userServiceImpl.userIsAdminByToken()) {
//		    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
//		    }
//		allocationHistoryServiceImpl.deleteAllocationByActivityId(id);
//		recognitionActivityServiceImpl.deleteRecognitionActivity(id);
//		return new ResponseEntity<>("deleted sucesfully",HttpStatus.ACCEPTED);
//	}
	

	@PutMapping("/updateRecognitionActivity/id/{id}")
	public ResponseEntity<RecognitionActivity> edit(@PathVariable("id")Long id, @RequestBody RecognitionActivity recognitionActivity) {
		
		return recognitionActivityServiceImpl.updateRecognitionActivity(id,recognitionActivity);
		
	}

	@GetMapping("/recognitionActivties")
	public ResponseEntity<List<RecognitionActivity>> getRecognnitionActivities(){
		
		return recognitionActivityServiceImpl.getRecognitionActivities();
	}
	

	@GetMapping("/recognitionActivty/id/{id}")
	public ResponseEntity<RecognitionActivity> getRecognnitionActivity(@PathVariable("id")Long id){
	
		return recognitionActivityServiceImpl.getRecognitionActivity(id);
	}
	

	@PostMapping("/product")
	public ResponseEntity<String> addProduct( @RequestBody ProductRequest productRequest){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    User user = (User) authentication.getPrincipal();
		    if(!user.isAdmin()) {
		    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
		    }
		    
		productServiceImpl.addProduct(productRequest);
		return new ResponseEntity<>("added sucesfully",HttpStatus.ACCEPTED);
	}
	@PutMapping("/recoverProduct/{id}")
	public ResponseEntity<String> recoverProduct(@PathVariable("id")Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    if(!user.isAdmin()) {
	    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
	    }
		 productServiceImpl.recoverProduct(id);
		return new ResponseEntity<>("recovered succesfully",HttpStatus.ACCEPTED);
	}
	

	@DeleteMapping("/deleteProduct/id/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id")Long id) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    User user = (User) authentication.getPrincipal();
		    if(!user.isAdmin()) {
		    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
		    }
		
		productServiceImpl.deleteProduct(id);
		return new ResponseEntity<>("deleted sucesfully",HttpStatus.ACCEPTED);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		
		return productServiceImpl.getProducts();
	}

	@GetMapping("/product/id/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id")Long id){
		
		return productServiceImpl.getProduct(id);
	}
	

	@PutMapping("/updateProduct/id/{id}")
	public ResponseEntity<String> edit(@PathVariable("id")Long id, @RequestBody ProductRequest productRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    if(!user.isAdmin()) {
	    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
	    }
		 productServiceImpl.updateProduct(id,productRequest);
		return new ResponseEntity<>("updated succesfully",HttpStatus.ACCEPTED);
	}
	
//	@PostMapping("/addReward")
//	public ResponseEntity<?> addReward( @RequestBody RewardRequest rewardRequest){
//		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		    User user = (User) authentication.getPrincipal();
//		    if(!user.isAdmin()) {
//		    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
//		    }
//		    
//		allocationHistoryServiceImpl.addReward(rewardRequest);
//		return new ResponseEntity<>("added sucesfully",HttpStatus.ACCEPTED);
//	}

	@PostMapping("/reward")
	public ResponseEntity<String> addReward( @RequestBody Map<Long, List<Long>> rewardMap){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    User user = (User) authentication.getPrincipal();
		    if(!user.isAdmin()) {
		    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
		    }
		    
		allocationHistoryServiceImpl.addRewards(rewardMap);
		return new ResponseEntity<>("added sucesfully",HttpStatus.ACCEPTED);
	}

	@GetMapping("/allocationHistory")
	public ResponseEntity<List<AllocationHistoryResponse>> getAllocationHistory(){
	
		return allocationHistoryServiceImpl.getAllocationHistory();
	}

	@GetMapping("/allocationHistory/{id}")
	public ResponseEntity<AllocationHistoryResponse> getAllocationHistoryById(@PathVariable("id")Long id){
		
		return allocationHistoryServiceImpl.getAllocationHistoryById(id);
	}

	@DeleteMapping("allocationHistory/{id}")
	public ResponseEntity<String> deleteAllocationHistoryById(@PathVariable("id")Long id){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    if(!user.isAdmin()) {
	    	return new ResponseEntity<>("Not Authenticated",HttpStatus.UNAUTHORIZED);
	    }
	    allocationHistoryServiceImpl.deleteAllocationHistoryById(id);
	    return new ResponseEntity<>("deleted sucesfully",HttpStatus.ACCEPTED);
	}
	

	

	@GetMapping("/userPoints")
	public ResponseEntity<List<User>> getUserPoints(){
		
		return userServiceImpl.getUserPoints();
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		
		return userServiceImpl.getUsers();
	}
	@GetMapping("/activeUsers")
	public ResponseEntity<List<User>> getActiveUsers(){
		
		return userServiceImpl.getActiveUsers();
	}
	
	@GetMapping("/inActiveUsers")
	public ResponseEntity<List<User>> getInActiveUsers(){
		
		return userServiceImpl.getInActiveUsers();
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id")Long id){
				
	    userServiceImpl.deleteUserById(id);
	    return new ResponseEntity<>("deleted sucesfully",HttpStatus.ACCEPTED);
	}
	@DeleteMapping("pastuser/{id}")
	public ResponseEntity<String> deletePastUserById(@PathVariable("id")Long id){
		allocationHistoryServiceImpl.deleteAllocationByUserId(id);
		redeemHistoryServiceImpl.deleteRedeemByUserId(id);
	    userServiceImpl.deletePastUser(id);
	    return new ResponseEntity<>("deleted sucesfully",HttpStatus.ACCEPTED);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id")Long id){
		
		return userServiceImpl.returnUserById(id);
	}

	@GetMapping("/redeemHistory")
	public ResponseEntity<List<RedeemResponse>> getredeemHistory(){
		
		return redeemHistoryServiceImpl.getRedeemHistory();
	}
	
	
	
}
