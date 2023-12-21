package com.backend.gallery.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryVideoDto {
	private String videoUri;
	private int galleryNo;
	private int order;
	
	@Override
	public String toString() {
		return "GalleryVideoDto [videoUri=" + videoUri + ", galleryNo=" + galleryNo + ", order=" + order + "]";
	}
}
