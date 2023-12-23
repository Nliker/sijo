package com.backend.user.model.service;

import org.springframework.stereotype.Service;

import com.backend.errorcode.UserErrorCode;
import com.backend.exception.UserException;
import com.backend.jwt.JwtService;
import com.backend.user.model.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final JwtService jwtService;
	private final UserMapper userMapper;
	
	@Override
	public String login(String userPassword) throws Exception {
		if(userMapper.selectByPassword(userPassword)==0) {
			throw new UserException(UserErrorCode.NotFoundUser.getCode(), UserErrorCode.NotFoundUser.getDescription());
		}
		
		return jwtService.create();
	}

	@Override
	public void changePassword(String password, String changePassword) throws Exception {
		if(userMapper.selectByPassword(password)==0) {
			throw new UserException(UserErrorCode.NotMatchPassword.getCode(), UserErrorCode.NotMatchPassword.getDescription());
		}
		userMapper.updateByPassword(password,changePassword);
	}
	

	
}
