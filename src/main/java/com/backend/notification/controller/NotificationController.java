package com.backend.notification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.academy.model.AcademyReqeustDto;
import com.backend.academy.model.AcademyResponseDto;
import com.backend.jwt.JwtService;
import com.backend.notification.model.NotificationRequestDto;
import com.backend.notification.model.NotificationResponseDto;
import com.backend.notification.model.NotificationUpdateReqeustDto;
import com.backend.notification.model.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;
	private final JwtService jwtService;
	
	@PostMapping("")
	public ResponseEntity<Map<String,String>> galleryImage(@CookieValue(value = "access_token",required = false) String access_token,@RequestPart String request,@RequestPart MultipartFile[] files) throws Exception{
		log.debug("Post notifications");
//		jwtService.validate(access_token);
		ObjectMapper objectMapper = new ObjectMapper();
	    NotificationRequestDto notificationRequest = objectMapper.readValue(request, NotificationRequestDto.class);
	    log.debug("json: " + notificationRequest);
		notificationService.createNotification(notificationRequest,files);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("")
	public ResponseEntity<Map<String,List<NotificationResponseDto>>> academy(@RequestParam Map<String, String> map) throws Exception{
		log.debug("Get Academy");
		
		Map<String,List<NotificationResponseDto>> result=new HashMap<>();
		result.put("notificationList",notificationService.getNotificationList(map));
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Map<String,Integer>> notificationPage(@RequestParam Map<String, String> map) throws Exception{
		log.debug("Get Notification");
		
		Map<String,Integer> result=new HashMap<>();
		result.put("notificationTotalPage",notificationService.getNotificationTotalPage(map));
		return ResponseEntity.ok().body(result);
	}
	
//	@PutMapping("/{notificationyNo}")
//	public ResponseEntity<Map<String,String>> updateAcademy(@CookieValue(value = "access_token",required = false) String access_token,@PathVariable int notificationNo,@RequestPart NotificationUpdateReqeustDto request,@RequestPart MultipartFile[] addList) throws Exception{
//		log.debug("Put Academy");
////		jwtService.validate(access_token);
//		
//		notificationService.updateNotification(request,addList,notificationNo);
//		Map<String,String> result=new HashMap<>();
//		result.put("result","successful");
//		return ResponseEntity.ok().body(result);
//	}

}
