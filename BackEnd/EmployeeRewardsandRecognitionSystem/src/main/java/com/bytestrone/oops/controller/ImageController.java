package com.bytestrone.oops.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytestrone.oops.model.Image;
import com.bytestrone.oops.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@CrossOrigin(origins = "*")

public class ImageController {
	
	private final ImageService imageServiceImpl;
	

	@GetMapping("/{id}")
	public Optional<Image> getById(@PathVariable("id")Long id) {
		
		return imageServiceImpl.getById(id);
		
	}
	@PostMapping
	public Image create(@RequestBody String image) {
		return imageServiceImpl.create(image);
	}
}
