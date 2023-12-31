package com.backend.academy.model.service;

import java.util.List;
import java.util.Map;

import com.backend.academy.model.AcademyDetailDto;
import com.backend.academy.model.AcademyReqeustDto;
import com.backend.academy.model.AcademyResponseDto;

public interface AcademyService {

	void createAcademy(AcademyReqeustDto academyRequest, String type) throws Exception;

	List<AcademyResponseDto> getAcademyList(String type, Map<String, String> map) throws Exception;

	int getAcademyTotalPage(String type,Map<String, String> map) throws Exception;

	AcademyDetailDto getAcademy(String type, int academyNo) throws Exception;

	void updateAcademy(AcademyReqeustDto academyRequest, String type, int academyNo) throws Exception;

	void deleteAcademy(String type, int academyNo) throws Exception;
}
