package com.backend.gallery.model.service;

import java.util.List;
import java.util.Map;

import com.backend.gallery.model.GalleryResponseDto;

public interface GalleryService {
	List<GalleryResponseDto> getGalleryList(String type,Map<String,Integer> map) throws Exception;
}
