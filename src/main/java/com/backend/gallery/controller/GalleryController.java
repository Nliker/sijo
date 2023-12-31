package com.backend.gallery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.gallery.model.GalleryImageRequestDto;
import com.backend.gallery.model.GalleryJoinMediaDto;
import com.backend.gallery.model.GalleryResponseDto;
import com.backend.gallery.model.GalleryVideoRequestDto;
import com.backend.gallery.model.service.GalleryService;
import com.backend.jwt.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("gallerys")
@RequiredArgsConstructor
public class GalleryController {
	private final GalleryService galleryService;
	private final JwtService jwtService;
	@GetMapping("/{type}")
	public ResponseEntity<Map<String,List<GalleryResponseDto>>> gallerys(@PathVariable String type,@RequestParam Map<String, String> map) throws Exception{
		log.debug("Get gallerys");
		Map<String,List<GalleryResponseDto>> result=new HashMap<>();
		result.put("galleryList",galleryService.getGalleryList(type,map));
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/video")
	public ResponseEntity<Map<String,String>> galleryVideo(@CookieValue(value = "access_token",required = false) String access_token,@RequestBody GalleryVideoRequestDto galleryVideoRequest) throws Exception{
		log.debug("Post gallerys");
		jwtService.validate(access_token);
		
		galleryService.createVideoGallery(galleryVideoRequest);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/image")
	public ResponseEntity<Map<String,String>> galleryImage(@CookieValue(value = "access_token",required = false) String access_token,@RequestBody GalleryImageRequestDto galleryImageRequest) throws Exception{
		log.debug("Post gallerys");
		jwtService.validate(access_token);
		
		galleryService.createImageGallery(galleryImageRequest);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{type}/{gallayNo}")
	public ResponseEntity<Map<String,GalleryJoinMediaDto>> gallery(@PathVariable String type,@PathVariable int gallayNo) throws Exception{
		log.debug("Get gallery");
		Map<String,GalleryJoinMediaDto> result=new HashMap<>();
		result.put("galleryDetailInfo",galleryService.getGalleryDetail(type,gallayNo));
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/{type}/{galleryNo}")
	public ResponseEntity<Map<String,String>> deleteGallery(@CookieValue(value = "access_token",required = false) String access_token,@PathVariable String type,@PathVariable int galleryNo) throws Exception{
		log.debug("Delete gallery");
		jwtService.validate(access_token);
		
		galleryService.deleteGallery(type,galleryNo);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping("/image/{galleryNo}")
	public ResponseEntity<Map<String,String>> updateGalleryImage(@CookieValue(value = "access_token",required = false) String access_token,@PathVariable int galleryNo,@RequestBody GalleryImageRequestDto galleryImageRequest) throws Exception{
		log.debug("Put gallery");
		jwtService.validate(access_token);
		
		galleryService.updateGalleryImage(galleryNo,galleryImageRequest);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping("/video/{galleryNo}")
	public ResponseEntity<Map<String,String>> updateGalleryVideo(@CookieValue(value = "access_token",required = false) String access_token,@PathVariable int galleryNo,@RequestBody GalleryVideoRequestDto galleryVideoRequest) throws Exception{
		log.debug("Put gallery");
		jwtService.validate(access_token);
		
		galleryService.updateGalleryVideo(galleryNo,galleryVideoRequest);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	

}
