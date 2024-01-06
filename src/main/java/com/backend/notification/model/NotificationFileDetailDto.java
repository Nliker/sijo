package com.backend.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationFileDetailDto {
	private String fileUri;
	private String fileName;
	
	@Override
	public String toString() {
		return "NotificationFileDetailDto [fileUri=" + fileUri + ", fileName=" + fileName + "]";
	}
}
