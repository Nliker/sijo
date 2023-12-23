package com.backend.academy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.academy.model.AcademyDetailDto;
import com.backend.academy.model.AcademyReqeustDto;
import com.backend.academy.model.AcademyResponseDto;
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
	public ResponseEntity<Map<String,String>> academy(@PathVariable String type,@RequestBody AcademyReqeustDto academyRequest) throws Exception{
		log.debug("Post Academy");
//		jwtService.validate(access_token);
		
		academyService.createAcademy(academyRequest,type);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{type}")
	public ResponseEntity<Map<String,List<AcademyResponseDto>>> academy(@PathVariable String type,@RequestParam Map<String, Integer> map) throws Exception{
		log.debug("Get Academy");
		
		Map<String,List<AcademyResponseDto>> result=new HashMap<>();
		result.put("academyList",academyService.getAcademyList(type,map));
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{type}/page")
	public ResponseEntity<Map<String,Integer>> academyPage(@PathVariable String type) throws Exception{
		log.debug("Get Academy");
		
		Map<String,Integer> result=new HashMap<>();
		result.put("academyTotalPage",academyService.getAcademyTotalPage(type));
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{type}/{academyNo}")
	public ResponseEntity<Map<String,AcademyDetailDto>> academyPage(@PathVariable String type,@PathVariable int academyNo) throws Exception{
		log.debug("Get Academy");
		
		Map<String,AcademyDetailDto> result=new HashMap<>();
		result.put("academyDetailInfo",academyService.getAcademy(type,academyNo));
		return ResponseEntity.ok().body(result);
	}
}
