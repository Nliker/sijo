package com.backend.academy.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AcademyDto {
	private int academyNo;
	private String academyTitle;
	private String academyContent;
	private String academyRegTime;
	private String academyType;
	private String academyThumbnailUri;
	
	public AcademyDto(AcademyReqeustDto academyRequest) {
		this.academyTitle=academyRequest.getTitle();
		this.academyContent=academyRequest.getContent();
	}

	@Override
	public String toString() {
		return "AcademyDto [academyNo=" + academyNo + ", academyTitle=" + academyTitle + ", academyContent="
				+ academyContent + ", academyRegTime=" + academyRegTime + ", academyType=" + academyType
				+ ", academyThumbnailUri=" + academyThumbnailUri + "]";
	}
}
