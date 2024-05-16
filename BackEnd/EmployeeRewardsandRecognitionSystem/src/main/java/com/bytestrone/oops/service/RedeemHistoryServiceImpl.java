package com.bytestrone.oops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.dao.ProductDao;
import com.bytestrone.oops.dao.RedeemHistoyDao;
import com.bytestrone.oops.dao.UserDao;
import com.bytestrone.oops.dto.ErrorResponse;
import com.bytestrone.oops.dto.RedeemRequest;
import com.bytestrone.oops.dto.RedeemResponse;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.Product;
import com.bytestrone.oops.model.RedeemHistory;
import com.bytestrone.oops.model.User;

@Service
public class RedeemHistoryServiceImpl implements RedeemHistoryService {
	@Autowired
	RedeemHistoyDao redeemHistoryDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	UserService userServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(RedeemHistoryServiceImpl.class);
	
	//buying a product
	@Override
	public ResponseEntity<ResponseWrapper<RedeemRequest>> redeem(RedeemRequest redeemRequest) {
        Long userCurrentPoint = 0L;
        Long productPrice = 0L;
        Long quantity = 0L;
        ErrorResponse errorResponse = new ErrorResponse();

        
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            logger.info("User: {}", user);

            Optional<Product> products = productDao.findById(redeemRequest.getProductId());
            Product product = products.get();

            userCurrentPoint = user.getPoints();
            productPrice = product.getPoints();
            quantity = product.getQuantity();

            if (quantity > 0) { //to check whether if the product is out of stock or not 
                if (userCurrentPoint < productPrice) { //to check if the use have enogh points
                    errorResponse.setErrorMessage("Not enough Points");
                    ResponseWrapper<RedeemRequest> responseWrapper = new ResponseWrapper<>(null, errorResponse);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWrapper);
                } else {
                    RedeemHistory redeemHistory = new RedeemHistory(user, product);
                    redeemHistory.creationDate();
                    redeemHistory.setCreatedUser(userServiceImpl.getUserByToken());
                    redeemHistoryDao.save(redeemHistory);
                    logger.info("User points before redemption: {}", user.getPoints());
                    userCurrentPoint = userCurrentPoint - productPrice;
                    user.setPoints(userCurrentPoint);
                    userDao.save(user);
                    logger.info("User points after redemption: {}", user.getPoints());
                    quantity = quantity - 1;
                    product.setQuantity(quantity);
                    productDao.save(product);
                    ResponseWrapper<RedeemRequest> responseWrapper = new ResponseWrapper<>(redeemRequest, null);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseWrapper);
                }
            } else {
                errorResponse.setErrorMessage("Cannot buy a product with quantity 0");
                ResponseWrapper<RedeemRequest> responseWrapper = new ResponseWrapper<>(null, errorResponse);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWrapper);
            }
       
    }

	//get redeem history of certain user
	@Override
	public ResponseEntity<List<RedeemResponse>> getRedeemHistoryById() {
       
            List<RedeemResponse> redeemResponseList = new ArrayList<>();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            List<RedeemHistory> redeemList = redeemHistoryDao.findRedeemByUserId(user.getId());
            for (RedeemHistory redeemData : redeemList) {
                Product product = redeemData.getProduct();
                RedeemResponse redeemResponse = new RedeemResponse();
                redeemResponse.setId(redeemData.getId());
                redeemResponse.setUserName(user.getUsername());
                redeemResponse.setProductName(product.getName());
                redeemResponse.setRedeemedPoints(product.getPoints());
                redeemResponse.setRedeemedDate(redeemData.getRedeemedDate());
                redeemResponseList.add(redeemResponse);
            }
            return ResponseEntity.ok(redeemResponseList);
        
    }
	//get every redeem history
	@Override
    public ResponseEntity<List<RedeemResponse>> getRedeemHistory() {
       
            List<RedeemResponse> redeemResponseList = new ArrayList<>();
            List<RedeemHistory> redeemList = redeemHistoryDao.findAll();
            for (RedeemHistory redeemData : redeemList) {
                User user = redeemData.getUser();
                Product product = redeemData.getProduct();
                RedeemResponse redeemResponse = new RedeemResponse();
                redeemResponse.setId(redeemData.getId());
                redeemResponse.setUserName(user.getUsername());
                redeemResponse.setProductName(product.getName());
                redeemResponse.setRedeemedPoints(product.getPoints());
                redeemResponse.setRedeemedDate(redeemData.getRedeemedDate());
                redeemResponseList.add(redeemResponse);
            }
            return ResponseEntity.ok(redeemResponseList);
        
    }

	//delete reedeem history of given user
	@Override
    public void deleteRedeemByUserId(Long id) {
            redeemHistoryDao.deleteByUserId(id);
        
    }

	//delete redeem history of given product
    @Override
    public void deleteRedeemByProductId(Long id) {
        
            redeemHistoryDao.deleteByProductId(id);
       
    }

}
