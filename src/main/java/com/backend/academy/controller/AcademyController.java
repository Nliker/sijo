package com.backend.academy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.academy.model.AcademyReqeustDto;
import com.backend.academy.model.service.AcademyService;
import com.backend.jwt.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("academys")
@RequiredArgsConstructor
public class AcademyController {
	private final AcademyService academyService;
	private final JwtService jwtService;
	
	@PostMapping("/{type}")
	public ResponseEntity<Map<String,String>> galleryVideo(@PathVariable String type,@RequestBody AcademyReqeustDto academyRequest) throws Exception{
		log.debug("Post Academy");
//		jwtService.validate(access_token);
		
		academyService.createAcademy(academyRequest,type);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
}
