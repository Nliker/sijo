package com.backend.gallery.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.backend.gallery.model.GalleryResponseDto;
import com.backend.gallery.model.mapper.GalleryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {
	private final GalleryMapper galleryMapper;
	
	@Override
	public List<GalleryResponseDto> getGalleryList(String type, Map<String, Integer> map) throws Exception {
		List<GalleryResponseDto> gallleryList=new ArrayList<>();
		return gallleryList;
	}

}
