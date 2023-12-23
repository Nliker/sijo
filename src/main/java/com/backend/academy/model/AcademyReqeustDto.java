package com.backend.academy.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AcademyReqeustDto {
	private String title;
	private String content;
	private String thumbnailUri;
	
	@Override
	public String toString() {
		return "AcademyReqeustDto [title=" + title + ", content=" + content + ", thumbnailUri=" + thumbnailUri + "]";
	}
}
