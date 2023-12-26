package com.backend.academy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.backend.academy.model.AcademyDto;

@Mapper
public interface AcademyMapper {

	void insertAcademy(AcademyDto academyDto) throws SQLException;

	List<AcademyDto> selectAcademy(Map<String,Object> param) throws SQLException;

	int selectAcademyCount(Map<String, String> param) throws SQLException;
	
	AcademyDto selectAcademyByTypeAndNo(@Param("type")String type,@Param("academyNo") int academyNo) throws SQLException;

	void updateAcademyByNo(AcademyDto updateAcademy) throws SQLException;

	void deleteAcademyByNo(@Param("type")String type,@Param("academyNo") int academyNo) throws SQLException;
}
