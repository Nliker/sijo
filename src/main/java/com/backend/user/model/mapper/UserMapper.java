package com.backend.user.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	int selectByPassword(String password) throws SQLException;

	void updateByPassword(@Param("password") String password,@Param("changePassword") String changePassword);
}
