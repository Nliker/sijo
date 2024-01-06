package com.backend.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationResponseDto {
	private String notificationTitle;
	private int notificationNo;
	private String notificationRegTime;
	
	public NotificationResponseDto() {
		super();
	}
	
	public NotificationResponseDto(NotificationDto notification) {
		this.notificationTitle=notification.getNotificationTitle();
		this.notificationNo=notification.getNotificationNo();
		this.notificationRegTime=notification.getNotificationRegTime();
	}
	
	@Override
	public String toString() {
		return "NotificationResponseDto [notificationTitle=" + notificationTitle + ", notificationNo=" + notificationNo
				+ ", notificationRegTime=" + notificationRegTime + "]";
	}
}
