package com.backend.errorcode;

import lombok.Getter;

@Getter
public enum NotificationErrorCode {
	NotFoundNotification(404,"해당 갤러리는 존재하지 않습니다.");
	
	private int code;
    private String description;
    
    private NotificationErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
