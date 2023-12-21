package com.backend.gallery.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryImageDto {
	private String imageUri;
	private int galleryNo;
	private int position;
	
	@Override
	public String toString() {
		return "GalleryImageDto [imageUri=" + imageUri + ", galleryNo=" + galleryNo + ", position=" + position + "]";
	}
}
