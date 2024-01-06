package com.backend.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationFileDto {
	private String fileUri;
	private int notificationNo;
	private String fileName;
	private int position;
	
	@Override
	public String toString() {
		return "NotiicationFileDto [fileUri=" + fileUri + ", notificationNo=" + notificationNo + ", fileName="
				+ fileName + ", position=" + position + "]";
	}
}
