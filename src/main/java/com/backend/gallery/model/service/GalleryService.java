package com.backend.gallery.model.service;

import java.util.List;
import java.util.Map;

import com.backend.gallery.model.GalleryImageRequestDto;
import com.backend.gallery.model.GalleryJoinMediaDto;
import com.backend.gallery.model.GalleryResponseDto;
import com.backend.gallery.model.GalleryVideoRequestDto;

public interface GalleryService {
	List<GalleryResponseDto> getGalleryList(String type,Map<String,Integer> map) throws Exception;

	void createVideoGallery(GalleryVideoRequestDto galleryVideoRequest) throws Exception;
	void createImageGallery(GalleryImageRequestDto galleryImageRequest) throws Exception;

	GalleryJoinMediaDto getGalleryDetail(String type, int galleryNo) throws Exception;

	void deleteGallery(String type, int galleryNo) throws Exception;
}
