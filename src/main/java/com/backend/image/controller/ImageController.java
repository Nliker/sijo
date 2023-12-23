package com.backend.image.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.jwt.JwtService;
import com.backend.s3.service.S3ServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
	private final S3ServiceImpl s3Service;
	private final JwtService jwtService;
	
	@PostMapping("")
	public ResponseEntity<Map<String,String>> galleryImage(@CookieValue(value = "access_token",required = false) String access_token,@RequestPart("file") MultipartFile file) throws Exception{
		log.debug("Post images");
		jwtService.validate(access_token);
		
		
		Map<String,String> result=new HashMap<>();
		result.put("imageUri",s3Service.uploadImage(file));
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/{saveFileName}")
	public ResponseEntity<Map<String,String>> galleryImage(@CookieValue(value = "access_token",required = false) String access_token,@PathVariable String saveFileName) throws Exception{
		log.debug("Delete images");
		jwtService.validate(access_token);
		
		s3Service.deleteImage(saveFileName);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
}
