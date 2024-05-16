package com.bytestrone.oops.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.dao.ImageDao;
import com.bytestrone.oops.model.Image;
@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageDao imageDao;

	//get image with given id
	@Override
	public Optional<Image> getById(Long id) {
		return imageDao.findById(id);
	}

	//saving image
	@Override
	public Image create(String imageString) {
		Image image=new Image(imageString);
		imageDao.save(image);
		return image;
	}

}
