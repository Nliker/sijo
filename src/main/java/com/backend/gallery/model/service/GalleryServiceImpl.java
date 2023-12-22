package com.backend.gallery.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.gallery.model.GalleryDto;
import com.backend.gallery.model.GalleryImageDto;
import com.backend.gallery.model.GalleryImageRequestDto;
import com.backend.gallery.model.GalleryJoinMediaDto;
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
		int start= (Integer.parseInt(String.valueOf(map.getOrDefault("page",1))) -1) * 15;
		int count= Integer.parseInt(String.valueOf(map.getOrDefault("count", 15)));

		List<GalleryDto> galleryList=galleryMapper.selectGallery(start,count, type);

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
				String[] tokenList=galleryVideo.getVideoUri().split("/");
				galleryResponse.setThumbnailUri("https://img.youtube.com/vi/"+tokenList[tokenList.length-1]+"/0.jpg");
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
			galleryMapper.insertGalleryVideo(galleryVideo);
		}
	}
	
	@Transactional
	@Override
	public void createImageGallery(GalleryImageRequestDto galleryImageRequest) throws Exception {
		GalleryDto gallery=new GalleryDto(galleryImageRequest);
		galleryMapper.insertGallery(gallery);
		for(int i=0;i<galleryImageRequest.getImageList().size();i++) {
			GalleryImageDto galleryImage=new GalleryImageDto();
			galleryImage.setGalleryNo(gallery.getGalleryNo());
			galleryImage.setPosition(i);
			galleryImage.setImageUri(galleryImageRequest.getImageList().get(i));
			galleryMapper.insertGalleryImage(galleryImage);
		}
	}
	@Override
	public GalleryJoinMediaDto getGalleryDetail(String type,int galleryNo) throws Exception {
		GalleryDto gallery=galleryMapper.selectGalleryByNo(galleryNo);
		GalleryJoinMediaDto galleryDetail=new GalleryJoinMediaDto(gallery);
		if("image".equals(type)){
			galleryDetail.setContentList(galleryMapper.selectGalleryImageByGalleryNo(galleryNo));
		}
		if("video".equals(type)){
			galleryDetail.setContentList(galleryMapper.selectGalleryVideoByGalleryNo(galleryNo));
		}

		if (galleryDetail.getContentList()==null) {
			galleryDetail.setContentList(new ArrayList<>());
		}
		return galleryDetail;
	}
}
