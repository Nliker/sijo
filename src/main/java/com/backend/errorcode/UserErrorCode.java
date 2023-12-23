package com.backend.errorcode;

import lombok.Getter;

@Getter
public enum UserErrorCode {
	NotFoundUser(401,"해당 관리자는 존재하지 않습니다."),
	NotMatchPassword(401,"비밀번호가 일치하지 않습니다.");
	
	private int code;
    private String description;
    
    private UserErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
