package com.backend.errorcode;

import lombok.Getter;

@Getter
public enum AcademyErrorCode {
	NotFoundAcademy(404,"해당 아카데미는 존재하지 않습니다.");
	
	private int code;
    private String description;
    
    private AcademyErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
