package com.backend.gallery.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryDto {
	private String galleryTitle;
	private int galleryNo;
	private String galleryType;
	private String galleryRegTime;
	
	@Override
	public String toString() {
		return "GalleryDto [galleryTitle=" + galleryTitle + ", galleryNo=" + galleryNo + ", galleryType=" + galleryType
				+ ", galleryRegTime=" + galleryRegTime + "]";
	}
}
