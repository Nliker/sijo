package com.backend.academy.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AcademyResponseDto {
	private String academyTitle;
	private int academyNo;
	private String academyRegTime;
	private String thumbnailUri;
	
	
	public AcademyResponseDto(AcademyDto academy) {
		this.academyTitle=academy.getAcademyTitle();
		this.academyNo=academy.getAcademyNo();
		this.academyRegTime=academy.getAcademyRegTime();
		this.thumbnailUri=academy.getAcademyThumbnailUri();
	}


	@Override
	public String toString() {
		return "AcademyResponseDto [academyTitle=" + academyTitle + ", academyNo=" + academyNo + ", academyRegTime="
				+ academyRegTime + ", thumbnailUri=" + thumbnailUri + "]";
	}
}
