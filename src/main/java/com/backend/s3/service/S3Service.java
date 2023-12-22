package com.backend.s3.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	public String uploadImage(MultipartFile multipartFile) throws Exception;
	public void deleteImage(String saveFileName) throws Exception;
}
