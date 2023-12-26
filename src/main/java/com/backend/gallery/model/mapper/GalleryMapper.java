package com.backend.gallery.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.backend.gallery.model.GalleryDto;
import com.backend.gallery.model.GalleryImageDto;
import com.backend.gallery.model.GalleryVideoDto;

@Mapper
public interface GalleryMapper {
	List<GalleryDto> selectGallery(Map<String, Object> param) throws SQLException;
	GalleryImageDto selectOneGalleryImageByGalleryNo(int galleryNo) throws SQLException;
	GalleryVideoDto selectOneGalleryVideoByGalleryNo(int galleryNo) throws SQLException;

	void insertGallery(GalleryDto gallery) throws SQLException;
	
	void insertGalleryVideo(GalleryVideoDto galleryVideo) throws SQLException;
	void insertGalleryImage(GalleryImageDto galleryImage) throws SQLException;
	
	
	List<String> selectGalleryImageByGalleryNo(int galleryNo) throws SQLException;
	List<String> selectGalleryVideoByGalleryNo(int galleryNo) throws SQLException;
	
	void deleteGalleryByNo(int galleryNo) throws SQLException;
	
	void deleteGalleryImageByGalleryNo(int galleryNo) throws SQLException;
	void deleteGalleryVideoByGalleryNo(int galleryNo) throws SQLException;
	
	void updateGalleryTitleByNo(GalleryDto gallery) throws SQLException;
	
	GalleryDto selectGalleryByTypeAndNo(@Param("type")String type,@Param("galleryNo") int galleryNo) throws SQLException;
}
