package com.backend.gallery.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryVideoRequestDto {
	private String galleryTitle;
	private List<String> videoList;
	
	@Override
	public String toString() {
		return "GalleryVideoRequestDto [galleryTitle=" + galleryTitle + ", videoList=" + videoList + "]";
	}
}
