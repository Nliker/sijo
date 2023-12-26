package com.backend.gallery.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.errorcode.GalleryErrorCode;
import com.backend.exception.GalleryException;
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
	@Value("${defaultImage}")
    private String defaultImage;
	
	@Value("${galleryDefaultPage}")
	private int galleryDefaultPage;
	
	
	private final GalleryMapper galleryMapper;
	
	@Override
	public List<GalleryResponseDto> getGalleryList(String type, Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", (Integer.parseInt(map.getOrDefault("page","1"))-1)*galleryDefaultPage);
		param.put("count",Integer.parseInt(map.getOrDefault("count", String.valueOf(galleryDefaultPage))));
		param.put("title",map.getOrDefault("title", ""));
		param.put("order", map.getOrDefault("order","latest"));
		param.put("type",type);
		log.debug(param.toString());
		
		List<GalleryDto> galleryList=galleryMapper.selectGallery(param);

		List<GalleryResponseDto> galleryResposneList=new ArrayList<>();
		
		if("image".equals(type)) {
			for(GalleryDto gallery:galleryList) {
				GalleryResponseDto galleryResponse=new GalleryResponseDto(gallery);
				GalleryImageDto galleryImage=galleryMapper.selectOneGalleryImageByGalleryNo(gallery.getGalleryNo());
				if(galleryImage==null) {
					galleryResponse.setThumbnailUri(defaultImage);
				}
				else {
					galleryResponse.setThumbnailUri(galleryImage.getImageUri());
				}
				galleryResposneList.add(galleryResponse);
			}
		}
		if("video".equals(type)) {
			for(GalleryDto gallery:galleryList) {
				GalleryResponseDto galleryResponse=new GalleryResponseDto(gallery);
				GalleryVideoDto galleryVideo=galleryMapper.selectOneGalleryVideoByGalleryNo(gallery.getGalleryNo());
				if(galleryVideo==null) {
					galleryResponse.setThumbnailUri(defaultImage);
				}
				else {
					String[] tokenList=galleryVideo.getVideoUri().split("/");
					galleryResponse.setThumbnailUri("https://img.youtube.com/vi/"+tokenList[tokenList.length-1]+"/0.jpg");
				}
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
		GalleryDto gallery=galleryMapper.selectGalleryByTypeAndNo(type,galleryNo);
		if(gallery==null) {
			throw new GalleryException(GalleryErrorCode.NotFoundGallery.getCode(), GalleryErrorCode.NotFoundGallery.getDescription());
		}
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
	@Transactional
	@Override
	public void deleteGallery(String type, int galleryNo) throws Exception {
		if("image".equals(type)){
			galleryMapper.deleteGalleryImageByGalleryNo(galleryNo);
		}
		if("video".equals(type)){
			galleryMapper.deleteGalleryVideoByGalleryNo(galleryNo);
		}
		galleryMapper.deleteGalleryByNo(galleryNo);
		
	}
	@Override
	public void updateGalleryImage(int galleryNo, GalleryImageRequestDto galleryImageRequest) throws Exception {
		GalleryDto gallery=galleryMapper.selectGalleryByTypeAndNo("image",galleryNo);
		if(gallery==null) {
			throw new GalleryException(GalleryErrorCode.NotFoundGallery.getCode(), GalleryErrorCode.NotFoundGallery.getDescription());
		}
		gallery.setGalleryTitle(galleryImageRequest.getGalleryTitle());
		
		galleryMapper.updateGalleryTitleByNo(gallery);
		
		galleryMapper.deleteGalleryImageByGalleryNo(galleryNo);
		
		for(int i=0;i<galleryImageRequest.getImageList().size();i++) {
			GalleryImageDto galleryImage=new GalleryImageDto();
			galleryImage.setGalleryNo(gallery.getGalleryNo());
			galleryImage.setPosition(i);
			galleryImage.setImageUri(galleryImageRequest.getImageList().get(i));
			galleryMapper.insertGalleryImage(galleryImage);
		}
		
	}
	@Override
	public void updateGalleryVideo(int galleryNo, GalleryVideoRequestDto galleryVideoRequest) throws Exception {
		GalleryDto gallery=galleryMapper.selectGalleryByTypeAndNo("video",galleryNo);
		if(gallery==null) {
			throw new GalleryException(GalleryErrorCode.NotFoundGallery.getCode(), GalleryErrorCode.NotFoundGallery.getDescription());
		}
		gallery.setGalleryTitle(galleryVideoRequest.getGalleryTitle());

		galleryMapper.updateGalleryTitleByNo(gallery);
		
		galleryMapper.deleteGalleryVideoByGalleryNo(galleryNo);
		
		for(int i=0;i<galleryVideoRequest.getVideoList().size();i++) {
			GalleryVideoDto galleryVideo=new GalleryVideoDto();
			galleryVideo.setGalleryNo(gallery.getGalleryNo());
			galleryVideo.setPosition(i);
			galleryVideo.setVideoUri(galleryVideoRequest.getVideoList().get(i));
			galleryMapper.insertGalleryVideo(galleryVideo);
		}
		
		
		
	}
}
