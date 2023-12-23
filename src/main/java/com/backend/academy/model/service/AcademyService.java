package com.backend.academy.model.service;

import com.backend.academy.model.AcademyReqeustDto;

public interface AcademyService {

	void createAcademy(AcademyReqeustDto academyRequest, String type);
}
