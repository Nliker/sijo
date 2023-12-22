package com.backend.image.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageDto {
	private String originalFileName;
	private String saveFileName;
	
	public ImageDto(String originalFileName,String saveFileName) {
		this.originalFileName = originalFileName;
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "ImageDto [originalFileName=" + originalFileName + ", saveFileName=" + saveFileName + "]";
	}
}
