package com.backend.gallery.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryResponseDto {
	private int galleryNo;
	private String galleryTitle;
	private String galleryRegTime;
	private String thumbnailUri;
	
	@Override
	public String toString() {
		return "GalleryResponseDto [galleryNo=" + galleryNo + ", galleryTitle=" + galleryTitle + ", galleryRegTime="
				+ galleryRegTime + ", thumbnailUri=" + thumbnailUri + "]";
	}
}
