package com.backend.gallery.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryJoinMediaDto {
	private String galleryTitle;
	private String galleryRegTime;
	private List<String> contentList;
	
	public GalleryJoinMediaDto(GalleryDto gallery) {
		this.galleryTitle=gallery.getGalleryTitle();
		this.galleryRegTime=gallery.getGalleryRegTime();
	}

	@Override
	public String toString() {
		return "GalleryJoinMediaDto [galleryTitle=" + galleryTitle + ", galleryRegTime=" + galleryRegTime
				+ ", contentList=" + contentList + "]";
	}
}
