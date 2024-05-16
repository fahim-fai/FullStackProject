package com.bytestrone.oops.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.dao.ImageDao;
import com.bytestrone.oops.dao.ProductDao;
import com.bytestrone.oops.dto.ProductRequest;
import com.bytestrone.oops.model.Image;
import com.bytestrone.oops.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	@Autowired
	ImageDao imageDao;
	@Autowired
	UserService userServiceImpl;

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	//adding a new product
	@Override
	public void addProduct(ProductRequest productRequest) {
		
			Image image = new Image();
			image.setImage(productRequest.getImage());
			imageDao.save(image);
			Product product = new Product();
			product.setImage(image);
			product.setCategory(productRequest.getCategory());
			product.setExpiryDate(productRequest.getExpiryDate());
			product.setName(productRequest.getName());
			product.setPoints(productRequest.getPoints());
			product.setQuantity(productRequest.getQuantity());
			product.setTermsAndCondition(productRequest.getTermsAndCondition());
			product.setAvailable(true);
			product.creationDate();
			product.setCreatedUser(userServiceImpl.getUserByToken());
			productDao.save(product);
			logger.info("Product {} added successfully", product.getName());
		
	}

	//deleting a product
	@Override
	public void deleteProduct(Long id) {
		
			Product product = productDao.findById(id)
					.orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
			product.setQuantity(0L);
			product.setAvailable(false);
			productDao.save(product);
			logger.info("Product with id {} deleted successfully", id);
		
	}

	//get all products
	@Override
	public ResponseEntity<List<Product>> getProducts() {
		
			List<Product> productList = productDao.findAll();
			return ResponseEntity.ok(productList);
		
	}

	//get product with certain id
	@Override
	public ResponseEntity<Product> getProduct(Long id) {
		
			Optional<Product> products = productDao.findById(id);
			Product product = products.get();
			return ResponseEntity.ok(product);
	
	}

	//update the product with given id
	@Override
	public void updateProduct(Long id, ProductRequest productRequest) {
		
			Optional<Product> products = productDao.findById(id);
			if (products.isEmpty()) {
				logger.error("Product not found with id: {}", id);
				return;
			}

			Product product = products.get();

			product.setCategory(productRequest.getCategory());
			product.setExpiryDate(productRequest.getExpiryDate());
			product.setName(productRequest.getName());
			product.setPoints(productRequest.getPoints());
			product.setQuantity(productRequest.getQuantity());
			product.setTermsAndCondition(productRequest.getTermsAndCondition());
			product.modifiedDate();
			product.setModifiedUser(userServiceImpl.getUserByToken());

			if (product.getImage_id() != null) {
				Optional<Image> oldImages = imageDao.findById(product.getImage_id());

				if (oldImages.isPresent()) {
					Image oldImage = oldImages.get();

					if (!oldImage.getImage().equals(productRequest.getImage())) {
						Image image = new Image();
						image.setImage(productRequest.getImage());
						imageDao.save(image);
						product.setImage(image);
						productDao.save(product);
						imageDao.delete(oldImage);
					}
				}
			} else {
				Image image = new Image();
				image.setImage(productRequest.getImage());
				imageDao.save(image);
				product.setImage(image);
			}

			productDao.save(product);
		
	}

	//recover the deleted product
	@Override
	public void recoverProduct(Long id) {
		
			Optional<Product> products = productDao.findById(id);
			if (products.isEmpty()) {
				logger.error("Product not found with id: {}", id);
				return;
			}

			Product product = products.get();
			product.setAvailable(true);
			productDao.save(product);
		
	}

}
