package com.backend.gallery.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryImageRequestDto {
	private String galleryTitle;
	private List<String> imageList;
	
	@Override
	public String toString() {
		return "GalleryImageRequestDto [galleryTitle=" + galleryTitle + ", imageList=" + imageList + "]";
	}
}
