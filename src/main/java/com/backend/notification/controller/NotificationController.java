package com.backend.notification.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.academy.model.AcademyResponseDto;
import com.backend.jwt.JwtService;
import com.backend.notification.model.NotificationRequestDto;
import com.backend.notification.model.NotificationResponseDto;
import com.backend.notification.model.service.NotificationService;

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
	public ResponseEntity<Map<String,String>> galleryImage(@CookieValue(value = "access_token",required = false) String access_token,@RequestPart NotificationRequestDto request,@RequestPart MultipartFile[] files) throws Exception{
		log.debug("Post notifications");
//		jwtService.validate(access_token);
		log.debug("json: " + request);
		log.debug("multi: "+ files);
		notificationService.createNotification(request,files);
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

}
