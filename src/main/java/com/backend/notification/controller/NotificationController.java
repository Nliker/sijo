package com.backend.notification.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.jwt.JwtService;
import com.backend.notification.model.NotificationRequestDto;
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
	public ResponseEntity<Map<String,String>> galleryImage(@CookieValue(value = "access_token",required = false) String access_token,@RequestBody NotificationRequestDto noticiationReqeust) throws Exception{
		log.debug("Post notifications");
//		jwtService.validate(access_token);
		
		notificationService.createNotification(noticiationReqeust);
		Map<String,String> result=new HashMap<>();
		result.put("result","successful");
		return ResponseEntity.ok().body(result);
	}

}
