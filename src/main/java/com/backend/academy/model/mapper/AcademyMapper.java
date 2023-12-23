package com.backend.academy.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.backend.academy.model.AcademyDto;

@Mapper
public interface AcademyMapper {

	void insertAcademy(AcademyDto academyDto) throws SQLException;

	List<AcademyDto> selectAcademy(@Param("start") int start,@Param("count") int count,@Param("type") String type) throws SQLException;

	int selectAcademyCount(String type) throws SQLException;
	
	AcademyDto selectAcademyByTypeAndNo(@Param("type")String type,@Param("academyNo") int galleryNo) throws SQLException;
}
