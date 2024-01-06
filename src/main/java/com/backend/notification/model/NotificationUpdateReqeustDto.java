package com.backend.notification.model;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationUpdateReqeustDto {
	private String title;
	private String content;
	private String[] deleteList;
	
	@Override
	public String toString() {
		return "NotificationUpdateReqeustDto [title=" + title + ", content=" + content + ", deleteList="
				+ Arrays.toString(deleteList) + "]";
	}
}
