package com.backend.academy.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backend.academy.model.AcademyDto;
import com.backend.academy.model.AcademyReqeustDto;
import com.backend.academy.model.mapper.AcademyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService{
	@Value("${defaultImage}")
    private String defaultImage;
	
	private final AcademyMapper academyMapper;
	
	@Override
	public void createAcademy(AcademyReqeustDto academyRequest, String type) {
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

}
