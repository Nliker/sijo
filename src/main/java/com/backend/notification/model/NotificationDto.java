package com.backend.notification.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationDto {
	private int notificationNo;
	private String notificationTitle;
	private String notificationContent;
	private String notificationRegTime;
	
	public NotificationDto() {
		super();
	}
	
	public NotificationDto(NotificationRequestDto notificationRequest) {
		this.notificationTitle=notificationRequest.getTitle();
		this.notificationContent=notificationRequest.getContent();
	}

	public NotificationDto(NotificationUpdateReqeustDto notificationUpdateReqeust) {
		this.notificationTitle=notificationUpdateReqeust.getTitle();
		this.notificationContent=notificationUpdateReqeust.getContent();
	}

	@Override
	public String toString() {
		return "NotificationDto [notificationNo=" + notificationNo + ", notificationTitle=" + notificationTitle
				+ ", notificationContent=" + notificationContent + ", notificationRegTime=" + notificationRegTime + "]";
	}
	
	

}
