package com.backend.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserException extends Exception implements CustomException{
	private final int code;
	private final String msg;
	
	@Override
	public int getCode() {
		return this.code;
		
	}
	@Override
	public String getMsg() {
		return this.msg;
	}
}
