package com.backend.notification.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationDto {
	private int notificationNo;
	private String notificationTitle;
	private String notificationCotent;
	private String notificationRegDate;
	
	public NotificationDto() {
		super();
	}
	
	public NotificationDto(NotificationRequestDto notificationRequest) {
		this.notificationTitle=notificationRequest.getTitle();
		this.notificationCotent=notificationRequest.getContent();
	}
	
	@Override
	public String toString() {
		return "NotificationDto [notificationNo=" + notificationNo + ", notificationTitle=" + notificationTitle
				+ ", notificationCotent=" + notificationCotent + ", notificationRegDate=" + notificationRegDate + "]";
	}

}
