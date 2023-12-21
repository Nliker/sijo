package com.backend.gallery.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.gallery.model.GalleryDto;
import com.backend.gallery.model.GalleryImageDto;
import com.backend.gallery.model.GalleryVideoDto;

@Mapper
public interface GalleryMapper {
	List<GalleryDto> selectGallery(int start,int count,String type) throws SQLException;
	List<GalleryImageDto> selectGalleryImageByGalleryNo(int galleryNo) throws SQLException;
	List<GalleryVideoDto> selectGalleryVideoByGalleryNo(int galleryNo) throws SQLException;
}
