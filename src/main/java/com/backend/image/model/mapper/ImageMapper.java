package com.backend.image.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.backend.image.model.ImageDto;

@Mapper
public interface ImageMapper {
	void insertImage(ImageDto image) throws SQLException;

	void deleteImage(String saveFileName) throws SQLException;
}
