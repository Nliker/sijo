package com.backend.academy.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AcademyDetailDto {
	private String academyTitle;
	private String academyContent;
	private String academyRegTime;
	
	public AcademyDetailDto(AcademyDto academy) {
		this.academyTitle=academy.getAcademyTitle();
		this.academyContent=academy.getAcademyContent();
		this.academyRegTime=academy.getAcademyRegTime();
	}
	
	@Override
	public String toString() {
		return "AcademyDetailDto [academyTitle=" + academyTitle + ", academyContent=" + academyContent
				+ ", academyRegTime=" + academyRegTime + "]";
	}
}
