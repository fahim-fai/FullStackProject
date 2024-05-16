package com.bytestrone.oops.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytestrone.oops.model.Image;

public interface ImageDao extends JpaRepository<Image, Long>{

}
