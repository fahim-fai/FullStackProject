package com.bytestrone.oops.service;

import java.util.Optional;

import com.bytestrone.oops.model.Image;

public interface ImageService {

	Optional<Image> getById(Long id);

	Image create(String image);

}
