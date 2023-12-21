package com.backend.gallery.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.gallery.model.GalleryDto;
import com.backend.gallery.model.GalleryImageDto;
import com.backend.gallery.model.GalleryResponseDto;
import com.backend.gallery.model.GalleryVideoDto;
import com.backend.gallery.model.GalleryVideoRequestDto;
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
		List<GalleryDto> galleryList=galleryMapper.selectGallery(map.getOrDefault("page", 0)*map.getOrDefault("count", 15), map.getOrDefault("count", 15), type);
		
		
		List<GalleryResponseDto> galleryResposneList=new ArrayList<>();
		
		if("image".equals(type)) {
			for(GalleryDto gallery:galleryList) {
				GalleryResponseDto galleryResponse=new GalleryResponseDto(gallery);
				GalleryImageDto galleryImage=galleryMapper.selectOneGalleryImageByGalleryNo(gallery.getGalleryNo());
				galleryResponse.setThumbnailUri(galleryImage.getImageUri());
				galleryResposneList.add(galleryResponse);
			}
		}
		if("video".equals(type)) {
			for(GalleryDto gallery:galleryList) {
				GalleryResponseDto galleryResponse=new GalleryResponseDto(gallery);
				GalleryVideoDto galleryVideo=galleryMapper.selectOneGalleryVideoByGalleryNo(gallery.getGalleryNo());
				String video=galleryVideo.getVideoUri().split("=")[1];
				galleryResponse.setThumbnailUri("https://img.youtube.com/vi/"+video+"/0.jpg");
				galleryResposneList.add(galleryResponse);
			}
		}
		
		return galleryResposneList;
	}
	@Transactional
	@Override
	public void createVideoGallery(GalleryVideoRequestDto galleryVideoRequest) throws Exception {
		GalleryDto gallery=new GalleryDto(galleryVideoRequest);
		galleryMapper.insertGallery(gallery);
		for(int i=0;i<galleryVideoRequest.getVideoList().size();i++) {
			GalleryVideoDto galleryVideo=new GalleryVideoDto();
			galleryVideo.setGalleryNo(gallery.getGalleryNo());
			galleryVideo.setPosition(i);
			galleryVideo.setVideoUri(galleryVideoRequest.getVideoList().get(i));
			System.out.println(galleryVideo);
			galleryMapper.insertGalleryVideo(galleryVideo);
		}
	}
}
