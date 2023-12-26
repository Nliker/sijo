package com.backend.academy.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backend.academy.model.AcademyDetailDto;
import com.backend.academy.model.AcademyDto;
import com.backend.academy.model.AcademyReqeustDto;
import com.backend.academy.model.AcademyResponseDto;
import com.backend.academy.model.mapper.AcademyMapper;
import com.backend.errorcode.AcademyErrorCode;
import com.backend.exception.AcademyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService{
	@Value("${defaultImage}")
    private String defaultImage;
	
	@Value("${academyDefaultPage}")
	private int academyDefaultPage;
	
	private final AcademyMapper academyMapper;
	
	@Override
	public void createAcademy(AcademyReqeustDto academyRequest, String type) throws Exception{
		AcademyDto academy=new AcademyDto(academyRequest);
		academy.setAcademyType(type);
		String thumbnailUri=academyRequest.getThumbnailUri();
		if("".equals(thumbnailUri) || thumbnailUri==null) {
			academy.setAcademyThumbnailUri(defaultImage);
		}else {
			academy.setAcademyThumbnailUri(thumbnailUri);
		}
		academyMapper.insertAcademy(academy);
	}

	@Override
	public List<AcademyResponseDto> getAcademyList(String type, Map<String, String> map) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", (Integer.parseInt(map.getOrDefault("page","1"))-1)*academyDefaultPage);
		param.put("count",Integer.parseInt(map.getOrDefault("count", String.valueOf(academyDefaultPage))));
		
		param.put("title",map.getOrDefault("title", ""));
		param.put("order", map.getOrDefault("order","latest"));
		param.put("type",type);
		
		List<AcademyDto> academyList=academyMapper.selectAcademy(param);
		List<AcademyResponseDto> academyResposneList=new ArrayList<>();
		for(AcademyDto academy:academyList) {
			System.out.println(academy);
			academyResposneList.add(new AcademyResponseDto(academy));
		}
		
		return academyResposneList;
	}

	@Override
	public int getAcademyTotalPage(String type,Map<String,String> map) throws Exception{
		Map<String, String> param = new HashMap<String, String>();
		param.put("type",type);
		param.put("title", map.getOrDefault("title", ""));
		return (int) Math.max(Math.ceil(((double)academyMapper.selectAcademyCount(param))/academyDefaultPage),1);
	}

	@Override
	public AcademyDetailDto getAcademy(String type, int academyNo) throws Exception {
		AcademyDto academy=academyMapper.selectAcademyByTypeAndNo(type, academyNo);
		if(academy==null) {
			throw new AcademyException(AcademyErrorCode.NotFoundAcademy.getCode(),AcademyErrorCode.NotFoundAcademy.getDescription());
		}
		return new AcademyDetailDto(academy);
	}

	@Override
	public void updateAcademy(AcademyReqeustDto academyRequest, String type, int academyNo) throws Exception {
		AcademyDto academy=academyMapper.selectAcademyByTypeAndNo(type, academyNo);
		if(academy==null) {
			throw new AcademyException(AcademyErrorCode.NotFoundAcademy.getCode(),AcademyErrorCode.NotFoundAcademy.getDescription());
		}
		
		AcademyDto updateAcademy=new AcademyDto(academyRequest);
		updateAcademy.setAcademyType(type);
		String thumbnailUri=academyRequest.getThumbnailUri();
		if("".equals(thumbnailUri) || thumbnailUri==null) {
			updateAcademy.setAcademyThumbnailUri(defaultImage);
		}else {
			updateAcademy.setAcademyThumbnailUri(thumbnailUri);
		}
		updateAcademy.setAcademyNo(academyNo);
		
		academyMapper.updateAcademyByNo(updateAcademy);
	}

	@Override
	public void deleteAcademy(String type, int academyNo) throws Exception {
		academyMapper.deleteAcademyByNo(type,academyNo);
	}

	
	
	

}
