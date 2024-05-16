package com.bytestrone.oops.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bytestrone.oops.dto.ProductRequest;
import com.bytestrone.oops.model.Product;

public interface ProductService {

	public void addProduct(ProductRequest productRequest);

	public void deleteProduct(Long id);

	public ResponseEntity<List<Product>> getProducts();

	public ResponseEntity<Product> getProduct(Long id);

	public void updateProduct(Long id, ProductRequest product);

	public void recoverProduct(Long id);
	
	

}
