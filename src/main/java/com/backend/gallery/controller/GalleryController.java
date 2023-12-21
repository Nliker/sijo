package com.backend.gallery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("gallerys")
@RequiredArgsConstructor
public class GalleryController {
	private final GalleryService galleryService;
	
	@GetMapping("/{type}")
	public ResponseEntity<Map<String,Integer>> gallerys(@PathVariable String type,@RequestParam Map<String, String> map){
		log.debug("Get gallerys");
		Map<String,List<GalleryResponseDto>> result=new HashMap<>();
		result.put("galleryList",galleryService.getGalleryList(type,map));
		return ResponseEntity.ok().body(result);
	}
}
