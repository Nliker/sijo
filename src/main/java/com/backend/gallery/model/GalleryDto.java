package com.backend.gallery.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GalleryDto {
	private String galleryTitle;
	private int galleryNo;
	private String galleryType;
	private String galleryRegTime;
	
	
	
	public GalleryDto(GalleryVideoRequestDto GalleryVideoRequest) {
		this.galleryTitle=GalleryVideoRequest.getGalleryTitle();
		this.galleryType="video";
	}
	
	public GalleryDto(GalleryImageRequestDto GalleryImageRequest) {
		this.galleryTitle=GalleryImageRequest.getGalleryTitle();
		this.galleryType="image";
	}
	
	@Override
	public String toString() {
		return "GalleryDto [galleryTitle=" + galleryTitle + ", galleryNo=" + galleryNo + ", galleryType=" + galleryType
				+ ", galleryRegTime=" + galleryRegTime + "]";
	}

	public GalleryDto(String galleryTitle, int galleryNo, String galleryType, String galleryRegTime) {
		super();
		this.galleryTitle = galleryTitle;
		this.galleryNo = galleryNo;
		this.galleryType = galleryType;
		this.galleryRegTime = galleryRegTime;
	}
}
