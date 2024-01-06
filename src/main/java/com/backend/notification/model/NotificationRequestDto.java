package com.backend.notification.model;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationRequestDto {
	private String title;
	private String content;
	private MultipartFile[] fileList;
	
	@Override
	public String toString() {
		return "NotificationRequestDto [title=" + title + ", content=" + content + ", fileList="
				+ Arrays.toString(fileList) + "]";
	}
}
