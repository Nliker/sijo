package com.backend.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.user.model.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
		private final UserService userService;
		
		@PostMapping("/login")
		public ResponseEntity<Map<String,String>> userLogin(@RequestBody Map<String,String> userPasswordMap) throws Exception{
			log.debug("Post UserLogin");
			Map<String,String> result=new HashMap<>();
			log.debug("userPassword: "+userPasswordMap.get("userPassword"));
			result.put("access_token",userService.login(userPasswordMap.get("userPassword")));
			return ResponseEntity.ok().body(result);
		}
		
		@PutMapping("/mypassword")
		public ResponseEntity<Map<String,String>> userPassword(@RequestBody Map<String,String> userPasswordMap) throws Exception{
			log.debug("Put UserPassword");
			userService.changePassword(userPasswordMap.get("password"), userPasswordMap.get("changePassword"));
			Map<String,String> result=new HashMap<>();
			result.put("result","successful");
			return ResponseEntity.ok().body(result);
		}
}
