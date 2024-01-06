package com.backend.notification.model;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationJoinFileDto {
	private String notificationTitle;
	private String notificationRegTime;
	private String notificationContent;
	private List<NotificationFileDetailDto> notificationFileList =new ArrayList<>();
	
	@Override
	public String toString() {
		return "NotificationJoinFileDto [notificationTitle=" + notificationTitle + ", notificationRegTime="
				+ notificationRegTime + ", notificationContent=" + notificationContent + ", notificationFileList="
				+ notificationFileList + "]";
	}
	
}
