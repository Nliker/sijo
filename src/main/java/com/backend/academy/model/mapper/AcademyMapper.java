package com.backend.academy.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.backend.academy.model.AcademyDto;

@Mapper
public interface AcademyMapper {

	void insertAcademy(AcademyDto academyDto);
}
