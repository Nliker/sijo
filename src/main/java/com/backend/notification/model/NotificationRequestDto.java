package com.backend.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationRequestDto {
	private String title;
	private String content;
	
	@Override
	public String toString() {
		return "NotificationRequestDto [title=" + title + ", content=" + content + "]";
	}
}
